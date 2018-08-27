package com.shop.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shop.user.service.UserService;
import com.shop.user.vo.User;

public class UserAction extends ActionSupport implements ModelDriven<User>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	//模型驱动使用的对像
	private User user=new User();
	
	
	//注入UserService
	private UserService userService;
	
	//接收验证码
   public String  checkcode;
   
	
	

	public void setCheckcode(String checkcode) {
	this.checkcode = checkcode;
}



	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	

	@Override
	public User getModel() {
		
		return user;
	}

	/**
	 * 跳转到注册页面的执行方法
	 */
	public String registPage() {
		
		return "registPage";
	}
	
	/**
	 * Ajax进行校验用户的方法
	 * @throws IOException 
	 */
     public String findByName() throws IOException {
		//调用Service进行查询
    	 User existUser=userService.findByUsername(user.getUsername());
    	 //获得response对象，页面输出
    	 HttpServletResponse response = ServletActionContext.getResponse();
    	 response.setContentType("text/html;charset=UTF-8");
    	 
    	 if(existUser!=null) {
    		 //查询到用户
    		 response.getWriter().println("<font color='red'>用户名已经存在</font>");
    		 
    		 
    	 }else {
    		 response.getWriter().println("<font color='green'>用户名可以使用</font>");
    		 
    	 }
    	 return NONE;
     }
     
     /**
      * 用户注册
      */
     public String regist(){
    	
    	String checkcode1=(String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
    	if(!checkcode.equalsIgnoreCase(checkcode1)) {
    		this.addActionError("验证码出错");
    		return "checkcodeFail";
    	}
    	
    	userService.save(user);
    	this.addActionMessage("注册成功！请到邮箱激活");
    	
		return "msg";
    	 
     }
     
     /**
      * 用户激活方法
      */

     public String active() {
    	 User existUser=userService.findByCode(user.getCode());
		 
    	 if(existUser==null) {
    		 //激活码错误的
    		 this.addActionMessage("激活失败");
    	 }else {
    		 
    		 //激活成功
    		 existUser.setState(1);
    		 existUser.setCode(null);
    		 userService.update(existUser);
    		 this.addActionMessage("激活成功，请登录");
    		 
    	 }
    	 return "msg";
       
     }
     
     /**
      * 跳转到登录页面
      */
	 public String loginPage() {
		 
		 return "loginPage";
	 }
	 
	 
	 /**
	  * 用户登录
	  */
	 public String login() {
		  String checkcode1=(String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
	    	if(!checkcode.equalsIgnoreCase(checkcode1)) {
	    		this.addActionError("验证码出错");
	    		return "checkcodeerror";
	    	}
		 User existUser = userService.login(user);
			// 判断
			if (existUser == null) {
				// 登录失败
				this.addActionError("登录失败:用户名或密码错误或用户未激活!");
				return LOGIN;
			} else {
				
				// 登录成功
				// 将用户的信息存入到session中
				ServletActionContext.getRequest().getSession()
						.setAttribute("existUser", existUser);
				// 页面跳转
				return "loginSuccess";
			}
	 }
	 
	 
	 /**
	  * 用户退出的方法
	  */
	 public String quit() {
		 ServletActionContext.getRequest().getSession().invalidate();
		 return "quit";
	 }
}
