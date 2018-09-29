package com.zy.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.zy.entity.Log;


public interface ILogDao {
	public void insertLog(Log log);
	public Log selectLog(Map<String, Object> map);
	public void updateLog(Log log);
	public List<Log> selectLogName(Map<String, Object> map);
	
}
