package hu.pazsitz.pacuse.tests.cucumber.featuretables;

import hu.pazsitz.pacuse.pages.AbstractPage;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.mapperactions.IMapperAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * AbstractDataTable.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public abstract class AbstractDataTable implements IFieldMapperDataTable {
	private List<String> fieldNonDetermined = new ArrayList<>();
	private int fieldNumber = 0;
	private final PageFieldTableMapper mapper;
	private List<Map<String, String>> table = new ArrayList<>();
	
	public AbstractDataTable(IMapperAction action) {
		this.mapper = new PageFieldTableMapper(action);
	}
	
	protected void fillNonDetermined() {
		for (Map<String, String> row : table) {
			for (Map.Entry<String, String> entry : row.entrySet()) {
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
	protected ComparedResult doActionToPageModel(AbstractPage page) {
		List<String> success = new ArrayList<>();
		List<String> failed = new ArrayList<>();
		for (Map<String, String> row : table) {
			for (Map.Entry<String, String> entry : row.entrySet()) {
				String fieldName = entry.getKey();
				Boolean mappedField = mapper.mapFields(page, fieldName, entry.getValue());
				if (mappedField != null && mappedField) {
					success.add(fieldName);
					fieldNonDetermined.remove(fieldName);
				} else if (mappedField != null) {
					failed.add(fieldName);
					fieldNonDetermined.remove(fieldName);
				}
				
			}
		}
		
		return new ComparedResult(fieldNumber, success, fieldNonDetermined, failed);
	}

	
}
