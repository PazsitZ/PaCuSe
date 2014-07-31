package hu.pazsitz.pacuse.tests.annotations;

/**
 * DTAInputHandling.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public enum DTAInputHandling {
	AUTO,
	
	SELECT_ALL,
	SELECT_BY_VALUE,
	SELECT_BY_TEXT,
	SELECT_BY_INDEX,
	
	LINK_BY_TEXT,
	LINK_BY_HREF
	;
}
