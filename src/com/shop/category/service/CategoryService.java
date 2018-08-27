package com.shop.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.shop.category.dao.CategoryDao;
import com.shop.category.vo.Category;

@Transactional
public class CategoryService {
	
	//注入CategoryDao
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	//业务查询所有一级分类的方法
	public List<Category> findAll(){
		
		return categoryDao.findAll();
	}

	//添加一级分类
	public void save(Category category) {
		categoryDao.save(category);
		
	}

	//查询一级分类
	public Category findByCid(Integer cid) {
		
		return categoryDao.findByCid(cid);
	}

	//删除一级分类
	public void delete(Category category) {
		
		categoryDao.delete(category);
	}

	//修改一级分类
	public void update(Category category) {
		categoryDao.update(category);
		
	}
	
	
	

}
