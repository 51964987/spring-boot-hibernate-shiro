package ring.system.service;

import ring.system.entity.base.User;

public interface UserService {
	User findByUsername(String username);
	void addUser(User user);
}
