Feature: Create activites for project

Actor: Administrator, Projectleader
Description: Admin or project leader creates activity for project

	Scenario: Admin creates activity for project
		Given a worker 1 is registered
		And worker 1 is registered as an admin
		And project "4D Chess" exists
		When worker 1 creates activity "Tesseract creation" with 10 hours given in project "4D Chess"
		Then the activity "Tesseract creation" with hours 10 allocated is added to the project "4D Chess" 
		And the message "Success" is returned
		
		Scenario: Project leader creates activity for project
		Given a worker 1 is registered
		And project "5D Chess" exists
		And a worker 1 is a project leader for project "5D Chess"
		When worker 1 creates activity "elucidate geometry" with 5 hours given in project "5D Chess"
		Then the activity "Tesseract creation" with hours 5 allocated is added to the project "5D Chess" 
		And the message "Success" is returned
		
		Scenario: Worker creates an activity to a project
		Given a worker 1 is registered
		And project "6D Chess" exists
		When worker 1 creates activity "BFG9000, no?" with 12 hours given in project "6D Chess"
		And the message "insufficient privileges to create activity  'BFG9000, no?' under Project '6D Chess'" is returned
		