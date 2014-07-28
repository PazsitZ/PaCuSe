package hu.pazsitz.pacuse.tests.cucumber.featuretables;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FieldActionResult.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class FieldActionResult {
	private final int tableFieldNumber;
	private final List<String> fieldSuccess;
	private final List<String> fieldNonDeterined;
	private final Map<String, String> fieldFailed;
	
	public FieldActionResult(int tableFieldNumber, List<String> fieldSuccess, List<String> fieldNonDeterined, Map<String, String> fieldFailed) {
		this.tableFieldNumber = tableFieldNumber;
		this.fieldSuccess = fieldSuccess;
		this.fieldNonDeterined = fieldNonDeterined;
		this.fieldFailed = fieldFailed;
	}
	
	/**
	 * The last procedure was successful and all the table fields have been evaluated already
	 * use FieldActionResult.getNonSucceedFields() for assertion message
	 * @return boolean
	 */
	public boolean fullSuccess() {
		return success() && fieldNonDeterined.size() == 0;
	}
	
	/**
	 * The last procedure was successful
	 * @return boolean
	 */
	public boolean success() {
		return fieldFailed.size() == 0;
	}
	
	/**
	 * Has the last procedure contained failures
	 * @return
	 */
	public boolean hasFailed() {
		return fieldFailed.size() > 0;
	}
	
	/**
	 * Has the table non-determined/evaluated field
	 * @return
	 */
	public boolean hasNonDeterined() {
		return fieldNonDeterined.size() > 0;
	}
	
	/**
	 * Gets the number of the field from the provided table
	 * @return int
	 */
	public int getFieldNumber() {
		return tableFieldNumber;
	}
	
	public List<String> getSuccessFields() {
		return fieldSuccess;
	}
	
	public Map<String, String> getFailedFields() {
		return fieldFailed;
	}
	
	/**
	 * Gets the sum of failed and nonDetermined fields
	 * For assertion debug message
	 * @return Map<String, String>
	 */
	public Map<String, String> getNonSucceedFields() {
		Map<String, String> map = new HashMap<String, String>();
		map.putAll(fieldFailed);
		for (String item : fieldNonDeterined) { map.put(item, "Non Determined"); }
		
		return map;
	}
	
	public List<String> getNonDeterminedFields() {
		return fieldNonDeterined;
	}
}
