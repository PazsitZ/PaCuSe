package hu.pazsitz.pacuse.tests.cucumber.featuretables;

import hu.pazsitz.pacuse.pages.AbstractPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * PopulatorDataTable.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class PopulatorDataTable implements IFieldMapperDataTable {
	private List<Map<String, String>> table = new ArrayList<>();
	private PageFieldTableMapper mapper = new PageFieldTableMapper(new PopulatorAction());
	

	public PopulatorDataTable(List<Map<String, String>> table) {
		this.table = table;
	}
	
	public List<Map<String, String>> getTable() {
		return table;
	}

	public void setTable(List<Map<String, String>> table) {
		this.table = table;
	}
	
	public ComparedResult populateToPageModel(AbstractPage page) {
//		mapper.
		
		return new ComparedResult(0, null, null, null);
	}
}
