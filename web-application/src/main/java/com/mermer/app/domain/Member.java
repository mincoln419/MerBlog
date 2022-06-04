/**
 * @packageName : com.mermer.app.domain
 * @fileName : Member.java 
 * @author : Mermer 
 * @date : 2022.04.11 
 * @description :
 * =========================================================== 
 * DATE AUTHOR NOTE 
 * ----------------------------------------------------------- 
 * 2022.04.11 Mermer 최초 생성
 */
package com.mermer.app.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/* 
 * @description: 
 */
@Entity
@Data
@NoArgsConstructor
@Table(name="member")
@ToString(of = {"id", "name", "age"})
public class Member {

	@Id @GeneratedValue
	@Column(name = "member_id")
	private Long id;
	
	private String name;
	
	private Integer age;
	
	@Embedded
	private Address address;
	
	@JsonIgnore
	@OneToMany(mappedBy = "member")
	private List<Order> orders = new ArrayList<Order>();
	
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;
	
	public Member(String name) {
		this.name = name;
	}
	
	public void changeTeam(Team team) {
		this.team = team;
		team.getMembers().add(this);
	}

	public Member(String name, int age, Team team) {
		this.name = name;
		this.age = age;
		if(team !=null) {
			changeTeam(team);
		}
	}
}
