package com.shop.user.service;

import org.springframework.transaction.annotation.Transactional;

import com.shop.user.dao.UserDao;
import com.shop.user.vo.User;
import com.shop.utils.MailUitls;
import com.shop.utils.UUIDUtils;


/**
 * 用户模块业务层
 * @author yezhichao
 * @Decipetion
 * @2018年8月12日
 */
@Transactional
public class UserService {
	//注入userDao
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao=userDao;
	}
	//用户名查询用户的方法
	public User findByUsername(String username) {
		  return userDao.findByUsername(username);
	}
	
	//用户注册方法
	public void save(User user) {
	
		user.setState(0);
		
		user.setCode(UUIDUtils.getCode());
		
		userDao.save(user);
		
		MailUitls.sendMail(user.getEmail(), user.getCode());
	}
	public User findByCode(String code) {
		
		return userDao.findByCode(code);
	}
	
	/**
	 * 修改用户的状态
	 * @param existUser
	 */
	public void update(User existUser) {
		
		userDao.update(existUser);
	}
	
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	public User login(User user) {
		
		return userDao.login(user);
		
	}
}
