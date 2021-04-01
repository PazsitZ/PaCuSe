package hu.pazsitz.pacuse.tests.cucumber.featuretables;

import java.util.List;
import java.util.Map;

/**
 * IFieldMapperDataTable.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public interface IFieldMapperDataTable {
	
	public List<Map<String, String>> getTable();

	public void setTable(List<Map<String, String>> table);
}
