package hu.pazsitz.pacuse.tests.cucumber.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.ListUtils;

/**
 * BoolValue.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 * <br/>
 * @usage supported aliases: "be", "is", "are", "has", "should", "could", "do", "does", "can", "must"<br/>
 * and their negates (eg.: "is not", "isn't") 
 * <br/>
 * @code
 * supports extend:<br>
 * <pre>
 * class NewBoolValue extends BoolValue {
 *     static {
 *         BoolValue.trueValue.addAll( new ArrayList&lt;String&gt;(Arrays.asList("true")) );
 *         BoolValue.falseValue.addAll( new ArrayList&lt;String&gt;(Arrays.asList("false")) );
 *     }
 *     
 *     public NewBoolValue(String value) { super(value); }
 * }</pre>
 */
public class BoolValue {

	protected static List<String> trueValue = new ArrayList<String>(
	    Arrays.asList(
			"be", "is", "are", "has", 
			"should", "could", "do", "does", 
			"can", "must"
		)
    );
	protected static List<String> falseValue = new ArrayList<String>(
	    Arrays.asList(
    		"be not", "is not", "are not", "has not", 
    		"should not", "could not", "do not", "does not", 
    		"can not", "must not"
		)
    );
	
	private String value;
	private boolean boolValue;
	
	public BoolValue(String value) {
		value = value.toLowerCase().replace("n't", " not");
		if (ListUtils.union(trueValue, falseValue).contains(value)) {
			this.value = value;
			this.boolValue = trueValue.contains(value);
		} else {
			throw new IllegalArgumentException("no such value predefined, try to extend the class and define the value");
		}
	}

	@Override
	public boolean equals(Object obj) {
		BoolValue bool = (BoolValue) obj;
		return this.value.equals(bool.getValue());
	}
	
	public String getValue() {
		return value;
	}
	
	public boolean getBoolValue() {
		return boolValue;
	}
	
	public String name() {
		return trueValue.contains(value) ? "TRUE" : "FALSE";
	}
	
	
}
