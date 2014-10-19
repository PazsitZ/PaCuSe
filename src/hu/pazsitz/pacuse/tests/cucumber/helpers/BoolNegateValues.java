package hu.pazsitz.pacuse.tests.cucumber.helpers;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * BoolNegateValues.java
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
public class BoolNegateValues extends BoolValue {

	static {
		BoolValue.trueValue.addAll( new ArrayList<String>(Arrays.asList(
				"be", "is", "are", "has", 
				"should", "could", "do", "does", 
				"can", "must"
		)) );
		BoolValue.falseValue.addAll( new ArrayList<String>(Arrays.asList(
				"be not", "not be", "is not", "are not", "has not", 
	    		"should not", "could not", "do not", "does not", 
	    		"can not", "must not"
		)) );
	}
	
	public BoolNegateValues(String value) {
		super(value.toLowerCase().replace("n't", " not"));
	}

	@Override
	public String name() {
		return trueValue.contains(super.getValue()) ? "TRUE" : "FALSE";
	}
}
