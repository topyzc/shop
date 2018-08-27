package com.shop.adminuser.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shop.adminuser.vo.AdminUser;
import com.shop.user.vo.User;

public class AdminUserDao extends HibernateDaoSupport{

	//后台登录方法
	public AdminUser login(AdminUser adminUser) {
		
		String hql = "from AdminUser where username = ? and password = ? ";
		Object[] value={adminUser.getUsername(),adminUser.getPassword()};
		List<AdminUser> list = this.getHibernateTemplate().find(hql, value);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

}
