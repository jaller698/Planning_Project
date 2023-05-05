
Feature: Whitebox test
  I want to use this template for my feature file

	Background:
		Given a clean slate
		And a worker "hans" is registered
		And worker "hans" is signed in 

		Scenario: test of 1 setID
  	When number 1 project titled "Backend testing systems" is created
  	And the message "Successfully created project 'Backend testing systems'(23001)" is returned
  	
  	Scenario: test of 10 setID
  	When number 24 project titled "Backend testing systems" is created
  	And the message "Successfully created project 'Backend testing systems'(23024)" is returned
  	
  	Scenario: test of 100 setID
  	When number 690 project titled "Backend testing systems" is created
  	And the message "Successfully created project 'Backend testing systems'(23690)" is returned
  	
  	Scenario: test of null setID
  	When number 4474 project titled "Backend testing systems" is created
  	And the message "Successfully created project 'Backend testing systems'(0)" is returned
  	
  	Scenario: test of addUser
  	When the user "Ole" is created
  	Then "Ole" is assigned an id
  	
  	Scenario: test of duplicate addUser
  	When the user "Ole" is created
  	And the duplicate user "Ole" is created
  	Then "Ole" is not assigned an id