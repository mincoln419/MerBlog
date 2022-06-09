package com.mermer.blog.mermer.blog.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Embeddable
@Entity
@Data
public class SourceCode {

	@Id @GeneratedValue
	@Column(name =  "source_id")
	private Long id;
	private String codeLine;
	
	@ManyToOne
	@JoinColumn(name = "programing_id")
	private Programing programing;
	
}
