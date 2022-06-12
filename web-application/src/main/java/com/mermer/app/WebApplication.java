package com.mermer.app;

import java.util.Optional;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

@SpringBootApplication
@EnableJpaAuditing
//@EnableJpaRepositories(basePackages = {""})
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
	
	
	@Bean
	public Hibernate5Module hibernate5Module() {
		Hibernate5Module module =  new Hibernate5Module();
		//이거는 쓰면 안된다...
		//module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, true);
		return module;
	}
	
	@Bean
	public AuditorAware<String> auditorProvider(){
		return () -> Optional.of(UUID.randomUUID().toString());
	}
}
