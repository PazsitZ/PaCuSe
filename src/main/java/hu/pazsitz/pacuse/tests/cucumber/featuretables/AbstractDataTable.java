package hu.pazsitz.pacuse.tests.cucumber.featuretables;

import hu.pazsitz.pacuse.pages.AbstractPage;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.IFieldAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;

/**
 * AbstractDataTable.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public abstract class AbstractDataTable<T extends AbstractDataTable<T>> implements IFieldMapperDataTable {
	private IFieldAction action;
	
	/**
	 * On false state the non existent elements treated as non-determined fields instead of failed ones
	 */
	private boolean strictMode = true;
	private List<String> success = new ArrayList<>();
	private Map<String, String> failed = new HashMap<>();
	private List<String> fieldNonDetermined = new ArrayList<>();
	private int fieldNumber = 0;
	private final PageFieldTableMapper mapper;
	private List<Map<String, String>> table = new ArrayList<>();
	
	public AbstractDataTable(IFieldAction action) {
		this.action = action;
		this.mapper = new PageFieldTableMapper();
	}
	
	/**
	 * Constructor for implicit turn off the strict mode
	 * Chain setter also provided
	 * @param action
	 * @param strictMode true by default
	 */
	public AbstractDataTable(IFieldAction action, boolean strictMode) {
		this.action = action;
		this.mapper = new PageFieldTableMapper();
		this.strictMode = strictMode;
	}
	
	protected void fillNonDetermined() {
		for (Map<String, String> row : table) {
			for (Entry<String, String> entry : row.entrySet()) {
				fieldNonDetermined.add(entry.getKey());
				fieldNumber++;
			}
		}
	}
	
	/**
	 * Implement with return to provide chained setter if second constructor is not implemented
	 * @param strictMode true by default
	 * @return super implementation class type
	 */
	@SuppressWarnings("unchecked") // the nested extend should prevents unchecked cast
	public T setStrictMode(boolean strictMode) {
		this.strictMode = strictMode;
		
		return (T) this;
	}

	public List<Map<String, String>> getTable() {
		return table;
	}

	public void setTable(List<Map<String, String>> table) {
		this.table = table;
		fillNonDetermined();
	}
	
	/**
	 * Finds the corresponding fields and invokes the given action on it
	 * @param page
	 * @return
	 */
	protected FieldActionResult doActionToPageModel(AbstractPage page) {
		success = new ArrayList<>();
		failed = new HashMap<>();
		Map<AnnotatedWebElement, Entry<String, String>> fields = new TreeMap<>(new PriorityAnnotationComparator());
		
		for (Map<String, String> row : table) {
			for (Entry<String, String> entry : row.entrySet()) {
				AnnotatedWebElement mappedField = mapper.mapField(page, entry.getKey());
				if (mappedField != null) {
					fields.put(mappedField, entry);
				}
			}
		}
		
		/** Sorted Map by priority anotation value */
		for (AnnotatedWebElement element : fields.keySet()) {
			evaluateAction(fields.get(element), element);
		}
		
		return new FieldActionResult(fieldNumber, success, fieldNonDetermined, failed);
	}

	private void evaluateAction(Entry<String, String> entry, AnnotatedWebElement mappedField) {
		Exception ex = null;
		Boolean result = null;
		String fieldName = entry.getKey();
		try {
			result = action.doAction(mappedField, entry.getValue());
		} catch (Exception e) {
			Logger.getLogger(this.getClass()).error("(field: [" + fieldName + "]) exception: " + e.getMessage());
			result = false;
			ex = e;
		}
		
		if (result != null && result) {
			success.add(fieldName);
			fieldNonDetermined.remove(fieldName);
		} else if (!strictMode && (ex instanceof NoSuchElementException)) {
			// no action
			Logger.getLogger(this.getClass()).info("strict mode off, field: [" + fieldName + "])", ex);
		} else if (result != null) {
			failed.put(fieldName, ex.getMessage());
			fieldNonDetermined.remove(fieldName);
		}
	}
}
