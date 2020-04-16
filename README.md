# phase2-submission
phase2



My phase2 submission includes a "Olympic.java" file which includes one public class. I applyed functions includes sqlconnection, login 
, logout, exit, displaySport displayEvent countryRanking  topkAthletes connectedAthletes for all; createUser, dropUser, createEvent,
addEventOutcome for Organizer;createTeam,createTeam, addParticipant, addTeamMember, dropTeamMember for Coach. They wil be called in the
main function when you run the code. All functions or tasks can be implemented using the database approach.

When running the code, the java would connect to the oracle server automatically through JDBC. At the first menu, the user needs to input 
correct username and password, else print error and then the console will provide the second menu including corresponding permission 
function list(name and number) based on your idtentification(your role_id includes "1", "2", "3 ). Next, the user just needs to input 
corresponding number based on what you want to do and then you can do what you want to do until you input "exit" number or "logout" number. 
If you input "exit" number, the system will exit and finally the connetion will close. If you put "logout" number,system will return to the
first menu and user needs to input his username and password to login again.
