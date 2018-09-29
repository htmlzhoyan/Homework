package com.zy.controller;



import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.zy.entity.User;
import com.zy.service.IUserService;
import com.zy.utils.JsonUtils;
import com.zy.vo.JsonBean;




@Controller
public class UserController {
	@Autowired
	private IUserService iUserService;
	
	@RequestMapping(value="/findByUserNo",method=RequestMethod.POST)
	@ResponseBody
	public JsonBean findByUserNo(String no,String password,HttpSession session) {
		
		
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		if((String) request.getSession(false).getAttribute("login")!=null) {
			String username = (String) request.getSession(false).getAttribute("login");	
			User user = iUserService.findUserByNameService(username);
			return JsonUtils.createJsonBean(1, user);	
		}else {
			return JsonUtils.createJsonBean(0, null);	
		}
		
	}
	
	@RequestMapping(value="/selectUser",method=RequestMethod.GET)
	@ResponseBody
	public JsonBean selectUser(HttpSession session) {
		String username = (String) session.getAttribute("login"); 	
		User user = iUserService.findUserByNameService(username);
		return JsonUtils.createJsonBean(1, user);	

	}
	
	@RequestMapping(value="/findUsrByNameAnd",method=RequestMethod.GET)
	@ResponseBody
	public JsonBean findUsrByNameAnd(User user) {
		String findUsrByNameAnd = iUserService.findUsrByNameAnd(user);
		if(findUsrByNameAnd==null) {
			return JsonUtils.createJsonBean(0, null);	
		}else{
			return JsonUtils.createJsonBean(1, findUsrByNameAnd);	
		}
		

	}
	
	
	@RequestMapping(value="/registerName",method=RequestMethod.POST)
	@ResponseBody
	public JsonBean registerName(String username,String password,String phone,String createdate) {
		User user = new User();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parse = null;
		try {
			parse = simpleDateFormat.parse(createdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setUsername(username);
		user.setPassword(password);
		user.setPhone(phone);
		user.setCreatedate(parse);
		try {
			iUserService.insertService(user);
			return JsonUtils.createJsonBean(1, user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonUtils.createJsonBean(0, e.getMessage());
		}
		
		
	
		
	}
	
	
	/*更新用户*/
	@RequestMapping(value="/updatetName",method=RequestMethod.POST)
	@ResponseBody
	public JsonBean updatetName(User user) {
		try {
			iUserService.updatetName(user);
			return JsonUtils.createJsonBean(1, null);	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonUtils.createJsonBean(0, null);	
		}	
	}
	
	
	@RequestMapping(value="/loginName",method=RequestMethod.POST)
	@ResponseBody
	public JsonBean loginName(String no,String password,HttpSession session) {
		
		
	
		UsernamePasswordToken token = new UsernamePasswordToken(no, password);
		Subject subject = SecurityUtils.getSubject();
		
		try {
			subject.login(token);
			session.setAttribute("login", no);
			return JsonUtils.createJsonBean(1, null);	
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonUtils.createJsonBean(0, e.getMessage());	
		}	
		
	}
	
	
	
	
	
}
