package com.zy.service;

import java.util.List;
import java.util.Map;

import com.zy.entity.User;
import com.zy.vo.PageBean;

public interface IUserService {
	public User findUserByNameService(String username);
	public void insertService(User user);
	public void updatetName(User user);
	public String findUsrByNameAnd(User user);
}

