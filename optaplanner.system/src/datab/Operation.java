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

            //Creating a statement object
            Statement stmt = ( connection).createStatement();
fdsf
            //Executing the query and getting the result set
            ResultSet rs =  stmt.executeQuery("select * from item");

            //Iterating the resultset and printing the 3rd column
            while (rs.next()) {
                System.out.println(rs.getString(3));
            }
            //close the resultset, statement and connection.
            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
	}
	
	
	/**
	 * This method create task with user
	 */
	
	public boolean createTask()
	{
		boolean answer = false;
	  try {
		  
	  
		Statement statement = connection.createStatement();
		statement.executeUpdate("INSERT INTO Customers " + 
	                "VALUES (1004, 'Cramden', 'Mr.', 'New York', 2001)");

        connection.close();
	  } catch (Exception e)
	  {
		  System.err.println("Got an exception! "); 
          System.err.println(e.getMessage()); 
          return false;
	  }
	  return true;
	}
	
}
