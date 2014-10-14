package hu.pazsitz.pacuse.tests.cucumber.helpers;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.ListUtils;

/**
 * BoolValue.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
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
abstract public class BoolValue {

	protected static List<String> trueValue = new ArrayList<String>();
	protected static List<String> falseValue = new ArrayList<String>();
	
	private String value;
	private boolean boolValue;
	
	public BoolValue(String value) {
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
		return value;
	}
	
	
}
