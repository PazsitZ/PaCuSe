package hu.pazsitz.pacuse.tests.cucumber.featuretables;

import hu.pazsitz.pacuse.pages.AbstractPage;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.PopulatorAction;

import java.util.List;
import java.util.Map;

/**
 * PopulatorDataTable.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class PopulatorDataTable extends AbstractDataTable<PopulatorDataTable> implements IFieldMapperDataTable {

	public PopulatorDataTable(List<Map<String, String>> table) {
		super(new PopulatorAction());
		setTable(table);
	}
	
	/**
	 * Populate fields to table values
	 * @param page
	 * @return FieldActionResult
	 */
	public FieldActionResult populateToPageModel(AbstractPage page) {
		return doActionToPageModel(page);
	}
}
