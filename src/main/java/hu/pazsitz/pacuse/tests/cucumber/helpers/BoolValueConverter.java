package hu.pazsitz.pacuse.tests.cucumber.helpers;

import cucumber.api.Transformer;

/**
 * BoolValueConverter.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class BoolValueConverter extends Transformer<BoolValueEnum> {
	@Override
	public BoolValueEnum transform(String value) {
		return BoolValueEnum.getBoolValue(value);
	}

}
