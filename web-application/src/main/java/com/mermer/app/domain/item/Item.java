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

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.mermer.app.domain.Category;
import com.mermer.app.exception.NotEnoughStockException;

import lombok.Data;

/* 
 * @description: 
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
@Data
@Table(name="item")
public abstract class Item {

	@Id @GeneratedValue
	@Column(name = "item_id")
	private Long id;
	
	private String name;
	
	private int price;
	
	private int stockQauntity;
	
	@ManyToMany(mappedBy = "items")
	private List<Category> categories = new ArrayList<>();
	
	
	
	//== 비즈니스 로직 ==//
	// stock 증가
	public void addStock(int quantity) {
		this.stockQauntity += quantity;
	}
	
	// stcok 감소 
	public void removeStock(int quantity) throws Exception {
		
		int restStock = this.stockQauntity - quantity;
		if(restStock < 0) {
			throw new NotEnoughStockException("need more stock");
		}
		this.stockQauntity = restStock;
	}
	
	
}
