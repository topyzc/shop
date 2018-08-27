package com.shop.index.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shop.category.service.CategoryService;
import com.shop.category.vo.Category;
import com.shop.product.service.ProductService;
import com.shop.product.vo.Product;

public class IndexAction extends ActionSupport {

	//注入一级分类的service
	private CategoryService categoryService;
	
	//注入商品的service
	private ProductService productService;
	
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}


	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}


	/**
	 * 执行的访问首页的方法:
	 */
	public String execute(){
		//查询所有一级分类
		List<Category> cList=categoryService.findAll();
		
		//将一级分类存入session的范围
		ActionContext.getContext().getSession().put("clist", cList);
		//查询热门商品
		List<Product> hlist=productService.findHot();
		
		//保存到值栈中
		ActionContext.getContext().getValueStack().set("hlist", hlist);
		//查询最新的商品
		List<Product> nlist=productService.findNew();
		
		//保存到值栈中
	    ActionContext.getContext().getValueStack().set("nlist", nlist);
		return "index";
	}
	
	
	
	
}
