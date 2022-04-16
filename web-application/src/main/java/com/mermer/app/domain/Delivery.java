/**
 * @packageName : com.mermer.app.domain
 * @fileName : Delivery.java 
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
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

/* 
 * @description: 
 */
@Entity
@Data
public class Delivery {

	@Id @GeneratedValue
	@Column(name="delivery_id")
	private Long id;
	
	@OneToOne(mappedBy = "delivery")
	private Order order;
	
	@Embedded
	private Address address;
	
	@Enumerated(EnumType.STRING)
	private DeliveryStatus status;//READY, COMP
}
