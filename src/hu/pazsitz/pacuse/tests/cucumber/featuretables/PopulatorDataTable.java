package hu.pazsitz.pacuse.tests.cucumber.featuretables;

import hu.pazsitz.pacuse.pages.AbstractPage;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.mapperactions.PopulatorAction;

import java.util.List;
import java.util.Map;

/**
 * PopulatorDataTable.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class PopulatorDataTable extends AbstractDataTable implements IFieldMapperDataTable {

	public PopulatorDataTable(List<Map<String, String>> table) {
		super(new PopulatorAction());
		this.table = table;
	}
	
	/**
	 * Populate fields to table values
	 * @param page
	 * @return ComparedResult
	 */
	public ComparedResult populateToPageModel(AbstractPage page) {
		return doActionToPageModel(page);
	}
}
