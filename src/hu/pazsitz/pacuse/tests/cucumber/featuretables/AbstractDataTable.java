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
	protected List<String> fieldNonDetermined = new ArrayList<>();
	protected int fieldNumber = 0;
	protected final PageFieldTableMapper mapper;
	protected List<Map<String, String>> table = new ArrayList<>();
	
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
	}
	
	protected ComparedResult doActionToPageModel(AbstractPage page) {
		List<String> success = new ArrayList<>();
		List<String> failed = new ArrayList<>();
		for (Map<String, String> row : table) {
			for (Map.Entry<String, String> entry : row.entrySet()) {
				String fieldName = entry.getKey();
				if (mapper.mapFields(page, fieldName, entry.getValue())) {
					success.add(fieldName);
				} else {
					failed.add(fieldName);
				}
				fieldNonDetermined.remove(fieldName);
			}
		}
		
		return new ComparedResult(fieldNumber, success, fieldNonDetermined, failed);
	}

	
}
