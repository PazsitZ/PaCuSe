package hu.pazsitz.pacuse.tests.cucumber.featuretables.fieldactions.delegates;

/**
 * DelegatedActionException.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class DelegatedActionException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public DelegatedActionException(String message) {
		super(message);
	}
	
	public DelegatedActionException(String expected, String actual) {
		super("expected: [" + expected + "] actual: [" + actual + "]");
	}
}
