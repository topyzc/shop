# shop
ssh版本的 我的商城
1.1前台需求分析:
1.1.1用户模块:
注册:
1.前台JS校验:
2.使用AJAX完成对用户名异步校验:
3.后台Struts2校验:
4.验证码:
5.发送激活邮件:
6.将用户信息存入到数据库:
激活:
1.点击链接完成激活:
* 根据激活码,查询数据库中是否有该用户:
* 如果有:激活.(将激活码字段清空.)
* 修改用户的状态:
登录:
1.输入用户名和密码:(数据校验.)
2.用户名和密码都正确同时用户状态必须是激活状态:
退出:
1.销毁session:
1.1.2一级分类模块:
查询一级分类:
1.查询一级分类.
* 将一级分类存入到session范围.(每个页面中都有一级分类的数据)
查询某个一级分类:
1.查询所有一级分类:
* 同时查询到每个一级分类下所属的二级分类
1.1.3商品模块:
查询热门商品:
1.查询热门商品:(限制个数10个)
查询最新商品:
1.查询最新商品:(限制个数10个)
查询某个分类商品:
1.根据分类的ID进行查询商品:
查询某个二级分类商品:
1.根据二级分类的ID进行查询商品:
查询某个商品信息:
1.根据商品ID进行查询商品:
1.1.4购物模块:
添加到购物车:
1.将商品信息添加到购物车中:
从购物车中移除商品:
1.将商品信息从购物车中移除:
清空购物车:
1.将所有的商品信息从购物车中移除:
1.1.5订单模块:
生成订单:
1.将购物车中的信息存入到数据库(生成订单).
* 清空购物车:
为订单付款:
1.在线支付功能:
2.修改订单状态:
3.修改订单的信息:(收货人,联系方式,送货地址)
查询我的订单:
1.根据用户ID查询订单.
查询某个订单详情:
1.根据订单ID进行查询:
1.2后台需求分析:
1.2.1用户模块:
添加用户:
添加用户到数据库
修改用户:
修改用户到数据库
删除用户:
删除用户
查询用户:
查询用户(带分页)
1.2.2一级分类:
添加一级分类:
添加一级分类:
修改一级分类:
修改一级分类
删除一级分类:
删除一级分类:
* 级联删除二级分类:
查询一级分类:
查询一级分类:
1.2.3二级分类:
添加二级分类:
二级分类需要有所属一级分类:
修改二级分类:
修改二级分类所属的一级分类:
删除二级分类:
删除二级分类:
查询所有二级分类:
查询二级分类(带分页)
1.2.4商品模块:
添加商品:
1.添加商品所属二级分类:
2.上传商品图片:
修改商品:
1.修改商品二级分类:
2.修改商品图片:
删除商品:
1.删除商品:
查询商品:
1.商品查询:(带分页)
1.2.5订单模块:
查询订单:
查询所有订单:(带分页)
* 异步加载订单项:
1.3页面设计:
使用静态页面就OK
1.4数据库设计:
用户表:
* 用户ID
* 用户名:
* 密码:
* 真实姓名:
* 邮箱:
* 地址:
* 电话:
* 用户状态:	0未激活  1已经激活
* 激活码:

一级分类:
* 一级分类ID
* 一级分类名称

二级分类:
* 二级分类ID:
* 二级分类名称:
* 一级分类ID(外键指向一级分类主键ID)

商品表:
* 商品ID:
* 商品名称:
* 商品商城价格:
* 商品市场价格:
* 商品描述:
* 商品图片:(路径)
* 二级分类ID(外键指向二级分类主键ID)

订单表:
* 订单ID
* 订单时间:
* 订单金额:
* 订单状态:
* 订单地址:
* 订单电话:
* 订单收货人:
* 用户ID(外键指向用户表的主键ID)

订单项表:(需要参与到业务逻辑中)
* 主键ID
* 商品ID
* 订单ID
* 数量
* 小计

后台用户表:
* 用户名:
* 密码:
1.5编码实现:
1.5.1搭建开发环境:
SSH整合:
1.创建一个web工程:
2.引入jar包和配置文件:
* struts2:
* jar包:
struts-2.3.15.3\apps\struts2-blank.war\WEB-INF\lib\*.jar
struts-2.3.15.3\lib\struts2-json-plugin-2.3.15.3.jar
struts-2.3.15.3\lib\struts2-spring-plugin-2.3.15.3.jar
* 配置文件:
* web.xml
 <!-- 配置Struts2的核心过滤器 -->
 <filter>
 	<filter-name>struts2</filter-name>
 	<filter-class>
org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter
</filter-class>
 </filter>
 
 <filter-mapping>
 	<filter-name>struts2</filter-name>
 	<url-pattern>/*</url-pattern>
 </filter-mapping>
* struts.xml
* Spring:
* jar包:
Spring3.2 开发最基本jar包
spring-beans-3.2.0.RELEASE.jar
spring-context-3.2.0.RELEASE.jar
spring-core-3.2.0.RELEASE.jar
spring-expression-3.2.0.RELEASE.jar
com.springsource.org.apache.commons.logging-1.1.1.jar
com.springsource.org.apache.log4j-1.2.15.jar
AOP开发
spring-aop-3.2.0.RELEASE.jar
spring-aspects-3.2.0.RELEASE.jar
com.springsource.org.aopalliance-1.0.0.jar
com.springsource.org.aspectj.weaver-1.6.8.RELEASE.jar
Spring Jdbc开发
spring-jdbc-3.2.0.RELEASE.jar
spring-tx-3.2.0.RELEASE.jar
Spring事务管理
spring-tx-3.2.0.RELEASE.jar
Spring整合其他ORM框架
spring-orm-3.2.0.RELEASE.jar
Spring在web中使用
spring-web-3.2.0.RELEASE.jar
Spring整合Junit测试
spring-test-3.2.0.RELEASE.jar
* 配置文件:
* web.xml
 <!-- 配置Spring的核心监听器 -->
 <listener>
 	<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
 </listener>
 
 <context-param>
 	<param-name>contextConfigLocation</param-name>
 	<param-value>classpath:applicationContext.xml</param-value>
 </context-param>
* applicationContext.xml
* log4j.properties

* Hibernate:
* jar包:
* hibernate-distribution-3.6.10.Final\hibernate3.jar
* hibernate-distribution-3.6.10.Final\lib\required\*.jar
* hibernate-distribution-3.6.10.Final\lib\jpa\*.jar
* slf4j-log4j整合的jar包 :
* 数据库驱动:
* 连接池:(c3p0连接池)
* 配置文件:
* 没有hibernate的核心配置文件的方式整合:
* 映射文件:

3.配置基本配置信息:
* C3P0连接池:
* 引入外部属性文件:
* jdbc.properties
* 配置连接池:
	<!-- 配置连接池: -->
	<!-- 引入外部属性文件 -->
	<context:property-placeholder location="classpath:jdbc.properties"/>
	<!-- 配置C3P0连接池: -->
	<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
		<property name="driverClass" value="${jdbc.driver}"/>
		<property name="jdbcUrl" value="${jdbc.url}"/>
		<property name="user" value="${jdbc.user}"/>
		<property name="password" value="${jdbc.password}"/>
	</bean>

* Hibernate相关信息:
	<!-- Hibernate的相关信息 -->
	<bean id="sessionFactory" class="org.springframework.orm.hibernate3.LocalSessionFactoryBean">
		<!-- 注入连接池 -->
		<property name="dataSource" ref="dataSource"/>
		<!-- 配置Hibernate的其他的属性 -->
		<property name="hibernateProperties">
			<props>
				<prop key="hibernate.dialect">org.hibernate.dialect.MySQLDialect</prop>
				<prop key="hibernate.show_sql">true</prop>
				<prop key="hibernate.format_sql">true</prop>
				<prop key="hibernate.connection.autocommit">false</prop>
				<prop key="hibernate.hbm2ddl.auto">update</prop>
			</props>
		</property>
		<!-- 配置Hibernate的映射文件 -->
		
	</bean>

* 事务管理:
	<!-- 事务管理: -->
	<!-- 事务管理器 -->
	<bean id="transactionManager" class="org.springframework.orm.hibernate3.HibernateTransactionManager">
		<property name="sessionFactory" ref="sessionFactory"/>
	</bean>
	
	<!-- 开启注解事务 -->
	<tx:annotation-driven transaction-manager="transactionManager"/>
1.5.2引入页面:
1.引入静态页面:
* 在WEB-INF/jsp/
* 将静态页面复制到jsp下.
* 将css/js 放到 WebRoot下.
1.5.3访问首页:
建包的格式:
* cn.itcast.shop.index
* action
* cn.itcast.shop.user
* action
* service
* dao
* vo
1.编写Action:
* IndexAction
2.改造JSP
* 将css和image的相对路径改变成绝对路径 :
* ./css 替换成 ${pageContext.request.contextPath}/css
3.配置Action
* 配置applicationContext.xml
<!-- 首页访问的Action -->
	<bean id="indexAction" class="cn.itcast.shop.index.action.IndexAction" scope="prototype">
	
	</bean>
* 配置struts.xml
<!-- 配置首页访问的Action -->
		<action name="index" class="indexAction">
			<result name="index">/WEB-INF/jsp/index.jsp</result>
		</action>
1.5.4用户模块:注册
跳转到注册页面:
1.点击注册链接(添加到一个Action中,从Action中完成页面转向).
* 修改注册的访问路径:
${ pageContext.request.contextPath }/user_registPage.action
* 编写Action:
* 配置Action:
* 配置applicationContext.xml
	<!-- 用户模块的Action -->
	<bean id="userAction" class="cn.itcast.shop.user.action.UserAction" scope="prototype">
	
	</bean>
* 配置struts.xml
		<!-- 配置用户模块的Action -->
		<action name="user_*" class="userAction" method="{1}">
			<result name="registPage">/WEB-INF/jsp/regist.jsp</result>
		</action>
* 修改regist.jsp:
* ./css 替换成 ${pageContext.request.contextPath}/css
用户注册校验:
表创建:
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `code` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

前台校验:
* JS校验:
* 事件触发:onsubmit
* 校验:
	function checkForm(){
		// 校验用户名:
		// 获得用户名文本框的值:
		var username = document.getElementById("username").value;
		if(username == null || username == ''){
			alert("用户名不能为空!");
			return false;
		}
		// 校验密码:
		// 获得密码框的值:
		var password = document.getElementById("password").value;
		if(password == null || password == ''){
			alert("密码不能为空!");
			return false;
		}
		// 校验确认密码:
		var repassword = document.getElementById("repassword").value;
		if(repassword != password){
			alert("两次密码输入不一致!");
			return false;
		}
	}

使用AJAX完成用户名是否存在异步校验:
1.事件触发:
* onblur
2.编写AJAX代码:
* 项Action中提交:传递username参数
3.编写Action
* 接收username:模型驱动接收.
* 编写实体类
* User
* User.hbm.xml
* 配置到Spring中.
4.编写DAO
* 继承HibernateDaoSupport
* 在配置中注入sessionFactory
5.编写Service:
* 注入UserDao
* 事务管理:

用户注册:后台数据校验:
1.在Action所在包下创建一个校验的xml.
* 创建一个与Action类名-validation.xml(针对所有方法进行校验,只需要针对regist方法进行校验)
* UserAction-user_regist-validation.xml
<validators>
	<!-- 校验用户名 -->
	<field name="username">
		<field-validator type="requiredstring">
			<message>用户名不能为空!</message>
		</field-validator>
	</field>
	
	<!-- 校验用户名 -->
	<field name="password">
		<field-validator type="requiredstring">
			<message>密码不能为空!</message>
		</field-validator>
	</field>
	
	<!-- 校验邮箱 -->
	<field name="email">
		<field-validator type="email">
			<message>邮箱格式不正确!</message>
		</field-validator>
	</field>
</validators>

2.配置INPUT逻辑视图:
用户注册:
1.将数据保存到数据库 :
* 完善user对象.
2.发送激活邮件:
* 电子邮箱:
* jt@itcast.cn
* 邮件服务器:
* 邮件协议:
* smtp	:发送邮件协议
* pop	:接收邮件协议
* 邮箱发送全过程:
* 搭建邮箱服务器:
* 安装邮箱服务器:
* 修改域名:
* 工具---->服务器设置---->shop.com
* 注册账号:
* 账号---->新建账号
* 安装客户端软件:(接收和发送邮件.)
* outlook 和 foxmail
* 安装foxmail:
* 配置:
* 配置发送邮件服务器:
* localhost
* 配置接收邮件服务器:
* localhost
* 编码实现发送邮件:
* 复制mail.jar和activation.jar
* 删除EE5.0中自带发送邮件jar包:
* windows---->preferences--->lib 
* 编写代码完成发送邮件:
1.5.5用户激活:
1.在邮箱客户端中点击激活链接:
* 根据传递激活码进行用户查询.
* 如果用户不为空:
* 修改用户状态 改为1
* 如果用户为空:
* 激活码被篡改了.
1.5.6用户登录:
跳转到登录页面:
1.修改登录链接:
* 改为action路径:
* 编写Action中方法:
* 转向:
用户登录:
1.接收用户名和密码:(模型驱动会自动接收)
2.调用userService进行查询:
* 用户名和密码及状态.
* 查询到:登录成功:
* 将用户的信息存入到session
* 跳转到首页
* 没查询到:登录失败:
* 跳转回登录页面:
1.5.7用户退出:
点击退出的链接:
* 提交到Action---销毁session
* 页面跳转(首页)
1.5.8验证码程序:(注册)
1.编写Action:
* 验证码Action:
* 完成配置:
2.在页面中写<img src=”验证码Action路径”>
3.编写JS,看不清换一张
4.提交到Action,在Action中进行比较.
* 如果一致提交成功
* 如果不一致回到注册页面
