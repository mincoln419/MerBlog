/**
 * @packageName : com.mermer.app.domain.item
 * @fileName : Item.java 
 * @author : Mermer 
 * @date : 2022.04.16 
 * @description :
 * =========================================================== 
 * DATE AUTHOR NOTE 
 * ----------------------------------------------------------- 
 * 2022.04.16 Mermer 최초 생성
 */
package com.mermer.app.domain.item;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Data;

/* 
 * @description: 
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
@Data
public abstract class Item {

	@Id @GeneratedValue
	@Column(name = "item_id")
	private Long id;
	
	private String name;
	
	private int price;
	
	private int stockQauntity;
	
	
}
