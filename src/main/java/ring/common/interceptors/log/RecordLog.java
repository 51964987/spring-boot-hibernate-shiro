package ring.common.interceptors.log;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import ring.common.consts.PlatformConstant;
import ring.common.interceptors.SpringContextHolder;
import ring.system.entity.base.SysLog;
import ring.system.entity.base.User;
import ring.system.service.SysLogService;
import ring.utils.IPUtil;
/**
 * 系统平台的 日志记录
 * @author YU
 *2016 08 22
 */
public class RecordLog {
	//利用线程池 提高效率 减少开销  YU
	private static Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

/**
 * 插入日志统一入口 方法
 * @param request
 * @param response
 * @param handler
 * @param ex
 * @param beginTime
 * @param endTime 
 * @author ring
 * @date 2017年5月6日 上午12:10:07
 * @version V1.0
 */
	public static void saveLog(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex,Long beginTime,Long endTime){
		User user = (User)SecurityUtils.getSubject().getSession().getAttribute(PlatformConstant.SESSION_USER_KEY);
		SysLog log = new SysLog();
		if(user!=null){			
			log.setOperatePerson(user.getUsername());
		}
		log.setOperateTime(new Date(beginTime));
		log.setIp(IPUtil.getIpAddr(request));
		log.setMethod(request.getMethod());
		log.setRequestUri(request.getRequestURI());
		log.setParams(getParamsDetail(request));
		log.setExecuteTime(Long.toString(endTime-beginTime));
		log.setLogType(ex == null ? PlatformConstant.TYPE_ACCESS : PlatformConstant.TYPE_EXCEPTION);
		if(null != ex){
			log.setExinfo(ex.getMessage());
		}
		
		//客户访问设备信息
		String userAgent = request.getHeader("user-agent");
	    UserAgent ua = UserAgent.parseUserAgentString(userAgent);
	    OperatingSystem os = ua.getOperatingSystem();
	    if(os != null){
	    	log.setOs(os.name());//访问设备系统
	    	log.setOsType(os.getDeviceType().getName());//访问设备类型
	    	log.setOsVersion(os.getGroup().getName());//访问设备版本
	    	log.setOsManu(os.getManufacturer().getName());//访问设备制造厂商
	    	//log.setOsBit(osBit);//访问设备系统
	    }
	    if("android".equalsIgnoreCase(log.getOsVersion())){//安卓系统	    	
	    	log.setDevice(parse(userAgent));
	    }
	    
	    //客户浏览器信息
	    if(ua.getBrowser()!=null){
	    	log.setBrowser(ua.getBrowser().getGroup().getName());
	    }
	    if (ua.getBrowserVersion() != null) {
	    	log.setBrowserMajorVersion(ua.getBrowserVersion().getMajorVersion());
	    	log.setBrowserVersion(ua.getBrowserVersion().getVersion());
	    }
		
		// 异步保存日志
		executor.execute(new SaveLogThread(log));
	}
	
	public static String getFromRegrex(String str,String pattern,int index){  
	    Pattern p = Pattern.compile(pattern);  
	    Matcher matcher = p.matcher(str);  
	    while(matcher.find())  
	        str = matcher.group(index);  
	    return str;  
	}  
	public static String parse(String userAgent){  
	    String pattern1 = "(.*) AppleWebKit";  
	    String pattern2 = ".* ";  
	    String str = getFromRegrex(userAgent,pattern1,1);  
	    String params[] = str.split(";");  
	    return getFromRegrex(params[params.length-1],pattern2,0).trim();  
	}

	/**
	 * 保存日志线程
	 */
	public static class SaveLogThread extends Thread{
		private static final Logger logger = LoggerFactory.getLogger(SaveLogThread.class);
		private SysLog log;

		public SaveLogThread(SysLog log){
			super(SaveLogThread.class.getSimpleName());
			this.log = log;
		}
		
		@Override
		public void run() {
			SysLogService logService =(SysLogService) SpringContextHolder.getBean("sysLogServiceImpl");
			// 保存日志信息
			try {
				logService.insert(log);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(),e);
			}
		}
	}
	
	/**
	 * 解析获取的 请求参数
	 * @param request
	 * @return 
	 * @author ring
	 * @date 2017年5月6日 上午12:14:33
	 * @version V1.0
	 */
	public static String getParamsDetail(HttpServletRequest request){
		StringBuffer parameter = new StringBuffer();
		Map<String, String[]> parameterMap = request.getParameterMap();
		if (parameterMap != null) {
			for (Entry<String, String[]> entry : parameterMap.entrySet()) {
				String parameterName = entry.getKey();
					String[] parameterValues = entry.getValue();
					if (parameterValues != null) {
						for (String parameterValue : parameterValues) {
							if(!"password".equalsIgnoreCase(parameterName)){//密码不打印								
								parameter.append(parameterName + " = " + parameterValue + "|");
							}
						}
					}
		
			}
		}
		return parameter.toString();
	}
}
