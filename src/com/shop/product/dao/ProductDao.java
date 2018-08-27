package com.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
/**
 * 商品持久层
 */
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shop.product.vo.Product;
import com.shop.utils.PageHibernateCallback;

public class ProductDao extends HibernateDaoSupport{

	//首页上的热门商品的查询
	public List<Product> findHot() {
		//使用离线条件查询
		DetachedCriteria criteria=DetachedCriteria.forClass(Product.class);
		criteria.add(Restrictions.eq("is_hot", 1));
		//倒序排序
		criteria.addOrder(Order.desc("pdate"));
		//执行查询
		List<Product> findByCriteria = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return findByCriteria;
	}

	//最新商品的查询
	public List<Product> findNew() {
		//使用离线条件查询
		DetachedCriteria criteria=DetachedCriteria.forClass(Product.class);
		criteria.add(Restrictions.eq("is_hot", 0));
		//倒序排序
		criteria.addOrder(Order.desc("pdate"));
		//执行查询
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return list;
	}

	public Product findByPid(Integer pid) {
		return (Product) this.getHibernateTemplate().get(Product.class,pid);
	
	}

	// 根据分类id查询商品的个数
		public int findCountCid(Integer cid) {
			String hql = "select count(*) from Product p where p.categorySeconds.category.cid = ?";
			List<Long> list = this.getHibernateTemplate().find(hql,cid);
			if(list != null && list.size() > 0){
				return list.get(0).intValue();
			}
			return 0;
		}

		// 根据分类id查询商品的集合
		public List<Product> findByPageCid(Integer cid, int begin, int limit) {
			// select p.* from category c,categorysecond cs,product p where c.cid = cs.cid and cs.csid = p.csid and c.cid = 2
			// select p from Category c,CategorySecond cs,Product p where c.cid = cs.category.cid and cs.csid = p.categorySecond.csid and c.cid = ?
			String hql = "select p from Product p join p.categorySeconds cs join cs.category c where c.cid = ?";
			// 分页另一种写法:
			Object  list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{cid}, begin, limit));
			if(list != null && ((List<Product>) list).size() > 0){
				return (List<Product>) list;
			}
			return null;
			
		}

		//根据二级分类查询商品个数
		public int findCountCsid(Integer csid) {
			String hql = "select count(*) from Product p where p.categorySeconds.csid = ?";
			List<Long> list = this.getHibernateTemplate().find(hql,csid);
			if(list != null && list.size() > 0){
				return list.get(0).intValue();
			}
			return 0;
		}

		//根据二级分类查询商品信息
		public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
			String hql = "select p from Product p join p.categorySeconds cs where cs.csid = ?";
			// 分页另一种写法:
			List<Product>  list =  (List<Product>) this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin, limit));
			if(list != null &&  list.size() > 0){
				return  list;
			}
			return null;
		}


		// 后台统计商品个数的方法
		public int findCount() {
			String hql = "select count(*) from Product";
			List<Long> list = this.getHibernateTemplate().find(hql);
			if(list != null && list.size() > 0){
				return list.get(0).intValue();
			}
			return 0;
		}

		// 后台查询所有商品的方法
		public List<Product> findByPage(int begin, int limit) {
			String hql = "from Product order by pdate desc";
			List<Product> list =  (List<Product>) this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, null, begin, limit));
			if(list != null && list.size() > 0){
				return list;
			}
			return null;
		}

		// DAO中的保存商品的方法
		public void save(Product product) {
			this.getHibernateTemplate().save(product);
		}

		// DAO中的删除商品的方法
		public void delete(Product product) {
			this.getHibernateTemplate().delete(product);
		}

		public void update(Product product) {
			this.getHibernateTemplate().update(product);
		}
}
