Feature: Edit activites for project

# Actor: Administrator, Project leader
# Description: Administrator or Project leader edits the an activity

	Background:
		Given a clean slate
		And a worker "Hans" is registered
		And worker "Hans" is signed in
		And project "Company van" exists
		And project "Company van" has an activity "Concept design brainstorming" registered with 5 hours allocated

	Scenario: Project leader edits expected hours for activity
		Given worker "Hans" is registered as a project leader for the project "Company van"
		When project leader "Hans" edits the expected time of activity "Concept design brainstorming", under project "Company van", to 15 hours
  	Then the activity "Concept design brainstorming" with 15 hours allocated exists under project "Company van" 
#  	And the message "Successfully changed expected time on activity 'Concept design brainstorming' under 'Company van' from 5 hours to 15 hours" is returned
  	
  Scenario: Administrator edits expected hours for activity
		Given worker "Hans" is registered as an admin
		When project leader "Hans" edits the expected time of activity "Concept design brainstorming", under project "Company van", to 2 hours
  	Then the activity "Concept design brainstorming" with 2 hours allocated exists under project "Company van" 
#  	And the message "Successfully changed expected time on activity 'Concept design brainstorming' under 'Company van' from 5 hours to 2 hours" is returned
	
	Scenario: Project leader removes an acitivty
		Given worker "Hans" is registered as a project leader for the project "Company van"
		When project leader "Hans" removes the activity "Concept design brainstorming", under project "Company van"
		Then the project "Company van" has no activity named "Concept design brainstorming" under it
#		And the message "Successfully removed the activity 'Concept design brainstorming' from the project 'Company van'" is returned
		