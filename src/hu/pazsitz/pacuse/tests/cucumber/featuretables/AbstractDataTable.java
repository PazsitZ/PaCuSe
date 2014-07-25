package hu.pazsitz.pacuse.tests.cucumber.featuretables;

import hu.pazsitz.pacuse.pages.AbstractPage;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.IFieldAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.WebElement;

/**
 * AbstractDataTable.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public abstract class AbstractDataTable implements IFieldMapperDataTable {
	private IFieldAction action;
	private List<String> success = new ArrayList<>();
	private List<String> failed = new ArrayList<>();
	private List<String> fieldNonDetermined = new ArrayList<>();
	private int fieldNumber = 0;
	private final PageFieldTableMapper mapper;
	private List<Map<String, String>> table = new ArrayList<>();
	
	public AbstractDataTable(IFieldAction action) {
		this.action = action;
		this.mapper = new PageFieldTableMapper();
	}
	
	protected void fillNonDetermined() {
		for (Map<String, String> row : table) {
			for (Entry<String, String> entry : row.entrySet()) {
				fieldNonDetermined.add(entry.getKey());
				fieldNumber++;
			}
		}
	}

	public List<Map<String, String>> getTable() {
		return table;
	}

	public void setTable(List<Map<String, String>> table) {
		this.table = table;
		fillNonDetermined();
	}
	
	/**
	 * finds the corresponding fields and invokes the given action on it
	 * @param page
	 * @return
	 */
	protected FieldActionResult doActionToPageModel(AbstractPage page) {
		success = new ArrayList<>();
		failed = new ArrayList<>();
		for (Map<String, String> row : table) {
			for (Entry<String, String> entry : row.entrySet()) {
				WebElement mappedField = mapper.mapField(page, entry.getKey());
				if (mappedField != null) {
					evaluateAction(entry, mappedField);
				}
			}
		}
		
		return new FieldActionResult(fieldNumber, success, fieldNonDetermined, failed);
	}

	private void evaluateAction(Entry<String, String> entry, WebElement mappedField) {
		String fieldName = entry.getKey();
		Boolean result = action.doAction(mappedField, entry.getValue());
		
		if (result != null && result) {
			success.add(fieldName);
			fieldNonDetermined.remove(fieldName);
		} else if (result != null) {
			failed.add(fieldName);
			fieldNonDetermined.remove(fieldName);
		}
	}
	
}
