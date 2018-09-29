package com.zy.service;

import java.util.List;
import java.util.Map;

import com.zy.entity.Log;
import com.zy.vo.PageBean;

public interface ILogService {
	public void insertLogService(Log log);
	public Log selectLog(String createtime,String username);
	public void updateLogServic(Log log);
	public PageBean<Log> selectLogName(int page, int limit,String username);
}
