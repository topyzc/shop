package com.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shop.category.vo.Category;

/**
 * 一级分类的持久层对象
 * @author yezhichao
 * @Decipetion
 * @2018年8月13日
 */
public class CategoryDao extends HibernateDaoSupport{

	public List<Category> findAll() {
		String hql="from Category";
		List<Category> list=this.getHibernateTemplate().find(hql);
		return list;
	}

	//保存一级分类
	public void save(Category category) {
	
		this.getHibernateTemplate().save(category);
	}

	//查询一级分类
	public Category findByCid(Integer cid) {
		
		return (Category) this.getHibernateTemplate().get(Category.class, cid);
	}

	//删除一级分类
	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
		
	}

	//修改一级分类
	public void update(Category category) {
		
		this.getHibernateTemplate().update(category);
		
	}
	
	

}
