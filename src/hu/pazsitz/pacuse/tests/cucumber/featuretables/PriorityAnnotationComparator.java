package hu.pazsitz.pacuse.tests.cucumber.featuretables;

import java.util.Comparator;

public class PriorityAnnotationComparator implements Comparator<AnnotatedWebElement> {

	/**
	 * Sorts AnnotatedWebElement 
	 * compares by priority 
	 * or if their have equal priority 
	 * compares by object hashcode
	 * (As we use them as Map keys we need unique elements)
	 */
	@Override
	public int compare(AnnotatedWebElement element1, AnnotatedWebElement element2) {
		if (element1.getFieldAnnotation().priority() == element2.getFieldAnnotation().priority())  {
			return element1.hashCode() - element2.hashCode();
		}
		return element1.getFieldAnnotation().priority() - element2.getFieldAnnotation().priority();
	}

}
