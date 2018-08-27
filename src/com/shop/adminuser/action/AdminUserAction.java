package com.shop.adminuser.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shop.adminuser.service.AdminUserService;
import com.shop.adminuser.vo.AdminUser;

public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>{

	//注入service
	private AdminUserService adminUserService;

	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	//模型驱动来接收参数
	private AdminUser adminUser=new AdminUser();
	@Override
	public AdminUser getModel() {
		
		return adminUser;
	}
	
	/**
	 * 后台登录方法
	 */
	public String login() {
		AdminUser existAdminUser=adminUserService.login(adminUser);
		if(existAdminUser == null) {
			this.addActionError("登录失败！");
			return "loginFail";
		}else {
			ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", existAdminUser);
		}
		return "loginSuccess";
	}
	
	
}
