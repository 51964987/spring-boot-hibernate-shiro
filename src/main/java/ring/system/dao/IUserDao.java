package ring.system.dao;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ring.system.entity.base.User;

@Repository
public interface IUserDao extends PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User> {

    User findByUsername(String userName);

}