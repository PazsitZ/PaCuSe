package hu.pazsitz.pacuse.tests.cucumber.featuretables;

import hu.pazsitz.pacuse.pages.AbstractPage;

/**
 * PageFieldTableMapper.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class PageFieldTableMapper {
	private final IMapperAction action;
	
	
	public PageFieldTableMapper(IMapperAction action) {
		this.action = action;
	}
	
	public boolean mapFields(AbstractPage page, String key, String value) {
		boolean result = false;
		
//		boolean result = element.doAction(value);
		
    	return result;
    }
}
