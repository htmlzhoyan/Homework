package com.zy.realm;

import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.zy.entity.User;
import com.zy.service.IUserService;




public class MyRealm extends AuthorizingRealm{
	@Autowired
	private IUserService iUserService;

	// 授权
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//		String name = (String)principals.getPrimaryPrincipal();
//		// 从数据库获取用户的角色和权限列表
//		
//		List<String> roleList = resDao.findRoleByUname(name);
//		List<String> permitList = resDao.findPermitByUname(name);
//		
//		Set<String> roleSet = new HashSet<>(roleList);
//		Set<String> perSet = new HashSet<>(permitList);
//				
//		// 授权信息的对象
//		SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
//		// 设置用户对应的角色
//		info.setRoles(roleSet);
//		// 设置对应的权限
//		info.setStringPermissions(perSet);
		
		return null;
	}

	// 认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String no = (String)token.getPrincipal();
		
		// 从数据库中根据用户名获取密码信息
		User user = iUserService.findUserByNameService(no);
		
		if(user == null){
			throw new UnknownAccountException();
		}
		
		String password = user.getPassword();
		
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(no, password, this.getName());
		
		return info;
	}

	

}
