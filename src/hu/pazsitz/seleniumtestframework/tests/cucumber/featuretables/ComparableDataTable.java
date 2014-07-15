package hu.pazsitz.seleniumtestframework.tests.cucumber.featuretables;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ComparableDataTable.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class ComparableDataTable {
	private List<Map<String, String>> table = new ArrayList<>();

	public ComparableDataTable(List<Map<String, String>> table) {
		this.table = table;
	}
	
	public List<Map<String, String>> getTable() {
		return table;
	}

	public void setTable(List<Map<String, String>> table) {
		this.table = table;
	}
	
	
}
