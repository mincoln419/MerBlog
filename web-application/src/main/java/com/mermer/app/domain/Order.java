/**
 * @packageName : com.mermer.app.domain
 * @fileName : Order.java 
 * @author : Mermer 
 * @date : 2022.04.11 
 * @description :
 * =========================================================== 
 * DATE AUTHOR NOTE 
 * ----------------------------------------------------------- 
 * 2022.04.11 Mermer 최초 생성
 */
package com.mermer.app.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

/* 
 * @description: 
 */
@Entity
@Table(name = "orders")
@Data
@Builder
public class Order {
	
	@Id @GeneratedValue
	@Column(name = "order_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;
	
	@OneToMany(mappedBy = "order")
	private List<OrderItem> orderItems = new ArrayList<>();
	
	private Delivery delivery;
	
	private LocalDateTime orderDate;//주문시간
	
	private OrderStatus status;//주문상태 [ORDER, CANCEL]
	
	
}
