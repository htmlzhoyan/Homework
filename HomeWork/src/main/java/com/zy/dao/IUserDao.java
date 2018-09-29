package com.zy.dao;

import com.zy.entity.User;

public interface IUserDao {
	public User findUsrByName(String username);
	public void insertName(User user);
	public void updatetName(User user);
	public String findUsrByNameAnd(User user);
	
}
