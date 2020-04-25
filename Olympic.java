package phase2;

import java.sql.*;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Olympic {
	 private static final String username = "yid33";
	 private static final String password = "4377744";
	 private static final String url = "jdbc:oracle:thin:@class3.cs.pitt.edu:1521:dbclass";
	 private static Connection connection=null;//
	 private static PreparedStatement ps=null;
	 private static Scanner j = new Scanner(System.in);
	 public static Connection getConnection() {
			try {
				DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
				connection=(Connection) DriverManager.getConnection(url, username, password);
			
			} catch (Exception e) {
				e.printStackTrace();//
			}
			return connection;
		}

	 public static void createUser(){     //////OK

		 try {

			 String filter=j.nextLine();
			 System.out.println("input user_name ：");
			 String user_name=j.nextLine();
			 System.out.println("input role_id：");
			 int role_id=j.nextInt();
			 System.out.println("input passkey：");
			 String passkey=j.next();
			 
	
			 
			 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//set the initial format of time stamp
	 		 java.sql.Date time = new java.sql.Date(df.parse(df.format(new java.util.Date())).getTime());  
	 		 
			 Statement st = connection.createStatement();
			 String query = "select max(user_id) from USER_ACCOUNT";
			 ResultSet res1 = st.executeQuery(query);
			 res1.next();
			 int user_id = res1.getInt(1) + 1;
			 String String="insert into USER_ACCOUNT values(?,?,?,?,?)";
			 ps=connection.prepareStatement(String);
			 ps.setInt(1,user_id );                        
			 ps.setString(2, user_name);
			 ps.setString(3, passkey);
			 ps.setInt(4, role_id);
			 ps.setDate(5,time);           
			 int executeUpdate = ps.executeUpdate();
			 System.out.println(executeUpdate);
		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 
	 public static void dropUser(){          ///////OK
		 try {
			 System.out.println("input id ：");
			 int id=j.nextInt();
			 String String="delete from USER_ACCOUNT where user_id=?";
			 ps=connection.prepareStatement(String);
			 ps.setInt(1,id);
			 int executedelete = ps.executeUpdate();
			 System.out.println(executedelete);
			 ps.close();

		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 
	 public static void createEvent(){   ///////////OK

		 try {
			 System.out.println("input sportId ：");
			 int sport_id=j.nextInt();
			 System.out.println("input venueId ：");
			 int venue_id=j.nextInt();
			 System.out.println("input datetime(yyyy-MM-dd) ：");
			 String datetime1=j.next();
			 System.out.println("input datetime(hh:mm:ss) ：");
			 String datetime2=j.next();
			 System.out.println("input gender ：");
			 int gender=j.nextInt();
			 String time=datetime1+" "+datetime2;
			 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		     java.sql.Date event_time = new java.sql.Date(df.parse(time).getTime());
			
			 Statement st = connection.createStatement();
			 String query = "select max(event_id) from EVENT";
			 ResultSet res1 = st.executeQuery(query);
			 res1.next();
			 int event_id = res1.getInt(1) + 1;
			 String String2="insert into EVENT values(?,?,?,?,?)";
			 ps=connection.prepareStatement(String2);  
			 ps.setInt(1,event_id);                    
			 ps.setInt(2,sport_id);
			 ps.setInt(3,venue_id);
			 ps.setInt(4,gender);
			 ps.setDate(5,event_time);         //problem
			 int executeinsert = ps.executeUpdate();
			 System.out.println(executeinsert);
			 
			 st.close();
			 ps.close();

		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 
	 public static void addEventOutcome(){    ///////Ok
		 //getConnection();
		 try {

			 System.out.println("input Olympic_id ：");
			 int  Oly_id=j.nextInt();
			 System.out.println("input event_id ：");
			 int event_id=j.nextInt();
			 System.out.println("input participant_id ：");
			 int participant_id=j.nextInt();
			 System.out.println("input position ：");
			 int position=j.nextInt();
			 System.out.println("input team_id ：");
			 int team_id=j.nextInt();

			 
			 String String2="insert into SCOREBOARD values(?,?,?,?,?,?)";
			 ps=connection.prepareStatement(String2);
			 ps.setInt(1,Oly_id);
			 ps.setInt(2,event_id);
			 ps.setInt(3,team_id);
			 ps.setInt(4,participant_id);
			 ps.setInt(5,position);
			 ps.setInt(6,1);         ////trigger will modify this value automatically
			 int executeinsert = ps.executeUpdate();
			 System.out.println(executeinsert);
			 
			 ps.close();
		
		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 
	 public static void createTeam(){  /////////////OK
		 //getConnection();
		 try {

			 System.out.println("input Olympic_city ：");
			 String  Oly_city=j.next();
			 System.out.println("input Olympic_year ：");
			 int  Oly_year=j.nextInt();
			 System.out.println("input sport_id ：");
			 int sport_id=j.nextInt();
			 System.out.println("input country：");
			 String country=j.next();
			 System.out.println("input team_name ：");
			 String team_name=j.next();
			 System.out.println("input your coach_id：");
			 int coach_id=j.nextInt();
			 
			 String String="select olympic_id from OLYMPICS where host_city=? ";
			 ps=connection.prepareStatement(String);
			 ps.setString(1,Oly_city);
			 ResultSet res = ps.executeQuery();
			 res.next();
			 int olympic_id=res.getInt("olympic_id");
			 
			 String String2="select country_id from COUNTRY where country=? ";
			 ps=connection.prepareStatement(String2);
			 ps.setString(1,country);
			 ResultSet res2 = ps.executeQuery();
			 res2.next();
			 int country_id=res2.getInt("country_id");
			 
			 Statement st = connection.createStatement();
			 String query = "select max(team_id) from TEAM";
			 ResultSet res3 = st.executeQuery(query);
			 res3.next();
			 int team_id = res3.getInt(1) + 1;
			 String String3="insert into TEAM values(?,?,?,?,?,?)";
			 ps=connection.prepareStatement(String3);
			 ps.setInt(1,team_id);           
			 ps.setInt(2,olympic_id);
			 ps.setString(3,team_name);
			 ps.setInt(4,country_id);
			 ps.setInt(5,sport_id);
			 ps.setInt(6,coach_id);
			 int executeinsert = ps.executeUpdate();
			 System.out.println(executeinsert);
			 
			 st.close();
			 ps.close();

		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 
	 public static void registerTeam(){    //////////////OK
		 //getConnection();
		 try {

			 System.out.println("input teamId ：");
			 int team_id=j.nextInt();
			 System.out.println("input eventId ：");
			 int event_id=j.nextInt();
			 
			 String String2="insert into EVENT_PARTICIPATION values(?,?,?)";
			 ps=connection.prepareStatement(String2);  
			 ps.setInt(1,event_id);                       
			 ps.setInt(2,team_id);
			 ps.setString(3,"e");
			 
			 int executeinsert = ps.executeUpdate();
			 System.out.println(executeinsert);
			 
			 ps.close();
	
		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 
	 public static void addParticipant(){    ///////OK
		 //getConnection();
		 try {
	
			 System.out.println("input fname ：");
			 String fname=j.next();
			 System.out.println("input lname：");
			 String lname=j.next();
			 System.out.println("input nationality ：");
			 String nationality=j.next();
			 System.out.println("input birthcity：");
			 String birthcity=j.next();
			 System.out.println("input birthdate(yyyy-MM-dd) ：");
			 String dob=j.next();
			 
			 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		     java.sql.Date birthdate = new java.sql.Date(df.parse(dob).getTime());
			 
			 Statement st = connection.createStatement();
			 String query = "select max(participant_id) from PARTICIPANT";
			 ResultSet res1 = st.executeQuery(query);
			 res1.next();
			 int participant_id = res1.getInt(1) + 1;
			 String String="insert into PARTICIPANT values(?,?,?,?,?,?)";
			 ps=connection.prepareStatement(String);  
			 ps.setInt(1,participant_id);                           
			 ps.setString(2,fname);
			 ps.setString(3,lname);
			 ps.setString(4,nationality);
			 ps.setString(5,birthcity);
			 ps.setDate(6,birthdate);
			 
			 int executeinsert = ps.executeUpdate();
			 System.out.println(executeinsert);
			 
			 st.close();
			 ps.close();
	
		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 	
	 public static void addTeamMember(){     /////OK
		 //getConnection();
		 try {

			 System.out.println("input teamId ：");
			 int team_id=j.nextInt();
			 System.out.println("input participantId ：");
			 int participant_id=j.nextInt();
			 
			 String String="insert into TEAM_MEMBER values(?,?)";
			 ps=connection.prepareStatement(String);  
			 ps.setInt(1,team_id);                       
			 ps.setInt(2,participant_id);
			 
			 int executeinsert = ps.executeUpdate();
			 System.out.println(executeinsert);
			 
			 ps.close();
	
		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 
	 public static void dropTeamMember(){
		 //getConnection();
		 try {
			 System.out.println("input user_id ：");
			 int user_id=j.nextInt();
			 
			 String String="delete from USER_ACCOUNT where user_id=? and role_id=3";
			 ps=connection.prepareStatement(String);
			 ps.setInt(1,user_id);
			 int executedelete = ps.executeUpdate();
			 System.out.println(executedelete);
			 
			 ps.close();

		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 
	 
	 
	 public static void displaySport(){   /////////////OK
		 //getConnection();
		 try {
			 String filter=j.nextLine();
			 System.out.println("input sport_name ：");
			 String sport_name=j.nextLine();
			 
			 String String="select distinct esp.participant_id ,st.dob,esp.event_id,esp.gender ,esp.nationality\r\n" + 
			 		"from SPORT st join\r\n" + 
			 		"(\r\n" + 
			 		"select e.event_id,e.sport_id,e.gender,sp.participant_id,sp.nationality,sp.medal_id,e.event_time\r\n" + 
			 		"from EVENT e join(\r\n" + 
			 		"select s.event_id,s.participant_id,p.nationality,s.medal_id\r\n" + 
			 		"from  SCOREBOARD s join PARTICIPANT p on s.PARTICIPANT_ID = p.PARTICIPANT_ID\r\n" + 
			 		"where medal_id is not null\r\n" + 
			 		"order by medal_id asc) sp on e.event_id= sp.event_id order by event_time asc) esp on st.sport_id=esp.sport_id\r\n" + 
			 		"where st.sport_name=?";
			 ps=connection.prepareStatement(String);
			 ps.setString(1,sport_name);
			 ResultSet rst = ps.executeQuery();
			 int revent_id,rparticipant_id,rgender;
			 String rdob,rnationality;
			 while (rst.next()) {
		            rdob = rst.getString("dob");
		            revent_id = rst.getInt("event_id");
		            rgender = rst.getInt("gender");
		            rparticipant_id = rst.getInt("participant_id");
		            rnationality = rst.getString("nationality");
		            System.out.println(rdob+ " " + revent_id+ " " + rgender+ " " +rparticipant_id+ " " +rnationality);
		        }

			 ps.close();
	
		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 
	 
	 public static void displayEvent(){    ////////OK
		 //getConnection();
		 try {
			 System.out.println("input Olympic_city ：");
			 String  Oly_city=j.next();
			 System.out.println("input Olympic_year ：");
			 int  Oly_year=j.nextInt();
			 System.out.println("input event_id ：");
			 int  event_id=j.nextInt();
			 
			 String String="select s.olympic_id,s.participant_id,s.position,s.MEDAL_ID\r\n" + 
			 		"from SCOREBOARD s join (\r\n" + 
			 		"select OLYMPIC_ID from OLYMPICS where host_city=?) o on o.OLYMPIC_ID=s.OLYMPIC_ID\r\n" + 
			 		"where event_id=? and medal_id is not null";
			 ps=connection.prepareStatement(String);
			 ps.setString(1,Oly_city);
			 ps.setInt(2,event_id);
			 ResultSet rst = ps.executeQuery();
			 int rolympic_id,rparticipant_id,rposition,rmedal_id;
			 while (rst.next()) {
				  rolympic_id= rst.getInt("olympic_id");
				  rparticipant_id = rst.getInt("participant_id");
				  rposition= rst.getInt("position");
				  rmedal_id = rst.getInt("medal_id");
		          System.out.println("olympic_id:"+rolympic_id+ " " +"participant_id:"+ rparticipant_id+ " " + "position:"+rposition+ " " +"medal_id:"+rmedal_id);
		        }
			 ps.close();
		
		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 
	 public static void countryRanking(){  /////////////OK
		 //getConnection();
		 try {
			 System.out.println("input Olympic_id ：");
			 int  Oly_id=j.nextInt();

			 String String="select smeco.country_code, sum(smeco.points) as points,min(smeco.opening) as opening,\r\n" + 
			 		"(select count(country_code) from ( select smec.points, smec.country_code,smec.OLYMPIC_ID,o.opening,smec.medal_id from OLYMPICS o join(select  sme.points, c.country_code,sme.medal_id,sme.OLYMPIC_ID from COUNTRY c join(select e.team_id, e.country_id,sm.olympic_id,sm.medal_id,sm.points from TEAM e join(select * from SCOREBOARD s natural join MEDAL m) sm on e.team_id=sm.team_id) sme on sme.COUNTRY_ID=c.country_id) smec on o.olympic_id= smec.OLYMPIC_ID)smeco1 where smeco1.country_code = smeco.country_code AND smeco1.medal_id = 1 and smeco1.Olympic_id=?)gold ,\r\n" + 
			 		"(select count(country_code) from (select smec.points, smec.country_code,smec.OLYMPIC_ID,o.opening,smec.medal_id from OLYMPICS o join(select  sme.points, c.country_code,sme.medal_id,sme.OLYMPIC_ID from COUNTRY c join(select e.team_id, e.country_id,sm.olympic_id,sm.medal_id,sm.points from TEAM e join(select * from SCOREBOARD s natural join MEDAL m) sm on e.team_id=sm.team_id) sme on sme.COUNTRY_ID=c.country_id) smec on o.olympic_id= smec.OLYMPIC_ID)smeco1 where smeco1.country_code = smeco.country_code AND smeco1.medal_id = 2 and smeco1.Olympic_id=?)silver,\r\n" + 
			 		"(select count(country_code) from (select smec.points, smec.country_code,smec.OLYMPIC_ID,o.opening,smec.medal_id from OLYMPICS o join(select  sme.points, c.country_code,sme.medal_id,sme.OLYMPIC_ID from COUNTRY c join(select e.team_id, e.country_id,sm.olympic_id,sm.medal_id,sm.points from TEAM e join(select * from SCOREBOARD s natural join MEDAL m) sm on e.team_id=sm.team_id) sme on sme.COUNTRY_ID=c.country_id) smec on o.olympic_id= smec.OLYMPIC_ID)smeco1 where smeco1.country_code = smeco.country_code AND smeco1.medal_id = 3 and smeco1.Olympic_id=?)bronze\r\n" + 
			 		"from(select smec.points, smec.country_code,smec.OLYMPIC_ID,o.opening,smec.medal_id from OLYMPICS o join\r\n" + 
			 		"(select  sme.points, c.country_code,sme.medal_id,sme.OLYMPIC_ID\r\n" + 
			 		"from COUNTRY c join\r\n" + 
			 		"    (select e.team_id, e.country_id,sm.olympic_id,sm.medal_id,sm.points\r\n" + 
			 		"from TEAM e join\r\n" + 
			 		"(select * from SCOREBOARD s natural join MEDAL m) sm on e.team_id=sm.team_id) sme on sme.COUNTRY_ID=c.country_id) smec on o.olympic_id= smec.OLYMPIC_ID)smeco\r\n" + 
			 		"where smeco.Olympic_id=?\r\n" + 
			 		"group by smeco.country_code\r\n" + 
			 		"order by points desc";           /////////////
			 ps=connection.prepareStatement(String);
			 ps.setInt(1,Oly_id);
			 ps.setInt(2,Oly_id);
			 ps.setInt(3,Oly_id);
			 ps.setInt(4,Oly_id);
			 ResultSet rst = ps.executeQuery();
			 String rcountry_abbreviation,ropening;
			 int rpoints,rgold,rsilver,rbronze;
			 while (rst.next()) {
				  rcountry_abbreviation= rst.getString("country_code");
				  ropening= rst.getString("opening");
				  rpoints = rst.getInt("points");
				  rgold= rst.getInt("gold");
				  rsilver = rst.getInt("silver");
				  rbronze = rst.getInt("bronze");
		          System.out.println( rcountry_abbreviation+ " "  +rpoints + " "+ropening+  " " + rgold+ " " +rsilver+ " " +rbronze);
		        }
			 ps.close();

		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 
	 public static void topkAthletes(){  ////////////OK
		 //getConnection();
		 try {

			 System.out.println("input Olympic_id ：");
			 int  Oly_id=j.nextInt();
			 System.out.println("input k ：");
			 int  k=j.nextInt();

			 
			 String String="select sm.participant_id,sum(sm.points) as points,\r\n" + 
			 		"(select count(participant_id) from (select * from SCOREBOARD natural join MEDAL) sm1 where sm1.participant_id = sm.participant_id AND sm1.medal_id = 1)gold ,\r\n" + 
			 		"(select count(participant_id) from (select * from SCOREBOARD natural join MEDAL) sm1 where sm1.participant_id = sm.participant_id AND sm1.medal_id = 2)silver,\r\n" + 
			 		"(select count(participant_id) from (select * from SCOREBOARD natural join MEDAL) sm1 where sm1.participant_id = sm.participant_id AND sm1.medal_id = 3)bronze\r\n" + 
			 		"from  ( select * from SCOREBOARD natural join MEDAL) sm\r\n" + 
			 		"where OLYMPIC_ID=? \r\n" + 
			 		"group by sm.participant_id\r\n" + 
			 		"order by points desc\r\n" + 
			 		"fetch first ? rows only";           /////////////
			 ps=connection.prepareStatement(String);
			 ps.setInt(1,Oly_id);
			 ps.setInt(2,k);
			 ResultSet rst = ps.executeQuery();
			 int rparticipant_id,rpoints,rgold,rsilver,rbronze;
			 while (rst.next()) {
				  rparticipant_id= rst.getInt("participant_id");
				  rpoints = rst.getInt("points");
				  rgold= rst.getInt("gold");
				  rsilver = rst.getInt("silver");
				  rbronze = rst.getInt("bronze");
		          System.out.println( "participant_id:"+rparticipant_id+ " " +"points:"+ rpoints+ " " + "gold:"+rgold+ " " +"silver:"+rsilver+ " " +"bronze:"+rbronze);
		        }
			 ps.close();

		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 
	 public static void connectedAthletes(){
		 //getConnection();
		 try {
			 System.out.println("input athlete_id ：");
			 int  participant_id=j.nextInt();
			 System.out.println("input Olympic_id ：");
			 int  Oly_id=j.nextInt();
			 System.out.println("input n ：");
			 int  n=j.nextInt();

			 String String="select distinct po1.participant_id from\r\n" + 
			 		"(select distinct s.participant_id from SCOREBOARD s join EVENT e on s.event_id = e.event_id where s.participant_id not in\r\n" + 
			 		"(select distinct s.participant_id from SCOREBOARD s join EVENT e on s.event_id = e.event_id where s.participant_id != ? and s.event_id in\r\n" + 
			 		"(select event_id from SCOREBOARD s where participant_id = ? and olympic_id = ?))and s.event_id in\r\n" + 
			 		"(select event_id from SCOREBOARD s where participant_id in\r\n" + 
			 		"(select distinct s.participant_id from SCOREBOARD s join EVENT e on s.event_id = e.event_id where s.participant_id != ? and s.event_id in\r\n" + 
			 		"(select event_id from SCOREBOARD s where participant_id = ? and olympic_id = ?)) and olympic_id = ?))po1 cross join\r\n" + 
			 		"(select participant_id from SCOREBOARD where participant_id = ? and olympic_id = ?)po2"; 
			
			 ps=connection.prepareStatement(String);
			 ps.setInt(1,participant_id);
			 ps.setInt(2,participant_id);
			 ps.setInt(3,Oly_id);
			 ps.setInt(4,participant_id);
			 ps.setInt(5,participant_id);
			 ps.setInt(6,Oly_id);
			 ps.setInt(7,n);
			 ps.setInt(8,participant_id);
			 ps.setInt(9,Oly_id);
			 ResultSet rst = ps.executeQuery();
			 int rparticipant_id;
			 while (rst.next()) {
				  rparticipant_id= rst.getInt("participant_id");			
		          System.out.println( rparticipant_id);
		        }
			 ps.close();
	
		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 
	 
  
	 public static int login() throws SQLException {
		   //getConnection();
		   System.out.println("username = ");
		   String username = j.nextLine();
		   System.out.println("password = ");
		   String password = j.nextLine();
		   
		   Statement st = connection.createStatement();
		   String query1 = "select username,passkey,role_id from user_account";
		   ResultSet res1 = st.executeQuery(query1);
		         
		   int rol ;
		   String rid,rpa;
	
		   while(res1.next()) {
		     rid = res1.getString(1);
		     rpa = res1.getString(2);
		     rol = res1.getInt(3);
		     if(rid.equals(username) && rpa.equals(password)) {
		       return(rol);
                   }}
				 return (-1);}

	 	public static void logout() throws SQLException, ParseException{
	 		   System.out.println("your user_id = ");
			   int user_id = j.nextInt();
	 		   SimpleDateFormat df = new SimpleDateFormat("yyyy-mmm-dd HH:mm:ss");//set the initial format of time stamp
	 		   java.sql.Date time = new java.sql.Date(df.parse(df.format(new java.util.Date())).getTime());
	 		   
	 		   String String= "update USER_ACCOUNT set last_login=? where user_id=?";
	 		   ps=connection.prepareStatement(String);
			   ps.setDate(1,time);
			   ps.setInt(2,user_id);
			   ps.executeQuery();
			   ps.close();
	 		   
	 	}
	 	
	 	
	 public static void exit() throws SQLException{
	 		   j.close();
	 		   System.exit(0);   
	 		}
	 
	 
	 public static void main(String args[]) throws SQLException, ParseException {
		getConnection();

		int a=login();
		//System.out.println(a);
		if(a==1) {
			System.out.println("1:createUser"+" "+"2:dropUser"+" "+"3:createEvent"+" "+"4:addEventOutcome"+" "+"5:displaySport"+" "+"6:displayEvent"+" "+"7:countryRanking"+" "+"8:topkAthletes"+" "+"9:connectedAthletes"+" "+"10:logout"+" "+"11:exit");
			System.out.println("what do you want to do ?  please input the number: ");
			int Org = j.nextInt();
			while(Org>0 && Org<12) {
		    	 switch(Org){
		    	 case 1: createUser();break;
		    	 case 2:dropUser();break;
		    	 case 3: createEvent();break;
		    	 case 4: addEventOutcome();break;
		    	 case 5: displaySport();break;
		    	 case 6: displayEvent();break;
		    	 case 7: countryRanking();break;
		    	 case 8: topkAthletes();break;
		    	 case 9: connectedAthletes();break;
		    	 case 10: logout();break;
		    	 case 11: exit();break;
		    	 }
		     System.out.println("please input number again:");
		     Org=j.nextInt();
		     }}
		else if(a==2) {
			System.out.println("1:createTeam"+" "+"2:registerTeam"+" "+"3:addParticipant"+" "+"4:addTeamMember"+" "+"5:dropTeamMember"+" "+"6:displaySport"+" "+"7:displayEvent"+" "+"8:countryRanking"+" "+"9:topkAthletes"+" "+"10:connectedAthletes"+" "+"11:logout"+" "+"12:exit");
			System.out.println("what do you want to do ?  please input the number: ");
			int Coa = j.nextInt();
			while(Coa>0 && Coa<13) {
		    	 switch(Coa){
		    	 case 1: createTeam();break;
		    	 case 2:registerTeam();break;
		    	 case 3: addParticipant();break;
		    	 case 4: addTeamMember();break;
		    	 case 5: dropTeamMember();break;
		    	 case 6: displaySport();break;
		    	 case 7: displayEvent();break;
		    	 case 8: countryRanking();break;
		    	 case 9: topkAthletes();break;
		    	 case 10: connectedAthletes();break;
		    	 case 11: logout();break;
		    	 case 12: exit();break;
		    	 }
		     System.out.println("please input number again:");
		     Coa=j.nextInt();
		     }}
		else if(a==3) {
			System.out.println("1:displaySport"+" "+"2:displayEvent"+" "+"3:countryRanking"+" "+"4:topkAthletes"+" "+"5:connectedAthletes"+" "+"6:logout"+" "+"7:exit");
			System.out.println("what do you want to do ?  please input the number: ");
			int All= j.nextInt();
			while(All>0 && All<8) {
		    	 switch(All){
		    	 case 1: displaySport();break;
		    	 case 2: displayEvent();break;
		    	 case 3: countryRanking();break;
		    	 case 4: topkAthletes();break;
		    	 case 5: connectedAthletes();break;
		    	 case 6: logout();break;
		    	 case 7: exit();break;
		    	 }
		     System.out.println("please input number again:");
		     All=j.nextInt();
		     }
	 }
		connection.close();
	
	 }
}



