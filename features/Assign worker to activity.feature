Feature: Assign worker to activity

# Actor: Project leader
# Description: Project leader assigns worker to activity

	Background: 
		Given a clean slate
		And a worker 1 is registered under the name "Bob"
		And worker 1 is signed in
		And project "MinesweeperBattleRoyale" exists
		And worker 1 is registered as a project leader for the project "MinesweeperBattleRoyale"
		And a worker 2 is registered
		
	Scenario: Project leader assigns worker to activity
		Given project "MinesweeperBattleRoyale" has an activity "TileCreation" registered
		When project leader 1 assigns worker 2 to activity "TileCreation" under project "MinesweeperBattleRoyale"
		Then worker 2 is assigned to activity "TileCreation"
#		And the message "Successfully added 2(Bob) to activity 'TileCreation' under 'MinesweeperBattleRoyale'" is returned
		
	Scenario: Project leader assigns worker to non-existing activity
		When project leader 1 assigns worker 2 to activity "Game Night" under project "MinesweeperBattleRoyale"
		Then worker 1 has no assigned activities
#		And the message "Unable to find activity 'Game Night' under 'MinesweeperBattleRoyale'" is returned
		