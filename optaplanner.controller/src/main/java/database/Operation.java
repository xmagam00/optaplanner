package database;

import java.sql.*;
import java.util.List;

import javax.persistence.*;

import database.Task;
import database.User;
import database.Organization;
import definition.OrganizationDef;
import definition.TaskDef;
import definition.UserDef;
/**
 * 
 * @author martin Maga
 * This class do oper over MySQL database
 */

public class Operation {
	
	private static final String PERSISTENCE_UNIT_NAME = "optaplanner";

	private static EntityManagerFactory factory;
	
	@PersistenceContext
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
	public void createTask(String username,String xmlfile,int ifPublic,long eta,int user,String name,int organization)
	{   
		User usertab = eManager.getReference(User.class,user);
		Organization orgtab = eManager.getReference(Organization.class,organization);
		
	 
		  
		Task task = new Task();
		task.setXmlFile(xmlfile);
		task.setStateOfTask("CREATED");
		task.setIfPublic(0);
		task.setProgress(0);
		task.setETA(0);
		task.setUser(usertab);
		task.setName(name);
		task.setOrganization(orgtab);
		eManager.getTransaction().begin();
		eManager.persist(task);
		eManager.getTransaction().commit();
		
			
        
	}
	
	/**
	 * This method create a new user
	 * @param username
	 * @param xmlfile
	 * @param urlflag
	 * @return
	 */
	public void createUser(String username,String password, String UserRole, String email, long organization)
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
		
		
	  
	  
	}
	
	/**
	 * Method return all users in database
	 * @return
	 */
	public List<UserDef> getAllUsers()
	{
		Query q = eManager.createQuery("SELECT  u.id_user,u.user_name, u.password,u.role, o.name_of_organization,u.email FROM User u,Organization o where u.organization=o.id_organization");
 		List<UserDef> todoList = q.getResultList();
 		 
 		 return todoList;
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
 		
 		
 		Query q = eManager.createQuery("select user_name from User where user_name='" + username +"'");
 		 Object pass = q.getSingleResult();
 		 if (pass.toString().equals(password))
 		 {
 			 result = true;
 		 }
 	  
       

        
 		
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
 	
 		Query q = eManager.createQuery("SELECT user_name FROM User where user_name='" +username + "'");
 		 List<User> todoList = q.getResultList();
 		if (todoList.size() > 0)
 			answer = true;

 		
 		
 		return answer;
 	}
 	
    /**
     * Return all created task
     * @return
     */
 	public List<TaskDef>  getAllTasks()
 	{
 		Query q = eManager.createQuery("select t.id_task,t.name,t.state_of_task,t.progress_of_task,t.eta,t.ifpublic, u.user_name from Task t,User u where t.user = u.id_user");

 		List<TaskDef> todoList = q.getResultList();
 		 
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
 		
		
 		
 	}
	
	/**
	 * 
	 * This method delete task
	 * @param username
	 * @return
	 */
	public void deleteTask(int idTask)
 	{
		String deleteQuery = "delete from Task where id_task=" + idTask;
		eManager.createQuery(deleteQuery).executeUpdate();
		
		
 		
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

 	    
 	  
	
	
	
	
 	}
	
	/**
	 * Method return all organizations
	 * @return
	 */
	public List<OrganizationDef> getOrganizations()
	{	
		Query query = eManager.createQuery("select id_organization,name_of_organization  from Organization");
		List<OrganizationDef> todoList = query.getResultList();
 		 
 		 
 		 return todoList;
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

 	    
		
	}
	
	/**
	 * Delete organization
	 * @param org
	 */
	public void deleteOrganization(String org)
	{
		String deleteQuery = "delete from Organization where name_of_organization='" + org +"'";
		eManager.createQuery(deleteQuery).executeUpdate();
		
		
	}
	
	/**
	 * Method return userrole for username
	 * @param username
	 */
	public String getUserRoleByUsername(String username)
	{
		String answer = null;
		
 		Query q = eManager.createQuery("select role from User where user_name='" + username + "'");
 		 Object role= q.getSingleResult();
 		
 	 	    answer = role.toString(); 
 	 	
 		
 		
 		
 		return answer;
		
	}
	
	public long getIdOrganization(String organization)
	{
		long answer = 0;
		
		Query q = eManager.createQuery("select id_organization from Organization where name_of_organization='" + organization +"'");
		List<Organization> todoList= q.getResultList();
		for (Organization name : todoList) {
 	 	    answer = Long.valueOf(name.toString()).longValue(); 
 	 	    }
 		
 		
 		
		return answer;
	}
	
	public long getIdUser(String username)
	{
		long answer = 0;
		
		Query q = eManager.createQuery("select id_user from User where user_name='" + username +"'");
		List<User> todoList= q.getResultList();
		for (User id : todoList) {
 	 	    answer = Long.valueOf(id.toString()).longValue(); 
 	 	    }
 		
		
 		
 		
		return answer;
	}
	
}
 	

