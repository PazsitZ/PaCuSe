package hu.pazsitz.pacuse.tests.cucumber.featuretables;

import hu.pazsitz.pacuse.pages.AbstractPage;
import hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.ComparatorAction;

import java.util.List;
import java.util.Map;

/**
 * ComparatorDataTable.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class ComparatorDataTable extends AbstractDataTable implements IFieldMapperDataTable {
	
	public ComparatorDataTable(List<Map<String, String>> table) {
		super(new ComparatorAction());
		setTable(table);
	}
	
	/**
	 * Compare fields to table values
	 * @param page
	 * @return FieldActionResult
	 */
	public FieldActionResult compareToPageModel(AbstractPage page) {
		return doActionToPageModel(page);
	}
}
