package hu.pazsitz.pacuse.tests.cucumber.featuretables;

import hu.pazsitz.pacuse.tests.annotations.DataTableAttributes;
import org.mockito.Mockito;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AnnotatedWebElementNoSuchElementExceptionTest {

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testMapFields() {
        WebElement element = Mockito.mock(WebElement.class, Mockito.RETURNS_MOCKS);
        Mockito.when(element.findElement(Mockito.any())).thenThrow(new NoSuchElementException("org.openqa.selenium.NoSuchElementException: no such element  "));
        DataTableAttributes attrib = Mockito.mock(DataTableAttributes.class, Mockito.RETURNS_MOCKS);
        Mockito.when(attrib.name()).thenReturn(new String[]{"elementNotAppearing"});
        AnnotatedWebElement aElement = new AnnotatedWebElement(element, attrib);

        try {
            aElement.findElement(By.id("elementNotAppearing"));
        } catch (NoSuchElementException e ) {
            Assert.assertTrue(e instanceof NoSuchElementException);
            Assert.assertTrue(e.getMessage().startsWith("Annotated Field[[elementNotAppearing]] "));
            throw e;
        }
    }
}
