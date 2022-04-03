/**
 * @packageName : com.mermer.blog.mermer.blog.post.entity
 * @fileName : Post.java 
 * @author : Mermer 
 * @date : 2022.02.06 
 * @description :
 * =========================================================== 
 * DATE AUTHOR NOTE 
 * ----------------------------------------------------------- 
 * 2022.02.06 Mermer 최초 생성
 */
package com.mermer.blog.mermer.blog.post.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Post {

	@Id @GeneratedValue
	private Long id;
	
	private String title;
	
	private PostType postType;
	
}
