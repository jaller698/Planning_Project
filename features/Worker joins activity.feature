Feature: Worker joins activity

# Actor: Worker
# Description: Worker joins activity by themselves

	Scenario: Worker joins activity
		Given a clean slate
		And a worker "hans" is registered
		And worker "hans" is signed in
		And project "Productivity app" exists
		And project "Productivity app" has an activity "GUI creation" registered
		When worker "hans" joins activity "GUI creation" under project "Productivity app"
		Then worker "hans" is assigned to activity "GUI creation"
		And the message "Successfully joined activity 'GUI creation' under 'Productivity app'" is returned
