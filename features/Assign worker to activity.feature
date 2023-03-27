Feature: Assign worker to activity

Actor: Project leader
Description: Project leader assigns worker to activity

	Background: 
		Given a worker 1 is registered
		And worker 1 is signed in
		And project "MinesweeperBattleRoyale" exists
		And worker 1 is project leader in project "MinesweeperBattleRoyale"
		
	Scenario: Project leader assigns worker to activity
		Given project "MinesweeperBattleRoyale" has an activity "TileCreation" registered
		When project leader 1 assigns worker 2 to activity "TileCreation" under project "MinesweeperBattleRoyale"
		Then worker 2 is assigned to activity "TileCreation"
		And the message "Success" is returned
		
	Scenario: Project leader assigns worker to non-existing activity
		When project leader 1 assignes worker 2 to activity "Game Night" under projekt "MinesweeperBattleRoyale"
		Then worker 1 has no assigned activities
		And the message "Unable to find activity 'Game Night'" is returned




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