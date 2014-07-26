package hu.pazsitz.pacuse.tests.cucumber.featuretables;

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
	
	public Map<String, String> getFailedFields() {
		return fieldFailed;
	}
	
	public List<String> getNonDeterminedFields() {
		return fieldNonDeterined;
	}
}
