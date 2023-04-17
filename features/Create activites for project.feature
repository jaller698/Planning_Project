Feature: Create activites for project

# Actor: Administrator, Projectleader
# Description: Administrator or Project leader creates activity for project

	Background:
		Given a clean slate
		And a worker "hans" is registered
		And worker "hans" is signed in
		And worker "hans" is a project leader

	Scenario: Admin creates activity for project
		Given project "4D Chess" exists
		And worker "hans" is registered as an admin
		When project leader "hans" creates activity "Tesseract creation" with 10 hours given in project "4D Chess"
		Then the activity "Tesseract creation" with 10 hours allocated exists under project "4D Chess" 
#		And the message "Successfully created activity 'Tesseract creation' with 10 hours under '4D Chess'" is returned
		
	Scenario: Project leader creates activity for project
		Given project "5D Chess" exists
		And worker "hans" is registered as a project leader for the project "5D Chess"
		When project leader "hans" creates activity "Elucidate geometry" with 5 hours given in project "5D Chess"
		Then the activity "Elucidate geometry" with 5 hours allocated exists under project "5D Chess" 
#		And the message "Successfully created activity 'Elucidate geometry' with 5 hours under '5D Chess'" is returned
		
	Scenario: Worker creates an activity to a project
		Given project "6D Chess" exists
		And worker "hans" is not registered as a project leader for the project "6D Chess"
		When project leader "hans" creates activity "BFG9000, no?" with 12 hours given in project "6D Chess"
#		And the message "Insufficient privileges to create activity  'BFG9000, no?' under Project '6D Chess'" is returned
		