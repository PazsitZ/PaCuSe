package hu.pazsitz.pacuse.tests.cucumber.helpers;

/**
 * BoolValueEnum.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 * <br/>
 * @usage supported aliases: "be", "is", "are", "has", "should", "could", "do", "does", "can", "must"<br/>
 * and their negates (eg.: "is not", "isn't") 
 * <br/>
 * @usage use the following annotation in the stepDefinition method to the class:<br> 
 * 	<pre>&#64;Transform(BoolValueConverter.class) BoolValueEnum value</pre>
 */
public enum BoolValueEnum {
	BE("be", true), NOT_BE("not be", false),
    IS("is", true), IS_NOT("is not", false),
    ARE("are", true), ARE_NOT("are not", false),
    HAS("has", true), HAS_NOT("has not", false),
	SHOULD("should", true), SHOULD_NOT("should not", false),
	COULD("could", true), COULD_NOT("could not", false),
	DO("do", true), DO_NOT("do not", false),
	DOES("does", true), DOES_NOT("does not", false),
	CAN("can", true), CANNOT("can not", false),
	MUST("must", true), MUST_NOT("must not", false);
	
	
	private String value;
	private boolean boolValue;
	
	
    BoolValueEnum(String value, boolean boolValue) {
        this.value = value;
        this.boolValue = boolValue;
    }
   
    public String getValue() {
        return value;
    }
    
    public boolean getBoolValue() {
    	return boolValue;
    }
   
    public static BoolValueEnum getBoolValue(String be) {
        for(BoolValueEnum boolEnum : values()){
            if( boolEnum.value.equals(be.toLowerCase().replace("n't", " not"))){
                return boolEnum;
            }
        }
       
        return null;
    }
}
