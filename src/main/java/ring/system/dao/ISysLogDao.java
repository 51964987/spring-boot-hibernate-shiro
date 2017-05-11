package ring.system.dao;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ring.system.entity.base.SysLog;

@Repository
public interface ISysLogDao extends PagingAndSortingRepository<SysLog, Long>, JpaSpecificationExecutor<SysLog> {

	@SuppressWarnings("unchecked")
	SysLog save(SysLog sysLog);
}