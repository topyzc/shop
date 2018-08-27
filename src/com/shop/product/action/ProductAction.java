package com.shop.product.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shop.category.service.CategoryService;
import com.shop.category.vo.Category;
import com.shop.product.service.ProductService;
import com.shop.product.vo.Product;
import com.shop.utils.PageBean;

public class ProductAction extends ActionSupport implements ModelDriven<Product>{

	//注入productService
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	//模型驱动接收参数
	private Product product=new Product();
	public Product getModel() {
		
		return product;
	}
	
	
	private Integer cid;
	
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
	public Integer getCid() {
		return cid;
	}

	private Integer csid;
	
	

	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}


	private int page;
	

	public void setPage(int page) {
		this.page = page;
	}

	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}


	public String findByPid() {
		product=productService.findByPid(product.getPid());
		return "findByPid";
		
	}
	

	public String findByCid() {
		//List<Category> cList = categoryService.findAll();
		PageBean<Product> pageBean=productService.findByPageCid(cid,page);
	    ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	
	//根据二级分类id查询商品
	public String findByCsid() {
		//根据二级分类查询
		PageBean<Product> pageBean=productService.findByPageCsid(csid,page);
		System.out.println(pageBean);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}

}
