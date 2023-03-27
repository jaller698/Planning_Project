Feature: Create activites for project

Actor: Administrator, Projectleader
Description: Admin or project leader creates activity for project

	Scenario: Admin creates activity for project
		Given an admin is logged in
		And project "Project" exists
		When an admin creates activiy with title "Activity title" 
		And activity number 1
		And allocates 10 hours to the activity
		Then the activity "Activity title" with the activity number 1, expected hours 10 is added to the project "Project" and the message "Success" is returned
		
		Scenario: Project leader creates activity for project
		Given project "Project" exists
		And a worker is project leader for project "Project"
		When the project leader creates activiy with title "Activity title" 
		And activity number 1
		And allocates 10 hours to the activity
		Then the activity "Activity title" with the activity number 1, expected hours 10 is added to the project "Project" and the message "Success" is returned