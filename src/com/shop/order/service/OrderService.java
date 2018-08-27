package com.shop.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.shop.order.dao.OrderDao;
import com.shop.order.vo.Order;
import com.shop.order.vo.OrderItem;
import com.shop.utils.PageBean;

@Transactional
public class OrderService {

	// 注入orderDao
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	// 保存订单
	public void save(Order order) {

		orderDao.save(order);
	}

	// 查询我的订单
	public PageBean<Order> findByPageUid(Integer uid, Integer page) {

		PageBean<Order> pageBean = new PageBean<>();
		// 设置当前的页数
		pageBean.setPage(page);
		// 设置每页的显示数
		int limit = 5;
		pageBean.setLimit(limit);
		// 设置总记录数
		int totalCount = 0;
		totalCount = orderDao.findByCountUid(uid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		Integer totalPage = null;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置每页显示的集合
		Integer begin = (page - 1) * limit;
		List<Order> list = orderDao.findByPageUid(uid, begin, limit);
		pageBean.setList(list);
		return pageBean;

	}

	// 根据订单的id查询订单
	public Order findByOid(String oid) {

		return orderDao.findByOid(oid);
	}

	// 业务层修改订单的方法:
	public void update(Order currOrder) {
		orderDao.update(currOrder);
	}

	// 业务层查询所有订单方法
		public PageBean<Order> findAll(Integer page) {
			PageBean<Order> pageBean = new PageBean<Order>();
			// 设置参数
			pageBean.setPage(page);
			// 设置每页显示的记录数:
			int limit = 10;
			pageBean.setLimit(limit);
			// 设置总记录数
			int totalCount = orderDao.findCount();
			pageBean.setTotalCount(totalCount);
			// 设置总页数
			int totalPage = 0;
			if(totalCount % limit == 0){
				totalPage = totalCount / limit;
			}else{
				totalPage = totalCount / limit + 1;
			}
			pageBean.setTotalPage(totalPage);
			// 设置每页显示数据集合
			int begin = (page - 1) * limit;
			List<Order> list = orderDao.findByPage(begin,limit);
			pageBean.setList(list);
			return pageBean;
		}

		// 业务层查询订单项的方法
		public List<OrderItem> findOrderItem(String oid) {
			return orderDao.findOrderItem(oid);
		}

}
