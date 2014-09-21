@test
Feature: Sample

	Scenario: NameCard WebPage
		Given the NameCard Webpage
		When select the about card
		Then validate the about page content:
		|	name		|	profession						|
		| Zoltán Pázsit	| Software Engineer - Web Developer	|
		When select the contact card
		Then validate the contact page content from table:
		| email1             | email2            | webPage            | Facebook                    | LinkedIn                          | Github                      |
		| contact@pazsitz.hu | pazsitz@gmail.com | http://pazsitz.hu/ | http://facebook.com/pazsitz | http://linkedin.com/in/PazsitZ    | https://github.com/PazsitZ/ |
		And no matching feature

