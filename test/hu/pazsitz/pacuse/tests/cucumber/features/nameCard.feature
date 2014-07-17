@test
Feature: Sample

	Scenario: NameCard WebPage
		Given the NameCard Webpage
		When select the about card
		Then validate the about page content:
		|	name		|	profession						|
		| Zoltán Pázsit	| Software Enginer - Web Developer	|
		When select the contact card
		Then validate the contact page content
		And no matching feature

