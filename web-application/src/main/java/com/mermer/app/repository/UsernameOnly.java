package com.mermer.app.repository;

import org.springframework.beans.factory.annotation.Value;

public interface UsernameOnly  {
	@Value("#{target.name + ' ' + target.age}")
	String getName();
}
