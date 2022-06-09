package com.mermer.blog.mermer.blog.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Programing {

	@Id @GeneratedValue
	@Column(name = "programing_id")
	private Long id;
	
	private String title;
	
	private String content;
	
	private LocalDateTime create_at;
	
	private LocalDateTime update_at;
	
	private String filename;
	
	private String filepath;
	
	@Embedded
	@OneToMany(mappedBy =  "programing")
	private List<SourceCode> sourceCode;
}
