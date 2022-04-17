/**
 * @packageName : com.mermer.app.config
 * @fileName : CustomPhysicalNamingStrategy.java 
 * @author : Mermer 
 * @date : 2022.04.17 
 * @description :
 * =========================================================== 
 * DATE AUTHOR NOTE 
 * ----------------------------------------------------------- 
 * 2022.04.17 Mermer 최초 생성
 */
package com.mermer.app.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.context.annotation.Configuration;

/* 
 * @description: 
 */
@Configuration
public class CustomPhysicalNamingStrategy implements PhysicalNamingStrategy {

	@Override
	public Identifier toPhysicalCatalogName(final Identifier name, final JdbcEnvironment jdbcEnvironment) {
		return convertToSnakeCase(name);
	}


	@Override
	public Identifier toPhysicalSchemaName(final Identifier name, final JdbcEnvironment jdbcEnvironment) {
		return convertToSnakeCase(name);
	}

	@Override
	public Identifier toPhysicalTableName(final Identifier name, final JdbcEnvironment jdbcEnvironment) {
		return convertToSnakeCase_table(name);
	}

	@Override
	public Identifier toPhysicalSequenceName(final Identifier name, final JdbcEnvironment jdbcEnvironment) {
		return convertToSnakeCase(name);
	}

	@Override
	public Identifier toPhysicalColumnName(final Identifier name, final JdbcEnvironment jdbcEnvironment) {
		return convertToSnakeCase(name);
	}
	
	/**
	 * @method convertToSnakeCase
	 * @param name
	 * @return
	 * Identifier
	 * @description 
	 */
	private Identifier convertToSnakeCase_table(final Identifier name) {
		final String regex = "([a-z])([A-Z])";
        final String replacement = "$1_$2";
        String newName = "";
        if(name !=null) {
        	newName = "t_" + name.getText()
          .replaceAll(regex, replacement)
          .toLowerCase();
        }
        else newName = "";
        return Identifier.toIdentifier(newName);
	}

	
	/**
	 * @method convertToSnakeCase
	 * @param name
	 * @return
	 * Identifier
	 * @description 
	 */
	private Identifier convertToSnakeCase(final Identifier name) {
		final String regex = "([a-z])([A-Z])";
        final String replacement = "$1_$2";
        String newName = "";
        if(name !=null) {
        	newName = name.getText()
          .replaceAll(regex, replacement)
          .toLowerCase();
        }
        else newName = "";
        return Identifier.toIdentifier(newName);
	}

}
