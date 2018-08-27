package com.shop.cart.vo;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
	
	//购物项的集合:key：pid value:购物项
	private Map<Integer,CartItem> map=new LinkedHashMap<>();
	
	public Collection<CartItem> getCartItems(){
		return map.values();
		
	}
	//总计
	private double total;
	
	public double getTotal() {
		return total;
	}
	//购物车功能
	//1.将购物项添加到购物车
	public void addCart(CartItem cartItem) {
		//判断购物车中是否存在该购物项
		//1.获得商品的id
		Integer pid=cartItem.getProduct().getPid();
		//判断购物车中是否已经存在购物项
		if(map.containsKey(pid)) {
			//存在
			CartItem _cartItem=map.get(pid);//获得购物车上原来的购物项
			_cartItem.setCount(cartItem.getCount()+_cartItem.getCount());
		}else {
			//不存在
			map.put(pid, cartItem);
		}
		//设置总计
		total+=cartItem.getSubtotal();
		
	}
	//2.从购物车移除购物项
	public void removeCart(Integer pid) {
		//将购物项移除
		CartItem cartItem=map.remove(pid);
		//总计=总计-移除的购物项小计
		total-=cartItem.getSubtotal();
	}
	//3.清空购物车
    public void clearCart() {
    	//将所有的购物项清空
    	map.clear();
    	
    	//将总计设置为0
    	total=0;
    }
	

}
