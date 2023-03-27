Feature: Register hours

Actor: Worker
Description: A worker registers hours spent on project

	Background:
		Given a worker 1 is registered
		And worker 1 is signed in

	Scenario: A worker registers hours spent on activity
		Given project "Javis" exists
		And project "Javis" has an activity "TileCreation" registered
		When the worker 1 adds 10 hours to activity "TileCreation" under project "Javis"
		Then 10 hours is registered under worker 1 in activity "TileCreation" 
		And the message "Success" is returned

	Scenario: A worker registers hours spent on activity not created
		When the worker 1 adds 10 hours to activity "TileCreation" under project "Javis"
		Then the message "Unable to find activity 'TileCreation'" is returned
