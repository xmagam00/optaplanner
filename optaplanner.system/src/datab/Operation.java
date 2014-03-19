package datab;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;


/**
 * 
 * @author martin Maga
 * This class do oper over MySQL database
 */

public class Operation {
	
	protected Connection connection;
	
	
	public Operation()
	{
		
        try {
            //Loading the JDBC driver for MySql
            Class.forName("com.mysql.jdbc.Driver");

            //Getting a connection to the database. Change the URL parameters
            connection =  DriverManager.getConnection("jdbc:mysql://Server/Schema", "root", "root");

            
            
          
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
	}
	
	
	
	/**
	 * This method create a new task for user
	 * @param username
	 * @param xmlfile
	 * @param urlflag
	 * @return
	 */
	public boolean createTask(String username,String xmlfile,boolean urlflag)
	{   
	
	  int id = 0;
	  try {
		  
	  
		Statement statement = connection.createStatement();
		
		
		ResultSet rs =  statement.executeQuery("SELECT id_user FROM user where user_name=" + username);
		while (rs.next()) {
	            id = Integer.parseInt(rs.getString(1));
	            	
	        }
		statement.executeUpdate("INSERT INTO task(xml_file,state_of_task,progress_of_task,url_flag,url) " + 
                "VALUES (" + xmlfile +"0, 0," + convertUrlFlag(urlflag)  +",'null',"+ id  +")");
	        //close the resultset, statement and connection.
	        rs.close();
	        statement.close();
	        connection.close();
		
		
		
        connection.close();
	  } catch (Exception e)
	  {
		  System.err.println("Got an exception! "); 
          System.err.println(e.getMessage()); 
          return false;
	  }
	  return true;
	}
	
	/**
	 * This method create a new user
	 * @param username
	 * @param xmlfile
	 * @param urlflag
	 * @return
	 */
	public boolean createUser(String username,String password,String NameOfOrganization, String UserRole)
	{   
	
	  
	  try {
		  
	  
		Statement statement = connection.createStatement();
		
		
		statement.executeUpdate("INSERT INTO user(user_name,password,name_of_organization,user_role) " + 
                "VALUES("+username +","+ password +","+ NameOfOrganization+","+UserRole);
	        //close the resultset, statement and connection.
	        
	        statement.close();
	        connection.close();
		
		
		
        connection.close();
	  } catch (Exception e)
	  {
		  System.err.println("Got an exception! "); 
          System.err.println(e.getMessage()); 
          return false;
	  }
	  return true;
	}
	
	
	protected int convertUrlFlag(boolean url_flag)
	{
		if (url_flag == true)
			return 1;
		else
			return 0;
	}
	

	/**
	 * Method validate if entered password for user existsin database
	 * @param username
	 * @param password
	 * @return true if password match with password in database
	 */
 	public boolean validatePassword(String username, String password)
 	{
 		boolean answer = false;
 		try {
 	   //Creating a statement object
        Statement stmt =  this.connection.createStatement();

        //Executing the query and getting the result set
        ResultSet rs =  stmt.executeQuery("select user_name from user where user_name=" + username);

        //Iterating the resultset and printing the 3rd column
        while (rs.next()) {
            if (rs.getString(1).equals(password))
            	answer = true;
            else
            	answer = false;
        }
        //close the resultset, statement and connection.
        rs.close();
        stmt.close();
        connection.close();
 		
 		
 	 } catch (SQLException e) {
         e.printStackTrace();
 	 }catch (Exception e)
 	 {
 		 e.printStackTrace();
 	 }
 		return answer;
 	}
 	
 	/**
 	 * Validate existince of username
 	 * @param username
 	 * @return
 	 */
 	public boolean validateUsername(String username)
 	{
 		boolean answer = false;
 		try {
 	   //Creating a statement object
        Statement stmt =  this.connection.createStatement();

        //Executing the query and getting the result set
        ResultSet rs =  stmt.executeQuery("select user_name from user where user_name=" + username);
        
        //return result
        if (rs.first())
        	answer = true;
        else 
        	answer = false;
     
        //close the resultset, statement and connection.
        rs.close();
        stmt.close();
        connection.close();
 		
 		
 	 } catch (SQLException e) {
         e.printStackTrace();
 	 }catch (Exception e)
 	 {
 		 e.printStackTrace();
 	 }
 		return answer;
 	}
 	
    /**
     * Return all created task
     * @return
     */
 	 ResultSet  selectAllTasks()
 	{
 		ResultSet rs  = null;
 		try {
 	   //Creating a statement object
        Statement stmt =  this.connection.createStatement();

        //Executing the query and getting the result set
        rs =  stmt.executeQuery("select * from task");
        
        
     
        //close the resultset, statement and connection.
        rs.close();
        stmt.close();
        connection.close();
 		
 	
 	 } catch (SQLException e) {
         e.printStackTrace();
 	 }
 		return rs;
 	}
 	
 	 /**
 	  * Method return all tasks which has owner
 	  * @param username
 	  * @return
 	  */
 	ResultSet selectTaskByUser(String username)
 	{
 		ResultSet rs = null;
 		try {
 	 	   //Creating a statement object
 	        Statement stmt =  this.connection.createStatement();

 	        //Executing the query and getting the result set
 	        rs =  stmt.executeQuery("select * from task T,user U where U.id_user = K.id_task and username=" + username);
 	        
 	        
 	        
 	     
 	        //close the resultset, statement and connection.
 	        rs.close();
 	        stmt.close();
 	        connection.close();
 	 		
 	 	
 	 	 } catch (SQLException e) {
 	         e.printStackTrace();
 	 	 }
 		
 		
 		
 		
 		
 		return rs;
 		
 		
 	}
 	
 	/**
 	 * 
 	 * @param username
 	 * @return
 	 */
	ResultSet selectTaskByUserWithOrganization(String username)
 	{
 		ResultSet rs = null;
 		try {
 	 	   //Creating a statement object
 	        Statement stmt =  this.connection.createStatement();

 	        //Executing the query and getting the result set
 	        rs =  stmt.executeQuery("select name_of_organization from user where username=" + username);
 	        String result = rs.getString(0);
 	        
 	        rs = stmt.executeQuery("select * from user where name_of_organization=" + result);
 	        
 	        
 	     
 	        //close the resultset, statement and connection.
 	        rs.close();
 	        stmt.close();
 	        connection.close();
 	 		
 	 	
 	 	 } catch (SQLException e) {
 	         e.printStackTrace();
 	 	 }
 		
 		
 		
 		
 		
 		return rs;
 		
 		
 	}
	
	/**
	 * Method delete specified user
	 * @param username
	 * @return true if operation succeed
	 */
	public boolean deleteUser(String username)
 	{
 		
 		try {
 	   //Creating a statement object
        Statement stmt =  this.connection.createStatement();

        //Executing the query and getting the result set
        stmt.executeQuery("DELETE user_name from user where user_name=" + username);
     
        stmt.close();
        connection.close();
 		
 		
 	 } catch (SQLException e) {
         e.printStackTrace();
         return false;
 	 }catch (Exception e)
 	 {
 		 e.printStackTrace();
 		 return false;
 	 }
 		return true;
 	}
	
	/**
	 * 
	 * This method delete task
	 * @param username
	 * @return
	 */
	public boolean deleteTask(int idTask)
 	{
 		
 		try {
 	   //Creating a statement object
        Statement stmt =  this.connection.createStatement();

        //Executing the query and getting the result set
        stmt.executeQuery("DELETE * from task where id_task=" + idTask);
     
        stmt.close();
        connection.close();
 		
 		
 	 } catch (SQLException e) {
         e.printStackTrace();
         return false;
 	 }catch (Exception e)
 	 {
 		 e.printStackTrace();
 		 return false;
 	 }
 		return true;
 	}
	

	/**
	 * This method change userpassword
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean changePasswordForUser(String username,String password)
 	{
 		
 		try {
 	   //Creating a statement object
        Statement stmt =  this.connection.createStatement();

        //Executing the query and getting the result set
        stmt.executeQuery("UPDATE password SET password="+ password +" where user_name=" + username);
     
        stmt.close();
        connection.close();
 		
 		
 	 } catch (SQLException e) {
         e.printStackTrace();
         return false;
 	 }catch (Exception e)
 	 {
 		 e.printStackTrace();
 		 return false;
 	 }
 		return true;
 	}
	
	/**
	 * Return all available userRoles
	 * @return
	 */
	ResultSet selectUserRoles()
 	{
 		ResultSet rs = null;
 		try {
 	 	   //Creating a statement object
 	        Statement stmt =  this.connection.createStatement();

 	        //Executing the query and getting the result set
 	        rs =  stmt.executeQuery("select name_of_role from role");
 	      
 	        
 	        
 	     
 	        //close the resultset, statement and connection.
 	        rs.close();
 	        stmt.close();
 	        connection.close();
 	 		
 	 	
 	 	 } catch (SQLException e) {
 	         e.printStackTrace();
 	 	 }
 		
 		
 		
 		
 		
 		return rs;
 		
 		
 	}
	
	/**
	 * Change userRole for specified user by parameter username
	 * @param username
	 * @param userRole
	 * @return
	 */
	public boolean changeUserRole(String username,int userRole)
 	{
 		
 		try {
 	   //Creating a statement object
        Statement stmt =  this.connection.createStatement();

        //Executing the query and getting the result set
        stmt.executeQuery("UPDATE user SET user_role="+ Integer.toString(userRole) +" where user_name=" + username);
     
        stmt.close();
        connection.close();
 		
 		
 	 } catch (SQLException e) {
         e.printStackTrace();
         return false;
 	 }catch (Exception e)
 	 {
 		 e.printStackTrace();
 		 return false;
 	 }
 		return true;
 	}
	
	
  
	
	
	
	
}
 	

