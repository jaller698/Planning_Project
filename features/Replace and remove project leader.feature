Feature: Replace and remove project leader

# Actor: Admin
# Description: Administrator adds or removes project leader

	Background: 
		Given a clean slate
		And a worker 1 is registered
		And worker 1 is registered as an admin
		And worker 1 is signed in
		And project "Diplomacy" exists
		And a worker 2 is registered under the name "Bob"
		
	Scenario: Administrator removes a project leader
		Given worker 2 is registered as a project leader for the project "Diplomacy"
  	When an admin 1 removes worker 2 as a project leader from project "Diplomacy"
  	Then the project "Diplomacy" has no project leader
  	And the message "Successfully removed 2(Bob) as project leader for project 'Diplomacy'" is returned
  	
	Scenario: Administrator adds a project leader
  	Given the project "Diplomacy" has no project leader assigned
  	When an admin 1 sets worker 2 as a project leader for project "Diplomacy"
  	Then a worker 2 is a project leader for project "Diplomacy"
  	And the message "Successfully sets 2(Bob) as project leader for project 'Diplomacy'" is returned
  	
  Scenario: Administrator replaces a project leader
  	Given worker 2 is registered as a project leader for the project "Diplomacy"
  	And a worker 3 is registered under the name "Steve"
  	When an admin 1 sets worker 3 as a project leader for project "Diplomacy"
  	Then a worker 3 is a project leader for project "Diplomacy"
  	And the message "Successfully sets 3(Steve) as project leader for project 'Diplomacy'" is returned
  	