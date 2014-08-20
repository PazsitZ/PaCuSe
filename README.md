Pazsit Cucumber Selenium Test FrameWork
==================

## cucumber - selenium test framework

The given repo with some Helper classes and abstract implementations provides an easier way to auto test with the PageObject pattern.

The framework use selenium. 
Both java or the cucumber feature tests are runned by TestNG

###Report-Generator
There is an integrated report-generator with the following cucumber-jvm reporter (https://github.com/masterthought/jenkins-cucumber-jvm-reports-plugin-java)

###Page Model and a PageObject
There are 2 layers for interacting with a web page:
- The Page Model represents the webpage elements and widgets only.
- The PageObject with the aggregation of the model, contains every interaction application logic.
The page model and pageObject can contains widgets, which are supported by the Object DataTable Mapping
My Model and Object structure based on the following resources: 
http://martinfowler.com/bliki/PageObject.html, https://code.google.com/p/selenium/wiki/PageObjects 

###Object-DataTable Mapping
Dynamic Cucumber-Selenium table mapping between Page Model and Gherkin Table from cucumber

# Some Features:
	- Easy usage of the Page Model - PageObject pattern
	- Support of Widgets, allow to embedd in the Page Model
	- Dynamic Cucumber-Selenium table mapping with configurable built in validation and population based on annotations (Object-DataTable Mapping)
	- Waiter Helper class with a collection of waiter implementations
	- WebElement Helper class
	- Form Elements Handling Helper class
	- built in report generator
	- use log4j logging
	

## Jar package available from:
http://pazsitz.hu/repo/hu/pazsitz/pacuse/0.82/pacuse.0.82.jar

http://pazsitz.hu/repo/hu/pazsitz/pacuse/0.82/pacuse.0.82.source.jar
