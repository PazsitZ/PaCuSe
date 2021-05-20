package hu.pazsitz.pacuse.tests.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * DataTableAttributes.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DataTableAttributes {
	String[] name();
	
	int priority() default 0;
	
	/**
	 * if multiple select allowed it considers "," as separator chars in value string
	 * @return
	 */
	boolean allowMultiSelect() default false;
	
	/**
	 * If you want to specify the Input Attribute handling
	 * @return
	 */
	DTAInputHandling inputHandling() default DTAInputHandling.AUTO;
	
	String attribute() default "";
	
}
