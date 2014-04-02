package database;

import java.sql.*;
import java.util.List;

import javax.persistence.*;

import database.Task;
import database.User;
import database.Organization;
/**
 * 
 * @author martin Maga
 * This class do oper over MySQL database
 */

public class Operation {
	
	private static final String PERSISTENCE_UNIT_NAME = "optaplanner";

	private static EntityManagerFactory factory;
	private EntityManager eManager;
	
	public Operation()
	{
		
    
        	factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            eManager = factory.createEntityManager();
                   
          
       
	}
	
	
	
	/**
	 * This method create a new task for user
	 * @param username
	 * @param xmlfile
	 * @param urlflag
	 * @return
	 */
	public void createTask(String username,String xmlfile,int ifPublic,long eta,int user,String name)
	{   
		User usertab = eManager.getReference(User.class,user);
		
		
	 
		  
		Task task = new Task();
		task.setXmlFile(xmlfile);
		task.setStateOfTask("CREATED");
		task.setIfPublic(0);
		task.setProgress(0);
		task.setETA(0);
		task.setUser(usertab);
		task.setName(name);
		eManager.getTransaction().begin();
		eManager.persist(task);
		eManager.getTransaction().commit();
		eManager.close();
			
        
	}
	
	/**
	 * This method create a new user
	 * @param username
	 * @param xmlfile
	 * @param urlflag
	 * @return
	 */
	public void createUser(String username,String password,String NameOfOrganization, String UserRole, String email, int organization)
	{   
		Organization org = eManager.getReference(Organization.class,organization);
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setRole(UserRole);
		user.setEmail(email);
		user.setOrganization(org);
		
		eManager.getTransaction().begin();
		eManager.persist(user);
		eManager.getTransaction().commit();
		eManager.close();
		
	  
	  
	}
	
	
	
	

	/**
	 * Method validate if entered password for user existsin database
	 * @param username
	 * @param password
	 * @return true if password match with password in database
	 */
 	public boolean validatePassword(String username, String password)
 	{
 		boolean result = false;
 		
 		
 		Query q = eManager.createQuery("select user_name from user where user_name='" + username + "'");
 		 List<User> todoList = q.getResultList();
 	    for (User passwordDatab : todoList) {
 	      if (password.equals(passwordDatab.toString()))
 	    		  {
 	    		 result = true;
 	    		  }
 	    }
       

        eManager.close();
 		
 	 return result;
 	}
 	
 	/**
 	 * Validate existince of username
 	 * @param username
 	 * @return
 	 */
 	public boolean validateUsername(String username)
 	{
 		boolean answer = false;
 	
 		Query q = eManager.createQuery("SELECT username FROM User where user_name='" +username + "'");
 		 List<User> todoList = q.getResultList();
 		if (todoList.size() > 0)
 			answer = true;

 		
 		eManager.close();
 		return answer;
 	}
 	
    /**
     * Return all created task
     * @return
     */
 	 List<Task>  selectAllTasks()
 	{
 		Query q = eManager.createQuery("select * from task");
 		List<Task> todoList = q.getResultList();
 		 
 		 return todoList;
 	}
 	
 	
 	
 	/**
 	 * 
 	 * @param username
 	 * @return
 	 */
	ResultSet selectTaskByUserWithOrganization(String username)
 	{
 	
		Query q  = eManager.createQuery("");
 	      return null;
 		
 	}
	
	/**
	 * Method delete specified user
	 * @param username
	 * @return true if operation succeed
	 */
	public void deleteUser(String username)
 	{
 		
		
 		eManager.close();
 	}
	
	/**
	 * 
	 * This method delete task
	 * @param username
	 * @return
	 */
	public void deleteTask(int idTask)
 	{
		String deleteQuery = "DELETE FROM TASK WHERE id_task=" + idTask;
		eManager.createQuery(deleteQuery).executeUpdate();
		eManager.close();
		
 		
 	}
	

	/**
	 * This method change userpassword
	 * @param username
	 * @param password
	 * @return
	 */
	public void changePasswordForUser(String username,String password)
 	{	
		User user = eManager.find(User.class,username);
 		user.setPassword(password);
 		eManager.persist(user);
 	    eManager.getTransaction().commit();

 	    eManager.close();
		
 		
 	}
	
	
	
	/**
	 * Change userRole for specified user by parameter username
	 * @param username
	 * @param userRole
	 * @return
	 */
	public void changeUserRole(String username,String userRole)
 	{
		User user = eManager.find(User.class,username);
 		user.setRole(userRole);
 		eManager.persist(user);
 	    eManager.getTransaction().commit();

 	    eManager.close();
 	  
	
	
	
	
 	}
	
	/**
	 * Method create organization
	 * @param organization
	 */
	public void createOrganization(String organization)
	{
		
		Organization org = new Organization();
		org.setNameOfOrganization(organization);
		
		
		eManager.getTransaction().begin();
		eManager.persist(org);
		eManager.getTransaction().commit();
		eManager.close();
	}
	
	/**
	 * Method change name of organization when is needed
	 * @param oldOrg
	 * @param newOrg
	 */
	public void editOrganization(String oldOrg, String newOrg)
	{
		Organization org = eManager.find(Organization.class,oldOrg);
 		org.setNameOfOrganization(newOrg);
 		eManager.persist(org);
 	    eManager.getTransaction().commit();

 	    eManager.close();
		
	}
	
	/**
	 * Delete organization
	 * @param org
	 */
	public void deleteOrganization(String org)
	{
		String deleteQuery = "DELETE FROM ORGANIZATION WHERE name_of_organization='" + org +"'";
		eManager.createQuery(deleteQuery).executeUpdate();
		eManager.close();
		
	}
	
	/**
	 * Method return userrole for username
	 * @param username
	 */
	public String getUserRoleByUsername(String username)
	{
		String answer = null;
		
 		Query q = eManager.createQuery("select role from user where user_name='" + username + "'");
 		 List<User> todoList = q.getResultList();
 		for (User role : todoList) {
 	 	    answer = role.toString(); 
 	 	    }
 		
 		
 		eManager.close();
 		return answer;
		
	}
	
}
 	

