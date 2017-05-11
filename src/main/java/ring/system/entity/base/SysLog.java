package ring.system.entity.base;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 日志实体类
 * @author YU
 *2016 08 22
 */
@Entity
@Table(name = "rsys_log")
public class SysLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long logId;
    
    private String operatePerson;
    private Date operateTime;
    private String ip;
    private String method;
    private String requestUri;
    private String params;
    private String executeTime;
    private String logType;
    private String exinfo;
    private String browser;
    private String browserMajorVersion;
    private String browserVersion;
    private String device;
    private String os;
    private String osType;
    private String osManu;
    private String osVersion;
    private String osBit;

    
    public Long getLogId() {
		return logId;
	}
	public void setLogId(Long logId) {
		this.logId = logId;
	}
	public String getOperatePerson() {
        return operatePerson;
    }
    public void setOperatePerson(String operatePerson) {
        this.operatePerson = operatePerson == null ? null : operatePerson.trim();
    }
    public Date getOperateTime() {
        return operateTime;
    }
    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }
    public String getRequestUri() {
        return requestUri;
    }
    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri == null ? null : requestUri.trim();
    }
    public String getExecuteTime() {
        return executeTime;
    }
    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime == null ? null : executeTime.trim();
    }
    public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
	public String getParams() {
        return params;
    }
    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }
    public String getExinfo() {
        return exinfo;
    }
    public void setExinfo(String exinfo) {
        this.exinfo = exinfo == null ? null : exinfo.trim();
    }
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getBrowserMajorVersion() {
		return browserMajorVersion;
	}
	public void setBrowserMajorVersion(String browserMajorVersion) {
		this.browserMajorVersion = browserMajorVersion;
	}
	public String getBrowserVersion() {
		return browserVersion;
	}
	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}
	public String getOsType() {
		return osType;
	}
	public void setOsType(String osType) {
		this.osType = osType;
	}
	public String getOsVersion() {
		return osVersion;
	}
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}
	public String getOsBit() {
		return osBit;
	}
	public void setOsBit(String osBit) {
		this.osBit = osBit;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getOsManu() {
		return osManu;
	}
	public void setOsManu(String osManu) {
		this.osManu = osManu;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	
}