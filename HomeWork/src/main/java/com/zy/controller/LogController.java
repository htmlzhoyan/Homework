package com.zy.controller;



import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import com.zy.entity.Log;

import com.zy.service.ILogService;
import com.zy.utils.JsonUtils;
import com.zy.vo.JsonBean;
import com.zy.vo.PageBean;




@Controller
public class LogController {
	@Autowired
	private ILogService iLogService;
	
	@RequestMapping(value="/selectLogName",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> selectLogName(int page, int limit,HttpSession session) {
		
		String username = (String) session.getAttribute("login");
		PageBean<Log> pageInfo = iLogService.selectLogName(page, limit, username);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("code", 0);// 针对layui的表格，0表示成功
		map.put("msg", "");
		map.put("count", pageInfo.getCount());
		map.put("data", pageInfo.getPageInfos());
		return map;
	}
	

	@RequestMapping(value="/insertLog",method=RequestMethod.GET)
	@ResponseBody
	public JsonBean insertLog(HttpSession session) {
		Log log = new Log();
		
		
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		String createtime = simpleDateFormat.format(date);
		Date parse = null;
		try {
			parse = simpleDateFormat.parse(createtime);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		
		String username = (String) session.getAttribute("login");
		
		Date date2 = new Date();
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		String createtime2 = simpleDateFormat2.format(date2);
		Log selectLog = iLogService.selectLog("%" + createtime2 + "%",username);
		
		//flag 0 代表未完成
		//time 次数
		if(selectLog==null) {
			
			log.setTime(1);
			log.setCreatetime(parse);
			if(hour>8&&hour<9) {		
				log.setFlag("1");
				log.setUsername(username);
				iLogService.insertLogService(log);
				return JsonUtils.createJsonBean(1, "上午签到成功");			
			}else {	
				log.setFlag("0");
				log.setUsername(username);
				iLogService.insertLogService(log);
				return JsonUtils.createJsonBean(1, "你迟早了");		
			}
		}else {
			
			if(selectLog.getEndtime()==null) {
				log.setTime(2);
				log.setEndtime(parse);		
				log.setUsername(username);
				log.setId(selectLog.getId());			
				if(hour>21&&hour<22) {	
					log.setFlag("1");
					iLogService.updateLogServic(log);
					return JsonUtils.createJsonBean(1, "下午签到成功");		
				}else {	
					log.setFlag("0");
					iLogService.updateLogServic(log);
					return JsonUtils.createJsonBean(1, "你早退了");		
				}
			}else {
				return JsonUtils.createJsonBean(1, "你今天已经打够2次了");		
			}
			
		}
		
	}
	
	
	
	
	
}
