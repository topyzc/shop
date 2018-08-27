package com.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shop.order.vo.Order;
import com.shop.order.vo.OrderItem;
import com.shop.utils.PageHibernateCallback;

public class OrderDao extends HibernateDaoSupport {

	// 保存订单
	public void save(Order order) {
		System.out.println(order);
		this.getHibernateTemplate().save(order);
	}

	// 我的订单的个数统计
	public int findByCountUid(Integer uid) {
		String hql = "select count(*) from Order o where o.user.uid=?";
		List<Long> list = this.getHibernateTemplate().find(hql, uid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// 我的订单的查询
	public List<Order> findByPageUid(Integer uid, Integer begin, int limit) {
		String hql = "from Order o where o.user.uid=? order by ordertime desc";
		List<Order> list = (List<Order>) this.getHibernateTemplate()
				.execute(new PageHibernateCallback<>(hql, new Object[] { uid }, begin, limit));
		return list;
	}

	public Order findByOid(String oid) {

		return (Order) this.getHibernateTemplate().get(Order.class, oid);
	}

	// DAO层修改订单的方法:
	public void update(Order currOrder) {
		this.getHibernateTemplate().update(currOrder);
	}

	// DAO中统计订单个数的方法
	public int findCount() {
		String hql = "select count(*) from Order";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// DAO中分页查询订单的方法
	public List<Order> findByPage(int begin, int limit) {
		String hql = "from Order order by ordertime desc";
		List<Order> list = (List<Order>) this.getHibernateTemplate()
				.execute(new PageHibernateCallback<Order>(hql, null, begin, limit));
		return list;
	}

	// DAo中根据订单id查询订单项
	public List<OrderItem> findOrderItem(String oid) {
		String hql = "from OrderItem oi where oi.order.oid = ?";
		List<OrderItem> list = this.getHibernateTemplate().find(hql, oid);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

}
