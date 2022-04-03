/**
 * @packageName : org.springframework.samples.petclinic.aspect
 * @fileName : LogExecutionTime.java 
 * @author : Mermer 
 * @date : 2022.04.03 
 * @description :
 * =========================================================== 
 * DATE AUTHOR NOTE 
 * ----------------------------------------------------------- 
 * 2022.04.03 Mermer 최초 생성
 */
package org.springframework.samples.petclinic.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogExecutionTime {

}
