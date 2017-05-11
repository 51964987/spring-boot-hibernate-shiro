package ring.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ring.system.dao.ISysLogDao;
import ring.system.entity.base.SysLog;
import ring.system.service.SysLogService;

@Service
public class SysLogServiceImpl implements SysLogService {
	
	@Autowired
	private ISysLogDao sysLogDao;

	@Override
	public SysLog insert(SysLog record) throws Exception {
		return sysLogDao.save(record);
	}

}
