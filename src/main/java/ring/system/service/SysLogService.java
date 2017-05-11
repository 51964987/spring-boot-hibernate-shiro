package ring.system.service;

import ring.system.entity.base.SysLog;

public interface SysLogService {
	/**
	 * 插入日志
	 * 
	 */
	SysLog insert(SysLog record) throws Exception;
}
