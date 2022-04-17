/**
 * @packageName : com.mermer.app.domain
 * @fileName : Album.java 
 * @author : Mermer 
 * @date : 2022.04.11 
 * @description :
 * =========================================================== 
 * DATE AUTHOR NOTE 
 * ----------------------------------------------------------- 
 * 2022.04.11 Mermer 최초 생성
 */
package com.mermer.app.domain.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;

/* 
 * @description: 
 */
@Entity
@DiscriminatorValue("A")
@Data
public class Album extends Item{

	private String artist;
	
	private String etc;
}