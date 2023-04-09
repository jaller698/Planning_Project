Feature: Assign worker to activity

Actor: Project leader
Description: Project leader assigns worker to activity

	Background: 
		Given a worker 1 is registered
		And worker 1 is signed in
		And project "MinesweeperBattleRoyale" exists
		And a worker 1 is a project leader for project "MinesweeperBattleRoyale"
		
	Scenario: Project leader assigns worker to activity
		Given project "MinesweeperBattleRoyale" has an activity "TileCreation" registered
		When project leader 1 assigns worker 2 to activity "TileCreation" under project "MinesweeperBattleRoyale"
		Then worker 2 is assigned to activity "TileCreation"
		And the message "Success" is returned
		
	Scenario: Project leader assigns worker to non-existing activity
		When project leader 1 assigns worker 2 to activity "Game Night" under project "MinesweeperBattleRoyale"
		Then worker 1 has no assigned activities
		And the message "Unable to find activity 'Game Night'" is returned
		