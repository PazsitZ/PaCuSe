package hu.pazsitz.pacuse.tests.cucumber.featuretables;

import hu.pazsitz.pacuse.pages.AbstractPage;
import hu.pazsitz.pacuse.pages.AbstractWidget;
import hu.pazsitz.pacuse.tests.annotations.DataTableAttributes;
import hu.pazsitz.pacuse.tests.annotations.Widget;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

/**
 * PageFieldTableMapper.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class PageFieldTableMapper {
			
	/**
	 * Gets the mapped field if exists
	 * @param page
	 * @param fieldName
	 * @return boolean
	 */
	public AnnotatedWebElement mapField(AbstractPage page, String fieldName) {
		AnnotatedWebElement element = lookUpForField(page, fieldName);

		return element;
    }
	
	/**
	 * Simple element mapping to field names
	 * @param page
	 * @param fields
	 * @return Map<String, WebElement>
	 */
	public Map<String, AnnotatedWebElement> mapFields(AbstractPage page, List<String> fields) {
		Map<String, AnnotatedWebElement> result = new HashMap<>();
		
		for (String field : fields) {
			AnnotatedWebElement element = lookUpForField(page, field);
			result.put(field, element);
		}
		
    	return result;
    }
	
	private AnnotatedWebElement lookUpForField(AbstractPage page, String fieldName) {
		List<Field> widgets = new ArrayList<>();
		for (Field field : page.getClass().getDeclaredFields()) {
			if (!isFieldAnnotationMatches(fieldName, field)) {
				if (field.isAnnotationPresent(Widget.class)) {
					widgets.add(field);
				}
				continue;
			}
			
			Type type = field.getGenericType();
		    if (type instanceof ParameterizedType) {
		    	// TODO
		    } else {
		        if (field.getType().getSimpleName().equals("WebElement")) {
		        	field.setAccessible(true);
		        	try {
		        		WebElement element = (WebElement)field.get(page);
		        		return new AnnotatedWebElement(element, field.getAnnotation(DataTableAttributes.class));
					} catch (IllegalArgumentException | IllegalAccessException e) {
						Logger.getLogger(this.getClass()).error(e.getMessage());
					}
		        }
		    }
		}
		
		for (Field field : widgets) {
			field.setAccessible(true);
			try {
				return lookUpForField((AbstractWidget)field.get(page), fieldName);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				Logger.getLogger(this.getClass()).error(e.getMessage());
			}
		}
		
		return null;
	}


	private boolean isFieldAnnotationMatches(String fieldName, Field field) {
		if (!field.isAnnotationPresent(DataTableAttributes.class)) return false;
			
		fieldName = fieldName.trim();
		String fieldNameVariant = fieldName.toLowerCase().replaceAll("[_ ]", "");
		for (String anotationFieldName : field.getAnnotation(DataTableAttributes.class).name()) {
			if ( anotationFieldName.equals(fieldName)
				|| anotationFieldName.toLowerCase().equals(fieldName.toLowerCase())
				|| anotationFieldName.toLowerCase().equals(fieldNameVariant)) {
				return true;
			}
		}
		return false;
	}
}
