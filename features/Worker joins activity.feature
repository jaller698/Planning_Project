Feature: Worker joins activity

# Actor: Worker
# Description: Worker joins activity by themselves

	Scenario: Worker joins activity
		Given a clean slate
		And a worker 1 is registered
		And worker 1 is signed in
		And project "Productivity app" exists
		And project "Productivity app" has an activity "GUI creation" registered
		When worker 1 joins activity "GUI creation" under project "Productivity app"
		Then worker 1 is assigned to activity "GUI creation"
#		And the message "Successfully joined activity 'GUI creation' under 'Productivity app'" is returned
