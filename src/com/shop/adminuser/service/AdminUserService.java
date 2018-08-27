package com.shop.adminuser.service;

import com.shop.adminuser.dao.AdminUserDao;
import com.shop.adminuser.vo.AdminUser;

public class AdminUserService {
	
	//注入Dao
	private AdminUserDao adminUserDao;

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	//后台登录方法
	public AdminUser login(AdminUser adminUser) {
	
		return adminUserDao.login(adminUser);
	}

	

	
	
	

}
