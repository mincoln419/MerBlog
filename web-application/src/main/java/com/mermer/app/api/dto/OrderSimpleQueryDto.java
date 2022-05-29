package com.mermer.app.api.dto;

import java.time.LocalDateTime;

import com.mermer.app.domain.Address;
import com.mermer.app.domain.Order;
import com.mermer.app.domain.OrderStatus;

import lombok.Data;

@Data
public class OrderSimpleQueryDto {

	private Long orderId;
	private String name;
	private LocalDateTime orderDate;
	private OrderStatus orderStatus;
	private Address address;
	
	public OrderSimpleQueryDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address) {
		this.orderId = orderId;
		this.name = name;//LAZY 초기화 
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.address = address;
	}
	
}
