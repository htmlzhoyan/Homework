package com.zy.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.dao.IUserDao;

import com.zy.entity.User;
import com.zy.service.IUserService;

@Service
public class UserService implements IUserService{
	@Autowired
	private IUserDao iUserDao;
	@Override
	public User findUserByNameService(String username) {
		// TODO Auto-generated method stub
		return iUserDao.findUsrByName(username);
	}
	@Override
	public void insertService(User user) {
		// TODO Auto-generated method stub
		User findUsrByName = iUserDao.findUsrByName(user.getUsername());
		if(findUsrByName!=null) {
			throw new RuntimeException("用户名已存在");
		}else {
			iUserDao.insertName(user);
		}
		
	}
	@Override
	public void updatetName(User user) {
		iUserDao.updatetName(user);
		
	}
	@Override
	public String findUsrByNameAnd(User user) {
		return iUserDao.findUsrByNameAnd(user);
	}


}
