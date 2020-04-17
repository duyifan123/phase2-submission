package demo;

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

	 public static void createUser(){     //  role_id?? problem
		 //getConnection();
		 try {
			 //Scanner j=new Scanner(System.in);
			 System.out.println("input fname ：");
			 String fname=j.nextLine();
			 System.out.println("input lname：");
			 String lname=j.nextLine();
			 System.out.println("input nationality ：");
			 String nationality=j.next();
			 System.out.println("input birthcity：");
			 String birthcity=j.nextLine();
			 System.out.println("input birthcountry：");
			 String birthcountry=j.nextLine();
			 System.out.println("input bithdate ：");
			 String birthdate= j.nextLine();
			 System.out.println("input sport：");
			 String sport=j.next();
			 
			 SimpleDateFormat df = new SimpleDateFormat("yyyy-mon-dd HH:mm:ss");//set the initial format of time stamp
	 		 java.sql.Date time = new java.sql.Date(df.parse(df.format(new java.util.Date())).getTime());
	 		   
			 Statement st = connection.createStatement();
			 String query = "select max(sid) from student";
			 ResultSet res1 = st.executeQuery(query);
			 res1.next();
			 int user_id = res1.getInt(1) + 1;
			 String String="insert into USER_ACCOUNT values(?,?,?,?,?)";
			 ps=connection.prepareStatement(String);
			 ps.setInt(1,user_id );                        
			 ps.setString(2, fname+" "+lname);
			 ps.setString(3, "GUEST");
			 ps.setInt(4, 3);
			 ps.setDate(5,time);           
			 int executeUpdate = ps.executeUpdate();
			 System.out.println(executeUpdate);
		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 
	 public static void dropUser(){
		 //getConnection();
		 try {
			 //Scanner j=new Scanner(System.in);
			 System.out.println("input id ：");
			 int id=j.nextInt();
			 String String="delete from USER_ACCOUNT where user_id=?";
			 ps=connection.prepareStatement(String);
			 ps.setInt(1,id);
			 int executedelete = ps.executeUpdate();
			 System.out.println(executedelete);
			 ps.close();
			 //j.close();
			 //connection.close();
		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 
	 public static void createEvent(){
		 //getConnection();
		 try {
			 //Scanner j=new Scanner(System.in);
			 System.out.println("input sportId ：");
			 int sport_id=j.nextInt();
			 System.out.println("input venueId ：");
			 int venue_id=j.nextInt();
			 System.out.println("input datetime ：");
			 String datetime=j.next();
			 System.out.println("input gender ：");
			 int gender=j.nextInt();
			
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
			 ps.setString(5,datetime);         //problem
			 int executeinsert = ps.executeUpdate();
			 System.out.println(executeinsert);
			 
			 st.close();
			 ps.close();
			 //j.close();
			 //connection.close();
		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 
	 public static void addEventOutcome(){
		 //getConnection();
		 try {
			 //Scanner j=new Scanner(System.in);
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
			 
			 //String String="select team_id from TEAM_MEMBER where participant_id=? ";
			 //ps=connection.prepareStatement(String);
			 //ps.setInt(1,participant_id);
			 //ResultSet res = ps.executeQuery();
			 //res.next();
			 //int team_id=res.getInt("team_id");
			 
			 String String2="insert into SCOREBOARD values(?,?,?,?,?,?)";
			 ps=connection.prepareStatement(String2);
			 ps.setInt(1,Oly_id);
			 ps.setInt(2,event_id);
			 ps.setInt(3,team_id);
			 ps.setInt(4,participant_id);
			 ps.setInt(5,position);
			 ps.setInt(6,0);
			 int executeinsert = ps.executeUpdate();
			 System.out.println(executeinsert);
			 
			 ps.close();
			 //j.close();
			 //connection.close();
		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 
	 public static void createTeam(){
		 //getConnection();
		 try {
			 //Scanner j=new Scanner(System.in);
			 System.out.println("input Olympic_city ：");
			 String  Oly_city=j.nextLine();
			 System.out.println("input Olympic_year ：");
			 int  Oly_year=j.nextInt();
			 System.out.println("input sport_id ：");
			 int sport_id=j.nextInt();
			 System.out.println("input country：");
			 String country=j.nextLine();
			 System.out.println("input team_name ：");
			 String team_name=j.nextLine();
			 System.out.println("input your coach_id：");
			 int coach_id=j.nextInt();
			 
			 String String="select olympic_id frm OLYMPICS where host_city=? ";
			 ps=connection.prepareStatement(String);
			 ps.setString(1,Oly_city);
			 ResultSet res = ps.executeQuery();
			 res.next();
			 int olympic_id=res.getInt("olympic_id");
			 
			 String String2="select country_id frm COUNTRY where country=? ";
			 ps=connection.prepareStatement(String2);
			 ps.setString(1,country);
			 ResultSet res2 = ps.executeQuery();
			 res2.next();
			 int country_id=res.getInt("country_id");
			 
			 Statement st = connection.createStatement();
			 String query = "select max(team_id) from TEAM";
			 ResultSet res1 = st.executeQuery(query);
			 res1.next();
			 int team_id = res1.getInt(1) + 1;
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
			 //j.close();
			 //connection.close();
		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 
	 public static void registerTeam(){
		 //getConnection();
		 try {
			 //Scanner j=new Scanner(System.in);
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
			 //j.close();
			 //connection.close();
		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 
	 public static void addParticipant(){
		 //getConnection();
		 try {
			 //Scanner j=new Scanner(System.in);
			 System.out.println("input fname ：");
			 String fname=j.nextLine();
			 System.out.println("input lname：");
			 String lname=j.nextLine();
			 System.out.println("input nationality ：");
			 String nationality=j.nextLine();
			 System.out.println("input birthcity：");
			 String birthcity=j.nextLine();
			 System.out.println("input birthdate ：");
			 String birthdate= j.nextLine();
			 
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
			 ps.setString(6,birthdate);
			 
			 int executeinsert = ps.executeUpdate();
			 System.out.println(executeinsert);
			 
			 st.close();
			 ps.close();
			// j.close();
			 //connection.close();
		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 	
	 public static void addTeamMember(){
		 //getConnection();
		 try {
			 //Scanner j=new Scanner(System.in);
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
			 //j.close();
			 //connection.close();
		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 
	 public static void dropTeamMember(){
		 //getConnection();
		 try {
			 //Scanner j=new Scanner(System.in);
			 System.out.println("input user_id ：");
			 int user_id=j.nextInt();
			 
			 String String="delete from USER_ACCOUNT where user_id=?";
			 ps=connection.prepareStatement(String);
			 ps.setInt(1,user_id);
			 int executedelete = ps.executeUpdate();
			 System.out.println(executedelete);
			 
			 ps.close();
			 //j.close();
			 //connection.close();
		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 
	 
	 
	 public static void displaySport(){
		 //getConnection();
		 try {
			 //Scanner j=new Scanner(System.in);
			 System.out.println("input sport_name ：");
			 String sport_name=j.nextLine();
			 
			 String String="select st.dob,esp.event_id,esp.gender,esp.participant_id,esp.nationality\r\n" + 
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
			 //j.close();
			 //connection.close();
		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 
	 
	 public static void displayEvent(){
		 //getConnection();
		 try {
			 //Scanner j=new Scanner(System.in);
			 System.out.println("input Olympic_city ：");
			 String  Oly_city=j.nextLine();
			 System.out.println("input Olympic_year ：");
			 int  Oly_year=j.nextInt();
			 System.out.println("input event_id ：");
			 int  event_id=j.nextInt();
			 
			 String String="select olympic_id,participant_id,position,MEDAL_ID\r\n" + 
			 		"from SCOREBOARD join (\r\n" + 
			 		"select OLYMPIC_ID from OLYMPICS where host_city=?) o on o.OLYMPIC_ID=OLYMPIC_ID\r\n" + 
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
		          System.out.println(rolympic_id+ " " + rparticipant_id+ " " + rposition+ " " +rmedal_id);
		        }
			 ps.close();
			// j.close();
			 //connection.close();
		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 
	 public static void countryRanking(){
		 //getConnection();
		 try {
			 //Scanner j=new Scanner(System.in);
			 System.out.println("input Olympic_id ：");
			 int  Oly_id=j.nextInt();

			 String String="select smeco.country_code, sum(smeco.points) as points,min(smeco.opening),\r\n" + 
			 		"(select count(country_code) from ( select smec.points, smec.country_code,smec.OLYMPIC_ID,o.opening,smec.medal_id from OLYMPICS o join(select  sme.points, c.country_code,sme.medal_id,sme.OLYMPIC_ID from COUNTRY c join(select e.team_id, e.country_id,sm.olympic_id,sm.medal_id,sm.points from TEAM e join(select * from SCOREBOARD s natural join MEDAL m) sm on e.team_id=sm.team_id) sme on sme.COUNTRY_ID=c.country_id) smec on o.olympic_id= smec.OLYMPIC_ID)smeco1  where smeco1.country_code = smeco.country_code AND smeco1.medal_id = 1 and smeco1.Olympic_id=?)gold ,\r\n" + 
			 		"(select count(country_code) from (select smec.points, smec.country_code,smec.OLYMPIC_ID,o.opening,smec.medal_id from OLYMPICS o join(select  sme.points, c.country_code,sme.medal_id,sme.OLYMPIC_ID from COUNTRY c join(select e.team_id, e.country_id,sm.olympic_id,sm.medal_id,sm.points from TEAM e join(select * from SCOREBOARD s natural join MEDAL m) sm on e.team_id=sm.team_id) sme on sme.COUNTRY_ID=c.country_id) smec on o.olympic_id= smec.OLYMPIC_ID)smeco1 where smeco1.country_code = smeco.country_code AND smeco1.medal_id = 2 and smeco1.Olympic_id=?)silver,\r\n" + 
			 		"(select count(country_code) from (select smec.points, smec.country_code,smec.OLYMPIC_ID,o.opening,smec.medal_id from OLYMPICS o join(select  sme.points, c.country_code,sme.medal_id,sme.OLYMPIC_ID from COUNTRY c join(select e.team_id, e.country_id,sm.olympic_id,sm.medal_id,sm.points from TEAM e join(select * from SCOREBOARD s natural join MEDAL m) sm on e.team_id=sm.team_id) sme on sme.COUNTRY_ID=c.country_id) smec on o.olympic_id= smec.OLYMPIC_ID)smeco1 where smeco1.country_code = smeco.country_code AND smeco1.medal_id = 3 and and smeco1.Olympic_id=?)bronze\r\n" + 
			 		"from(select smec.points, smec.country_code,smec.OLYMPIC_ID,o.opening,smec.medal_id from OLYMPICS o join(select  sme.points, c.country_code,sme.medal_id,sme.OLYMPIC_ID from COUNTRY c join(select e.team_id, e.country_id,sm.olympic_id,sm.medal_id,sm.points from TEAM e join(select * from SCOREBOARD s natural join MEDAL m) sm on e.team_id=sm.team_id) sme on sme.COUNTRY_ID=c.country_id) smec on o.olympic_id= smec.OLYMPIC_ID)smeco\r\n" + 
			 		"where smeco.Olympic_id=?\r\n" + 
			 		"group by smeco.country_code\r\n" + 
			 		"order by points desc;";           /////////////
			 ps=connection.prepareStatement(String);
			 ps.setInt(1,Oly_id);
			 ps.setInt(2,Oly_id);
			 ps.setInt(3,Oly_id);
			 ps.setInt(4,Oly_id);
			 ResultSet rst = ps.executeQuery();
			 String rcountry_abbreviation;
			 int rpoints,rgold,rsilver,rbronze;
			 while (rst.next()) {
				  rcountry_abbreviation= rst.getString("country_code");
				  rpoints = rst.getInt("points");
				  rgold= rst.getInt("gold");
				  rsilver = rst.getInt("silver");
				  rbronze = rst.getInt("bronze");
		          System.out.println( rcountry_abbreviation+ " " + rpoints+ " " + rgold+ " " +rsilver+ " " +rbronze);
		        }
			 ps.close();
			 //j.close();
			// connection.close();
		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 
	 public static void topkAthletes(){
		 //getConnection();
		 try {
			 //Scanner j=new Scanner(System.in);
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
		          System.out.println( rparticipant_id+ " " + rpoints+ " " + rgold+ " " +rsilver+ " " +rbronze);
		        }
			 ps.close();
			// j.close();
			// connection.close();
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

			 String String="select distinct s.participant_id, p.fname||' '||p.lname as fullname\r\n" + 
			 		"from SCOREBOARD s join EVENT e on e.event_id = s.event_id join PARTICIPANT p on p.participant_id = s.participant_id\r\n" + 
			 		"where e.event_id in\r\n" + 
			 		"(select distinct e.event_id\r\n" + 
			 		"from EVENT e left outer join SCOREBOARD s on s.event_id = e.event_id\r\n" + 
			 		"where s.olympic_id = ?\r\n" + 
			 		"order by event_time desc\r\n" + 
			 		"fetch first ? rows only) and s.participant_id not in\r\n" + 
			 		"(select tm.participant_id\r\n" + 
			 		"from TEAM_MEMBER tm join\r\n" + 
			 		"(select tm.team_id\r\n" + 
			 		"from TEAM_MEMBER tm join SCOREBOARD s on s.participant_id = tm.participant_id\r\n" + 
			 		"where tm.participant_id = ? and s.olympic_id = ?) tms on tm.team_id = tms.team_id\r\n" + 
			 		"where tm.participant_id != ? and tm.participant_id not in\r\n" + 
			 		"(select distinct tm.participant_id\r\n" + 
			 		"from TEAM_MEMBER tm join USER_ACCOUNT u on tm.participant_id = u.user_id\r\n" + 
			 		"where u.role_id = 2)) and s.participant_id != ?"; 
			
			 ps=connection.prepareStatement(String);
			 ps.setInt(1,Oly_id);
			 ps.setInt(2,n);
			 ps.setInt(3,participant_id);
			 ps.setInt(4,Oly_id);
			 ps.setInt(5,participant_id);
			 ps.setInt(6,participant_id);
			 ResultSet rst = ps.executeQuery();
			 int rparticipant_id;
			 String rfullname;
			 while (rst.next()) {
				  rparticipant_id= rst.getInt("participant_id");
				  rfullname = rst.getString("fullname");
		          System.out.println( rparticipant_id+ " " + rfullname);
		        }
			 ps.close();
			 //j.close();
			 //connection.close();
		 }catch (Exception e) {
			 e.printStackTrace();
	 }}
	 
	 
  
	 public static int login() throws SQLException {
		   //getConnection();
		   //Scanner j = new Scanner(System.in);
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
	 		   SimpleDateFormat df = new SimpleDateFormat("yyyy-mon-dd HH:mm:ss");//set the initial format of time stamp
	 		   java.sql.Date time = new java.sql.Date(df.parse(df.format(new java.util.Date())).getTime());
	 		   
	 		   String String= "alter table USER_ACCOUNT modify last_login ? where user_id=?";
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
			//Scanner j = new Scanner(System.in);
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

//public static String[] login() throws SQLException{     ///problem
//	 //getConnection();
//
//		 String[] array = new String[2]; 
//		 Scanner j=new Scanner(System.in);
//		 System.out.println("input username ：");
//		 String username1=j.nextLine();
//		 System.out.println("input password ：");
//		 String password1=j.nextLine();
//		 array[0] = username1;
//	     array[1] = password1;
//	
//		 return array;
//	
//}   


