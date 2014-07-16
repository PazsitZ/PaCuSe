package hu.pazsitz.pacuse.tests.cucumber.featuretables;

import hu.pazsitz.pacuse.pages.AbstractPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ComparatorDataTable.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class ComparatorDataTable implements IFieldMapperDataTable {
	private List<Map<String, String>> table = new ArrayList<>();
	private PageFieldTableMapper mapper = new PageFieldTableMapper(new ComparatorAction());
	private int fieldNonDeterined = 0;
	

	public ComparatorDataTable(List<Map<String, String>> table) {
		this.table = table;
		fillNonDetermined();
	}
	
	private void fillNonDetermined() {
		for (Map<String, String> row : table) {
			fieldNonDeterined += row.size();
		}
	}

	public List<Map<String, String>> getTable() {
		return table;
	}

	public void setTable(List<Map<String, String>> table) {
		this.table = table;
	}
	
	public ComparedResult compareToPageModel(AbstractPage page) {
		for (Map<String, String> row : table) {
			for (Map.Entry<String, String> entry : row.entrySet()) {
				mapper.mapFields(page, entry.getKey(), entry.getValue());
				fieldNonDeterined--;
			}
		}
		
		return new ComparedResult(fieldNonDeterined, null, null, null);
	}
}
