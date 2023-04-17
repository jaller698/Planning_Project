Feature: Register hours

# Actor: Worker
# Description: A worker registers hours spent on project

	Background:
		Given a clean slate
		And a worker 1 is registered
		And worker 1 is signed in
		And project "Javis" exists

	Scenario: A worker registers hours spent on activity
		Given project "Javis" has an activity "TileCreation" registered
		When the worker 1 adds 10 hours to activity "TileCreation" under project "Javis"
		Then 10 hours is registered under worker 1 in activity "TileCreation" 
#		And the message "Success" is returned

	Scenario: A worker registers hours spent on activity not created
		When the worker 1 adds 10 hours to activity "TileCreation" under project "Javis"
		Then the message "Unable to find activity 'TileCreation' under 'Javis'" is returned
		
	Scenario: A worker registers hours spent on activity under a non-existet project
		When the worker 1 adds 10 hours to activity "TileCreation" under project "Bob9000"
		Then the message "Unable to find project 'Bob9000'" is returned
