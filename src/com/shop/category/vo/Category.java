package com.shop.category.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.shop.categorysecond.vo.CategorySecond;

/**
 * 一级分类的实体类
 * @author yezhichao
 * @Decipetion
 * @2018年8月13日
 */
public class Category implements Serializable{

	private Integer cid;
	private String cname;
	private Set<CategorySecond> categorySeconds=new HashSet<>();
	
	
	
	public Set<CategorySecond> getCategorySeconds() {
		return categorySeconds;
	}
	public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
		this.categorySeconds = categorySeconds;
	}
	
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	@Override
	public String toString() {
		return "Category [cid=" + cid + ", cname=" + cname + "]";
	}
	
	
}
