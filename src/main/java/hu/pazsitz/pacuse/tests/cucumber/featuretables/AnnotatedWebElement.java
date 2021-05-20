package hu.pazsitz.pacuse.tests.cucumber.featuretables;

import hu.pazsitz.pacuse.tests.annotations.DataTableAttributes;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.*;

/**
 * AnnotatedWebElement.java
 *
 * @author Zoltan Pazsit <pazsitz@pazsitz.hu>
 * @copyright Copyright (c) 2014, Zoltan Pazsit
 */
public class AnnotatedWebElement implements WebElement {
    private WebElement baseElement;
    private DataTableAttributes fieldAnnotation;

    /**
     * wraps WebElement and the associated DataTableAttributes annotation
     *
     * @param element
     * @param annotation
     */
    public AnnotatedWebElement(WebElement element, DataTableAttributes annotation) {
        this.baseElement = element;
        this.fieldAnnotation = annotation;
    }

    /**
     * Gets the real WebElement
     *
     * @return WebElement
     */
    public WebElement getBaseElement() {
        return baseElement;
    }

    /**
     * Gets the WebElement - Field annotation
     *
     * @return DataTableAttributes
     */
    public DataTableAttributes getFieldAnnotation() {
        return fieldAnnotation;
    }

    @Override
    public void click() {
        try {
            baseElement.click();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Annotated Field[" + Arrays.toString(fieldAnnotation.name()) + "] " + e.getMessage(), e.getCause());
        }
    }

    @Override
    public void submit() {
        try {
            baseElement.submit();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Annotated Field[" + Arrays.toString(fieldAnnotation.name()) + "] " + e.getMessage(), e.getCause());
        }
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        try {
            baseElement.sendKeys(keysToSend);

        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Annotated Field[" + Arrays.toString(fieldAnnotation.name()) + "] " + e.getMessage(), e.getCause());
        }
    }

    @Override
    public void clear() {
        try {
            baseElement.clear();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Annotated Field[" + Arrays.toString(fieldAnnotation.name()) + "] " + e.getMessage(), e.getCause());
        }
    }

    @Override
    public String getTagName() {
        try {
            return baseElement.getTagName();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Annotated Field[" + Arrays.toString(fieldAnnotation.name()) + "] " + e.getMessage(), e.getCause());
        }
    }

    @Override
    public String getAttribute(String name) {
        try {
            return baseElement.getAttribute(name);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Annotated Field[" + Arrays.toString(fieldAnnotation.name()) + "] " + e.getMessage(), e.getCause());
        }
    }

    @Override
    public boolean isSelected() {
        try {
            return baseElement.isSelected();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Annotated Field[" + Arrays.toString(fieldAnnotation.name()) + "] " + e.getMessage(), e.getCause());
        }
    }

    @Override
    public boolean isEnabled() {
        try {
            return baseElement.isEnabled();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Annotated Field[" + Arrays.toString(fieldAnnotation.name()) + "] " + e.getMessage(), e.getCause());
        }
    }

    @Override
    public String getText() {
        try {
            return baseElement.getText();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Annotated Field[" + Arrays.toString(fieldAnnotation.name()) + "] " + e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<WebElement> findElements(By findElements) {
        try {
            return baseElement.findElements(findElements);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Annotated Field[" + Arrays.toString(fieldAnnotation.name()) + "] " + e.getMessage(), e.getCause());
        }
    }

    @Override
    public WebElement findElement(By by) {
        try {
            return baseElement.findElement(by);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Annotated Field[" + Arrays.toString(fieldAnnotation.name()) + "] " + e.getMessage(), e.getCause());
        }
    }

    @Override
    public boolean isDisplayed() {
        try {
            return baseElement.isDisplayed();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Annotated Field[" + Arrays.toString(fieldAnnotation.name()) + "] " + e.getMessage(), e.getCause());
        }
    }

    @Override
    public Point getLocation() {
        try {
            return baseElement.getLocation();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Annotated Field[" + Arrays.toString(fieldAnnotation.name()) + "] " + e.getMessage(), e.getCause());
        }
    }

    @Override
    public Dimension getSize() {
        try {
            return baseElement.getSize();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Annotated Field[" + Arrays.toString(fieldAnnotation.name()) + "] " + e.getMessage(), e.getCause());
        }
    }

    @Override
    public String getCssValue(String propertyName) {
        try {
            return baseElement.getCssValue(propertyName);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Annotated Field[" + Arrays.toString(fieldAnnotation.name()) + "] " + e.getMessage(), e.getCause());
        }
    }

    @Override
    public Rectangle getRect() {
        try {
            return baseElement.getRect();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Annotated Field[" + Arrays.toString(fieldAnnotation.name()) + "] " + e.getMessage(), e.getCause());
        }
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> ot) throws WebDriverException {
        try {
            return baseElement.getScreenshotAs(ot);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Annotated Field[" + Arrays.toString(fieldAnnotation.name()) + "] " + e.getMessage(), e.getCause());
        }
    }

}
