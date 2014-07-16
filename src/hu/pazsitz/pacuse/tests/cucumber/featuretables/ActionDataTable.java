package hu.pazsitz.pacuse.tests.cucumber.featuretables;

import hu.pazsitz.pacuse.pages.AbstractPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ActionDataTable.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public abstract class ActionDataTable {
	protected int fieldNonDetermined = 0;
	protected final PageFieldTableMapper mapper;
	protected List<Map<String, String>> table = new ArrayList<>();
	
	public ActionDataTable(PageFieldTableMapper mapper) {
		this.mapper = mapper;
	}
	
	protected void fillNonDetermined() {
		for (Map<String, String> row : table) {
			fieldNonDetermined += row.size();
		}
	}

	public List<Map<String, String>> getTable() {
		return table;
	}

	public void setTable(List<Map<String, String>> table) {
		this.table = table;
	}
	
	public ComparedResult doActionToPageModel(AbstractPage page) {
		for (Map<String, String> row : table) {
			for (Map.Entry<String, String> entry : row.entrySet()) {
				mapper.mapFields(page, entry.getKey(), entry.getValue());
				fieldNonDetermined--;
			}
		}
		
		return new ComparedResult(fieldNonDetermined, null, null, null);
	}

	
}
