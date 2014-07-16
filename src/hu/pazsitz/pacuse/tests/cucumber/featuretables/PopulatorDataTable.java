package hu.pazsitz.pacuse.tests.cucumber.featuretables;

import hu.pazsitz.pacuse.pages.AbstractPage;

import java.util.List;
import java.util.Map;

/**
 * PopulatorDataTable.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class PopulatorDataTable extends ActionDataTable implements IFieldMapperDataTable {

	public PopulatorDataTable(List<Map<String, String>> table) {
		super(new PageFieldTableMapper(new PopulatorAction()));
		this.table = table;
	}
	
	public ComparedResult populateToPageModel(AbstractPage page) {
		return doActionToPageModel(page);
	}
}
