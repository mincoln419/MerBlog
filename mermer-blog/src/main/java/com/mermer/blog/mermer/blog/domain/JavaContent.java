package com.mermer.blog.mermer.blog.domain;

import java.sql.Clob;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class JavaContent {
	
	@Id @GeneratedValue
	@Column(name = "content_id")
	private Long id;
	
	private String title;
	
	private Clob content;
	
	private LocalDateTime created_at;
	
	private LocalDateTime updated_at;
	
	@OneToMany(mappedBy = "content", cascade = CascadeType.ALL)
	private List<UploadFile> files;
}
