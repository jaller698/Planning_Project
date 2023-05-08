Feature: Assign worker to activity

# Actor: Project leader
# Description: Project leader assigns worker to activity

	Background: 
		Given a clean slate
		And a worker "Bob" is registered
		And worker "Bob" is signed in
		And project "MinesweeperBattleRoyale" exists
		And worker "Bob" is registered as a project leader for the project "MinesweeperBattleRoyale"
		And a worker "Steve" is registered
		
	Scenario: Project leader assigns worker to activity
		Given project "MinesweeperBattleRoyale" has an activity "TileCreation" registered
		When project leader "Bob" assigns worker "Steve" to activity "TileCreation" under project "MinesweeperBattleRoyale"
		Then worker "Steve" is assigned to activity "TileCreation"
		And the message "Successfully added 2(Steve) to activity 'TileCreation' under 'MinesweeperBattleRoyale'" is returned
		
	Scenario: Project leader assigns worker to non-existing activity
		When project leader "Bob" assigns worker "Steve" to activity "Game Night" under project "MinesweeperBattleRoyale"
		Then worker "Steve" has no assigned activities
		#And the message "Unable to find activity 'Game Night' under 'MinesweeperBattleRoyale'" is returned
		