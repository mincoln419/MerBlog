package com.mermer.blog.mermer.blog.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class UploadFile {

	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "content_id")
	private JavaContent content;
	
	private String filePath;
	
	private Integer fileSize;
	
	private LocalDateTime created_at;
	
	private LocalDateTime updated_at;
	
}
