Feature: Create project

# Actor: Administrator, Worker
# Description: Worker or Administrator creates project 

	Background:
		Given a worker 1 is registered
		And worker 1 is signed in

	Scenario: A worker creates project
  	When worker 1 creates a project with the name "Backend testing systems"
  	Then the project "Backend testing systems" with 5-digit serial no. from year and project number is created
  	And a worker 1 is a project leader for project "Backend testing systems"
  	And the message "Successfully created project 'Backend testing systems'(23001)" is returned
  
	Scenario: An administrator creates project and adds a project leader
  	Given worker 1 is registered as an admin
  	When the administrator creates project with project name "Unlimited money scheme"
  	Then the project "Unlimited money scheme" with 5-digit serial no. from year and project number is created
  	And the project "Unlimited money scheme" has no project leader
  	And the message "Successfully created project 'Unlimited money scheme'(23001)" is returned