package cn.service;

import cn.pojo.User;

public interface UserService {
	User login(User user);

	int regist(User user);

	int update(User user);

	User checkUser(String tel);
}
