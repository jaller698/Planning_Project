Feature: Create project

# Actor: Administrator, Worker
# Description: Worker or Administrator creates project 

	Background:
		#Given a clean slate
		Given a worker "hans" is registered
		And worker "hans" is signed in

	Scenario: A worker creates project
  	When loggedin worker creates a project with the name "Backend testing systems"
  	Then the project "Backend testing systems" with 5-digit serial no. from year and project number is created
  	And a worker "hans" is a project leader for project "Backend testing systems"
  	And the message "Successfully created project 'Backend testing systems'(23001)" is returned

  
	Scenario: An administrator creates project and adds a project leader
  	Given worker "hans" is registered as an admin
  	When the administrator creates project with project name "Unlimited money scheme"
  	Then the project "Unlimited money scheme" with 5-digit serial no. from year and project number is created
  	And the project "Unlimited money scheme" has no project leader
  	And the message "Successfully created project 'Unlimited money scheme'(23002)" is returned

	Scenario: An administrator creates project with estimated time
	  Given worker "hans" is registered as an admin
  	When administrator creates a project with the name "Ongo Bongo" and estimated time 10
  	Then the project "Ongo Bongo" with 5-digit serial no. from year and project number is created
  	And the message "Successfully created project 'Ongo Bongo'(23003)" is returned

	Scenario: An administrator creates project with estimated time and a leader
	  Given worker "hans" is registered as an admin
  	When administrator creates a project with the name "Ongo Bongo", with leader "hans", and estimated time 10
  	Then the project "Ongo Bongo" with 5-digit serial no. from year and project number is created
  	And a worker "hans" is a project leader for project "Ongo Bongo"
  	And the message "Successfully created project 'Ongo Bongo'(23004)" is returned
