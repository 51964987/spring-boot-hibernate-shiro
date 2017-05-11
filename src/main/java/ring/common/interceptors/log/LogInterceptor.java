package ring.common.interceptors.log;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/**
 * 平台系统 统一的日志记录 Interceptor
 * @author YU
 *2016 08 22
 */
public class LogInterceptor implements HandlerInterceptor {
	
	private static final ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("ThreadLocal StartTime");
	
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,Object arg2) throws Exception {
		//线程绑定变量（该数据只有当前请求的线程可见）
		startTimeThreadLocal.set(System.currentTimeMillis());
		return true;
	}



	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,Object arg2, ModelAndView arg3) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)throws Exception {
		long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）  
		long endTime = System.currentTimeMillis(); 	//2、结束时间 
		RecordLog.saveLog(request, response, handler, ex, beginTime, endTime);//调用Log日志记录器

	}

}
