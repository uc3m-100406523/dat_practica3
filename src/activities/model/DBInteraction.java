package activities.model;

import java.sql.*;
import java.util.ArrayList;

public class DBInteraction {

	private static final String dblogin = "";
	private static final String dbpasswd = "";
    
	Query q;
	Connection con;

	//Constructor that connects to the Database
	public DBInteraction () throws SQLException {
		String url="jdbc:mysql://localhost/sporting_manager";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
        }
        try {
            System.out.println("Trying to connect...");
            con = DriverManager.getConnection (url, "root", "password");
            System.out.println("Connected!");
		}
		catch(SQLException ex) {
            System.err.print("SQLException: ");
            System.err.println(ex.getMessage());
        }
        q=new Query(con);
	}    

	//Method to close the database Connection
	public void close()throws Exception{
        q.close();
		con.close();
	}
	
    // Method to retrieve activities between two dates
    public ArrayList listactbydates(String startDate, String endDate) throws Exception {
        String selection = "SELECT * FROM ACTIVITIES WHERE START_DATE BETWEEN '" + startDate + "' AND '" + endDate + "'";
        ArrayList data = this.listactivities(selection);
        return data;
    }

	//Method to add a new user to the CLIENTS table
	public void addusr(String login, String pwd, String name, String surname, String address, String phone)
					throws Exception{
        String addusr="INSERT INTO CLIENTS VALUES ('"+login+"','"+pwd+"','"+name+"','"+surname+"','"+address+"','"+phone+"')";
	    q.doUpdate(addusr);
	}

	// This method returns 'true' in case there exists a row in the CLIENTS table with the login and password passed as parameters
	// If there is no such row exists then it returns 'false'	
	public boolean authentication(String login, String pwd)throws Exception{
		String list="SELECT * FROM CLIENTS WHERE LOGIN='"+login+"'";
		String password=null;
		ResultSet rs=q.doSelect(list); //rs will contain the row with login passed as parameter
		if (rs.next()){ //Check if the Resultset is empty
		    password = rs.getString(2);
		}
		if (password == null){
			return(false);
		}
		if(password.equals(pwd)){ // In case the password for this login in the table is the same as the one passed as parameter
			return(true);
		}
		else {
			return(false);
		}
	}
	
	//This method deletes the user whose login is passed as a parameter from the CLIENTS table.
	public void delusr(String login) throws Exception{
		String delusr="DELETE FROM CLIENTS WHERE LOGIN='"+login+"'";
		q.doUpdate(delusr);
	}

	//This method adds a new activity in the ACTIVITIES table using the data passed as parameters
	public void addact( String name, String description, String initial, float price, String pav_name, int total, int occ)throws Exception{
		String addactivity="INSERT INTO ACTIVITIES (NAME, DESCRIPTION, START_DATE, COST, PAVILLION_NAME, TOTAL_PLACES, OCCUPIED_PLACES) VALUES ('"+name+"','"+description+"','"+initial+"','"+price+"','"+pav_name+"','"+total+"','"+occ+"')";
		q.doUpdate(addactivity);
	}
	
	//This method deletes the row whose id is passed as a parameter from the ACTIVITIES table.   
	public void delact(int id) throws Exception{
		String delact="DELETE FROM ACTIVITIES WHERE ID='"+id+"'";
		q.doUpdate(delact);
	}

	//This method adds a new pavillion in the PAVILLIONS table using the data passed as parameters
	public void addpav(String pavname, String pavlocation) throws Exception{
		String addpavillion="INSERT INTO PAVILLIONS VALUES ('"+pavname+"','"+pavlocation+"')";
		q.doUpdate(addpavillion);
	}

	//This method deletes the pavillion whose name is passed as a parameter from the PAVILLIONS table.
	public void delpav(String pavname) throws Exception{
		String delpav="DELETE FROM PAVILLIONS WHERE PABELLON='"+pavname+"'";
		q.doUpdate(delpav);
	}

	//This method requests the execution of an SQL sentence for listing all the clients
	//and retrieves all the information for each client, storing each client as an element
	//of an array. Each element contains an object of the type Client
	public ArrayList listallusr() throws Exception{
		ArrayList data = new ArrayList();
		String selection="SELECT * FROM CLIENTS";
		ResultSet rs=q.doSelect(selection);
		while (rs.next()) {                     
		   String login = rs.getString(1);
		   String password = rs.getString(2);
           String name = rs.getString(3);
		   String surname = rs.getString(4);
           String address = rs.getString(5);
		   String phone = rs.getString(6);
           data.add(new Client(login, password, name, surname, address, phone));
	    }
		return (data);	
	}

	//This method is common to all the listing activities operations
	//It requests the execution of a SQL sentence for listing activities depending on some criterion
	//and retrieves all the information for each activity, storing each activity as an element
	//of an ArrayList (of Activity objects)
	public ArrayList listactivities(String selection) throws Exception{
		ArrayList data = new ArrayList();
		ResultSet rs=q.doSelect(selection);
		while (rs.next()) {                     
		    int id = rs.getInt(1);
			String name = rs.getString(2);
			String description = rs.getString(3);
			String initial = rs.getString(4);
			float cost = rs.getFloat(5);
			String pavname = rs.getString(6);
			int total = rs.getInt(7);
			int occupied = rs.getInt(8);
			data.add(new Activity (id, name, description, initial, cost, pavname, total, occupied));
		}
		return (data);
	}

	//This method builds an SQL sentence for listing all the activities
	public ArrayList listallact() throws Exception{
		String selection="SELECT * FROM ACTIVITIES";
		ArrayList data = this.listactivities(selection);
		return (data);	
	}

      // This method builds an SQL sentence for listing the activities that have free places
	public ArrayList listactfreeplaces() throws Exception{
		String selection="SELECT * FROM ACTIVITIES WHERE ACTIVITIES.TOTAL_PLACES > ACTIVITIES.OCCUPIED_PLACES";
		ArrayList data = this.listactivities(selection);
		return (data);
	}

	// This method builds an SQL sentence for listing the activities that cost less than a certain amount
	public ArrayList listactprice(float price) throws Exception{
		String selection="SELECT * FROM ACTIVITIES, PAVILLIONS WHERE ACTIVITIES.COST <="+price+"AND ACTIVITIES.PAVILLION_NAME = PAVILLIONS.PAVILLION";
		ArrayList data = this.listactivities(selection);
		return (data); 
	}

	// This method builds an SQL sentence for listing the activities that take place in a certain pavillion
	public ArrayList listactpav(String namepav) throws Exception{
		String selection="SELECT * FROM ACTIVITIES, PAVILLIONS WHERE ACTIVITIES.PAVILLION_NAME='"+namepav+"'AND ACTIVITIES.PAVILLION_NAME = PAVILLIONS.PAVILLION";
		ArrayList data = this.listactivities(selection);
		return (data);
	}

	// This method builds an SQL sentence for listing the activities that have a given name (should be only one!)
	public ArrayList listactname(String nameact) throws Exception{
		String selection="SELECT * FROM ACTIVITIES, PAVILLIONS WHERE ACTIVITIES.NAME='"+nameact+"'AND ACTIVITIES.PAVILLION_NAME = PAVILLIONS.PAVILLION";
		ArrayList data = this.listactivities(selection);
		return (data);
	}

	// This method builds an SQL sentence for listing the activities to which a specific client is subscribed
	public ArrayList listactusr(String login) throws Exception{
		String selection="SELECT ID, NAME, DESCRIPTION, START_DATE, COST, PAVILLION_NAME, TOTAL_PLACES, OCCUPIED_PLACES FROM SUBSCRIPTIONS, ACTIVITIES, PAVILLIONS WHERE SUBSCRIPTIONS.CLIENT_LOGIN='"+login+"' AND SUBSCRIPTIONS.ACTIVITY_ID = ACTIVITIES.ID AND ACTIVITIES.PAVILLION_NAME = PAVILLIONS.PAVILLION";
		ArrayList data = this.listactivities(selection);
		return (data);
	}

	//This method requests the execution of a SQL sentence for listing all the pavillions
	//and it retrieves all the information for each pavillion, storing each pavillion as an element
	//of an array. Each element contains an object of the type pavillion
	public ArrayList listallpav() throws Exception{
		ArrayList data = new ArrayList();
		String selection="SELECT * FROM PAVILLIONS";
		ResultSet rs=q.doSelect(selection);
		while (rs.next()) {                     
		    String name = rs.getString(1);
			String location = rs.getString(2);
			data.add(new Pavillion(name, location));
       }
	   return (data);
	}

	public ArrayList listUsersWithMinCommonActivities(String login, int minActivities) throws Exception {
		String selection = "SELECT c.LOGIN, c.NAME, c.SURNAME " +
						"FROM CLIENTS c " +
						"JOIN SUBSCRIPTIONS s1 ON c.LOGIN = s1.CLIENT_LOGIN " +
						"JOIN SUBSCRIPTIONS s2 ON s1.ACTIVITY_ID = s2.ACTIVITY_ID " +
						"WHERE s2.CLIENT_LOGIN = '" + login + "' AND c.LOGIN <> '" + login + "' " +
						"GROUP BY c.LOGIN, c.NAME, c.SURNAME " +
						"HAVING COUNT(s1.ACTIVITY_ID) >= " + minActivities;
		
		ArrayList data = new ArrayList();
		ResultSet rs = q.doSelect(selection);
		
		while (rs.next()) {
			String userLogin = rs.getString(1);
			String userName = rs.getString(2);
			String userSurname = rs.getString(3);
			data.add(new Client(userLogin, "", userName, userSurname, "", ""));
		}
		
		return data;
	}

	public ArrayList listUsersWithMinActivities(int minActivities) throws Exception { // TODO: Fix
		String selection = "SELECT c.LOGIN, c.NAME, c.SURNAME " +
						"FROM CLIENTS c " +
						"JOIN SUBSCRIPTIONS s ON c.LOGIN = s.CLIENT_LOGIN " +
						"GROUP BY c.LOGIN, c.NAME, c.SURNAME " +
						"HAVING COUNT(s.ACTIVITY_ID) >= " + minActivities;
		
		ArrayList data = new ArrayList();
		ResultSet rs = q.doSelect(selection);
		
		while (rs.next()) {
			String userLogin = rs.getString(1);
			String userName = rs.getString(2);
			String userSurname = rs.getString(3);
			data.add(new Client(userLogin, "", userName, userSurname, "", ""));
		}
		
		return data;
	}

	//This method subscribes a client to a specific activity
      //(after checking that the activity has free places)
	public void regactivity(String login, String id) throws Exception{
		String regactivity="INSERT INTO SUBSCRIPTIONS VALUES ('"+login+"','"+id+"')";
		q.doUpdate(regactivity);
	}

	//This method unsubscribes a client from a specific activity.
	//(after checking that the client is indeed subscribed to the activity).
	public void unregactivity(String login, String id) throws Exception{
		String unregactivity="DELETE FROM SUBSCRIPTIONS WHERE SUBSCRIPTIONS.CLIENT_LOGIN='"+login+"'AND SUBSCRIPTIONS.ACTIVITY_ID="+id;
		q.doUpdate(unregactivity);
	}
}