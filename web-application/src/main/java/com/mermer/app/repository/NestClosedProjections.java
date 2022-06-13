package com.mermer.app.repository;

public interface NestClosedProjections {

	String getName();
	
	TeamInfo getTeam();
	
	interface TeamInfo{
		String getName();
	}
}
