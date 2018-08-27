package com.shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shop.user.vo.User;

/**
 * 用户模块持久层
 * @author yezhichao
 * @Decipetion
 * @2018年8月12日
 */
public class UserDao extends HibernateDaoSupport{

	// 按名次查询是否有该用户:
	public User findByUsername(String username){
		String hql = "from User where username = ?";
		List<User> list = this.getHibernateTemplate().find(hql, username);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	//用户注册方法
	public void save(User user) {
		
		this.getHibernateTemplate().save(user);
		
	}

	/**
	 * 根据激活码查询用户
	 * @param code
	 * @return
	 */
	public User findByCode(String code) {
		String hql="from User where code=?";
		List<User> list=this.getHibernateTemplate().find(hql,code);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 修改用户的状态
	 * @param existUser
	 */
	public void update(User existUser) {
		
		this.getHibernateTemplate().update(existUser);
		
	}

	    // 用户登录的方法
		public User login(User user) {
			
			String hql = "from User where username = ? and password = ? and state = 1";
			Object[] value={user.getUsername(),user.getPassword()};
			List<User> list = this.getHibernateTemplate().find(hql, value);
			if(list != null && list.size() > 0){
				return list.get(0);
			}
			return null;
		}
}
