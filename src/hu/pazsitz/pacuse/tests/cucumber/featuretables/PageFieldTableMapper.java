package hu.pazsitz.pacuse.tests.cucumber.featuretables;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

import hu.pazsitz.pacuse.pages.AbstractPage;
import hu.pazsitz.pacuse.tests.annotations.TableName;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.mapperactions.BlankAction;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.mapperactions.IMapperAction;

/**
 * PageFieldTableMapper.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class PageFieldTableMapper {
	private final IMapperAction action;
	
	public PageFieldTableMapper() {
		this.action = new BlankAction();
	}
			
	
	public PageFieldTableMapper(IMapperAction action) {
		this.action = action;
	}
	
	/**
	 * Evaluates the given Action on the mapped field
	 * @param page
	 * @param fieldName
	 * @param value
	 * @return boolean
	 */
	public boolean mapFields(AbstractPage page, String fieldName, String value) {
		boolean result = false;
		
		WebElement element = lookUpForField(page, fieldName);
		result = action.doAction(element, value);
		
    	return result;
    }

	/**
	 * Usually use with BlankAction, for just simple element mapping to field names
	 * @param page
	 * @param fields
	 * @return Map<String, WebElement>
	 */
	public Map<String, WebElement> mapFields(AbstractPage page, List<String> fields) {
		Map<String, WebElement> result = new HashMap<>();
		
		for (String field : fields) {
			WebElement element = lookUpForField(page, field);
			action.doAction(element, field);
		}
//		
    	return result;
    }
	
	private WebElement lookUpForField(AbstractPage page, String fieldName) {
		for (Field field : page.getClass().getDeclaredFields()) {
			if (!isFieldAnnotationMatches(fieldName, field)) {
				continue;
			}
			
			Type type = field.getGenericType();
		    if (type instanceof ParameterizedType) {
		    	// TODO
		    } else {
		        if (field.getType().getSimpleName().equals("WebElement")) {
		        	field.setAccessible(true);
		        	try {
		        		return (WebElement)field.get(page);
					} catch (IllegalArgumentException
							| IllegalAccessException e) {
						e.printStackTrace();
					}
		        }
		    }
		}
		return null;
	}


	private boolean isFieldAnnotationMatches(String fieldName, Field field) {
		if (!field.isAnnotationPresent(TableName.class)) return false;
			
		fieldName = fieldName.trim();
		String fieldNameVariant = fieldName.toLowerCase().replaceAll("[_ ]", "");
		String anotationFieldName = field.getAnnotation(TableName.class).name();
		return anotationFieldName.equals(fieldName)
			|| anotationFieldName.toLowerCase().equals(fieldName.toLowerCase())
			|| anotationFieldName.toLowerCase().equals(fieldNameVariant);
	}
}
