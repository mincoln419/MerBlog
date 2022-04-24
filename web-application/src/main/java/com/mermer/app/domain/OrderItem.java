/**
 * @packageName : com.mermer.app.domain
 * @fileName : OrderItem.java 
 * @author : Mermer 
 * @date : 2022.04.11 
 * @description :
 * =========================================================== 
 * DATE AUTHOR NOTE 
 * ----------------------------------------------------------- 
 * 2022.04.11 Mermer 최초 생성
 */
package com.mermer.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import com.mermer.app.domain.item.Item;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/* 
 * @description: 
 */
@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orderItem")
public class OrderItem {
	
	@Id @GeneratedValue
	@Column(name = "order_item_id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="item_id")
	private Item item;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Order order;
	
	private int orderPrice;
	
	private int count;
	
	//== 생성 매서드 ==//
	public static OrderItem createOrderItem(Item item, int orderPrice, int count) throws Exception {
		OrderItem orderItem = new OrderItem();
		orderItem.setItem(item);
		orderItem.setOrderPrice(orderPrice);
		orderItem.setCount(count);
		
		item.removeStock(count);
		return orderItem;
	}
	
	
	//== 비즈니스 로직 ==/
	//취소 후 재고 원복
	public void cancel() {
		getItem().addStock(count);
	}

	//== 조회 로직 ==/
	//주문가격 전체 조회
	public int getTotalPrice() {
		return getOrderPrice() * getCount();
	}
	
}
