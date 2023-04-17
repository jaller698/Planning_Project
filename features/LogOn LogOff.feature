Feature: LogOn LogOff

# Actor: Administrator, Worker
# Description: the ability to log on and off the system and registering new workers

	Background:
		Given a clean slate
		And a worker 1 is registered under the name "Steve" with the password "SuperSquare9000"

	Scenario: A worker logs in to the system
		Given worker 1 is signed off
		When worker 1 logs on with username "Steve" and password "SuperSquare9000"
		Then worker 1 is logged on
		And the message "Successfully logged in" is returned
		
	Scenario: A worker fails to login with the wrong password
		Given worker 1 is signed off
		When worker 1 logs on with username "Steve" and password "YesTHISis100%myPassword"
		Then worker 1 is logged off
		And the message "Failed login: Wrong username or password" is returned	
		
	Scenario: A worker fails to login with the wrong name
		Given worker 1 is signed off
		When worker 1 logs on with username "Bob" and password "SuperSquare9000%myPassword"
		Then worker 1 is logged off
		And the message "Failed login: Wrong username or password" is returned	
		
	Scenario: A worker changes their password
		Given worker 1 is signed in
		When worker 1 changes their password from "SuperSquare9000" to "SuperSquare9001"
		Then worker 1 has the password "SuperSquare9001"
		And the message "Successfully changed password" is returned	
	
	Scenario: An admin changes a workers password
		Given worker 1 is signed in
		And worker 1 is registered as an admin
		And a worker 2 is registered under the name "Bob" with the password "MySup3rAws0m3Passw0rd"
		When admin 1 changes worker 2 password from "MySup3rAws0m3Passw0rd" to "c29P@Yuj24x"
		Then worker 2 has the password "u290@yuj24"
		And the message "Successfully changed 2(Bob)'s password" is returned	
	
# TODO password rules
	
	Scenario: An admin registers a new worker
		Given worker 1 is signed in
		And worker 1 is registered as an admin
		When admin 1 registeres new worker 2 under the name "Bob" with the password "MySup3rAws0m3Passw0rd"
		Then worker 2 exists
		And worker 2 has the name "Bob"
		And worker 2 has the password "MySup3rAws0m3Passw0rd"
		And the message "Successfully created 2(Bob)" is returned
		
		
# TODO registering new admins
	