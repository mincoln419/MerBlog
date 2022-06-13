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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.mermer.app.domain.Category;
import com.mermer.app.exception.NotEnoughStockException;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/* 
 * @description: 
 */
@BatchSize(size = 100)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="item")
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class Item{

	@Id
	@Column(name = "item_id")
	private Long id;
	
	@CreatedDate
	private LocalDateTime create_at;//id를 임의값으로 할 경우 날짜 데이터로 체크하면됨
	
	private String name;
	
	private int price;
	
	private int stockQuantity;
	
	@ManyToMany(mappedBy = "items")
	private List<Category> categories = new ArrayList<>();
	
	
	
	//== 비즈니스 로직 ==//
	// stock 증가
	public void addStock(int quantity) {
		this.stockQuantity += quantity;
	}
	
	// stcok 감소 
	public void removeStock(int quantity) throws Exception {
		
		int restStock = this.stockQuantity - quantity;
		if(restStock < 0) {
			throw new NotEnoughStockException("need more stock");
		}
		this.stockQuantity = restStock;
	}
	
}
