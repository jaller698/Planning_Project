Feature: Replace and remove project leader

# Actor: Admin
# Description: Administrator adds or removes project leader

	Background: 
		Given a clean slate
		And a worker "hans" is registered
		And worker "hans" is registered as an admin
		And worker "hans" is signed in
		And project "Diplomacy" exists
		And a worker 2 is registered under the name "Bob"
		
	Scenario: Administrator removes a project leader
		Given worker "Bob" is project leader for the project "Diplomacy"
		When an admin "hans" removes worker "Bob" as a project leader from project "Diplomacy"
		Then the project "Diplomacy" has no project leader
#		And the message "Successfully removed 2(Bob) as project leader for project 'Diplomacy'" is returned
  	
	Scenario: Administrator adds a project leader
		Given the project "Diplomacy" has no project leader assigned
		When an admin "hans" sets worker "Bob" as a project leader for project "Diplomacy"
		Then a worker "Bob" is a project leader for project "Diplomacy"
#		And the message "Successfully sets 2(Bob) as project leader for project 'Diplomacy'" is returned
  	
  Scenario: Administrator replaces a project leader
  		Given worker "Bob" is project leader for the project "Diplomacy"
  		And a worker 3 is registered under the name "Steve"
  		When an admin "hans" sets worker "Steve" as a project leader for project "Diplomacy"
  		Then a worker "Steve" is a project leader for project "Diplomacy"
#  		And the message "Successfully sets 3(Steve) as project leader for project 'Diplomacy'" is returned
  	