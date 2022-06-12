package com.mermer.app.domain;

import lombok.Data;

@Data
public class MemberDto {

	private Long id;
	private String userName;
	private String teamName;

	public MemberDto(Long id, String userName, String teamName) {
		this.id = id;
		this.userName = userName;
		this.teamName = teamName;
	}

	public MemberDto(Member member) {
		this.id = member.getId();
		this.userName = member.getName();
		this.teamName = member.getTeam() == null ? null : member.getTeam().getName();
	}
	
}
