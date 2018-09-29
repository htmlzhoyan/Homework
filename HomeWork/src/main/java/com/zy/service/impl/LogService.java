package com.zy.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.dao.ILogDao;

import com.zy.entity.Log;
import com.zy.service.ILogService;
import com.zy.vo.PageBean;
@Service
public class LogService implements ILogService {
	@Autowired
	private ILogDao iLog;
	@Override
	public void insertLogService(Log log) {
		iLog.insertLog(log);	
	}
	@Override
	public Log selectLog(String createtime,String username) {
		// TODO Auto-generated method stub
		System.out.println(createtime);
		System.out.println(username);
		Map<String, Object> map = new HashMap<>();
		map.put("createtime", createtime);
		map.put("username", username);
		Log selectLog = iLog.selectLog(map);
		return selectLog;	
	}
	@Override
	public void updateLogServic(Log log) {
		// TODO Auto-generated method stub
		iLog.updateLog(log);
	}
	@Override
	public PageBean<Log> selectLogName(int page, int limit,String username) {
		// TODO Auto-generated method stub
		PageBean<Log> pageInfo= new PageBean<>();
		pageInfo.setCurrentPage(page);
		pageInfo.setPageSize(limit);
		
		
		int index = (page - 1) * pageInfo.getPageSize();
		Map<String, Object> map = new HashMap<>();
		map.put("index", index);
		map.put("size", pageInfo.getPageSize());
		map.put("username", username);
		List<Log> list = iLog.selectLogName(map);
		pageInfo.setPageInfos(list);
		
		int count = list.size();
		pageInfo.setCount(count);
		
		return pageInfo;
	}
	
	
}
