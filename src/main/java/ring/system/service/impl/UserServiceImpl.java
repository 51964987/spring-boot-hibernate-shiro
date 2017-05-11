package ring.system.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ring.system.dao.IUserDao;
import ring.system.entity.base.User;
import ring.system.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private IUserDao userdao;
	
	@Override
	public User findByUsername(String username) {
		return userdao.findByUsername(username);
	}

	@Override
	public void addUser(User user) {
		userdao.save(user);
	}

}
