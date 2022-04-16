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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.mermer.app.domain.item.Item;

import lombok.Builder;
import lombok.Data;

/* 
 * @description: 
 */
@Entity
@Data
@Builder
public class OrderItem {
	
	@Id @GeneratedValue
	@Column(name = "order_item_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name ="item_id")
	private Item item;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	private int orderPrice;
	
	private int count;
	
}
