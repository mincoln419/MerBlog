/**
 * @packageName : com.mermer.app.domain
 * @fileName : Item.java 
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

import lombok.Builder;
import lombok.Data;

/* 
 * @description: 
 */
@Entity
@Data
public abstract class Item {

	@Id @GeneratedValue
	@Column(name = "item_id")
	private Long id;
	
	private String name;
	
	private int price;
	
	private int stockQty;
}
