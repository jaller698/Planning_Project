Feature: Register hours

Actor: Worker
Description: A worker registers hours spent on project

	Background:
		Given a worker is registered
		And worker is signed in

	Scenario: A worker registers hours spent on project
		Given project "Project" is created
		When the worker adds "10" hours
		Then "10" hours is added to the project
		And the message "Success" is returned

	Scenario: A worker registers hours spent on project not created
		Given project "Project" is not created
		Then the error message "Unable to find projekt 'Project'" is returned. 
