/**
 * @packageName : com.mermer.app.entity
 * @fileName : Member.java 
 * @author : Mermer 
 * @date : 2022.04.10 
 * @description :
 * =========================================================== 
 * DATE AUTHOR NOTE 
 * ----------------------------------------------------------- 
 * 2022.04.10 Mermer 최초 생성
 */
package com.mermer.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/* 
 * @description: 
 */
@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Member {

	@Id @GeneratedValue
	private Long id;
	private String name;
}
