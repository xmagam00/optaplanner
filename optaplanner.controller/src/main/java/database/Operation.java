package database;
import org.jboss.seam.security.Credentials;
import org.picketlink.idm.impl.api.PasswordCredential;

import java.sql.*;
import java.util.List;

import javax.persistence.*;
import javax.transaction.UserTransaction;

import org.jboss.seam.security.Credentials;
import org.picketlink.idm.impl.api.PasswordCredential;

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
	public void createTask(String name,String xmlfile,String username)
	{   
		Query query = eManager.createQuery("select id_user from User where user_name='"+username+"'");
		Object idUser = query.getSingleResult();
		
		User usertab = eManager.getReference(User.class,Long.parseLong(idUser.toString()));
		
		query = eManager.createQuery("select organization from User where user_name='" + username +"'");
		Object idOrganization = query.getSingleResult();
		Organization org = (Organization)(idOrganization);
		
		Organization orgtab = eManager.getReference(Organization.class,org.getIdOrganization());
		
	 
		eManager.getTransaction().begin();
		Task task = new Task();
		task.setXmlFile(xmlfile);
		task.setStateOfTask("NEW");
		task.setIfPublic(0);
		task.setProgress(0);
		task.setETA(0);
		task.setUser(usertab);
		task.setName(name);
		task.setOrganization(orgtab);
		
		eManager.persist(task);
		eManager.getTransaction().commit();
		
		
        
	}
	
	public void createTaskState(String name,String xmlfile,String username)
	{   System.out.println(username);
		Query query = eManager.createQuery("select id_user from User where user_name='"+username+"'");
		Object idUser = query.getSingleResult();
		
		User usertab = eManager.getReference(User.class,Long.parseLong(idUser.toString()));
		
		query = eManager.createQuery("select organization from User where user_name='" + username +"'");
		Object idOrganization = query.getSingleResult();
		Organization org = (Organization)(idOrganization);
		
		Organization orgtab = eManager.getReference(Organization.class,org.getIdOrganization());
		
	 
		eManager.getTransaction().begin();
		Task task = new Task();
		task.setXmlFile(xmlfile);
		task.setStateOfTask("MODIFIED");
		task.setIfPublic(0);
		task.setProgress(0);
		task.setETA(0);
		task.setUser(usertab);
		task.setName(name);
		task.setOrganization(orgtab);
		
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
	 * @param string
	 * @return true if password match with password in database
	 */
 	public boolean validatePassword(String username, String string)
 	{
 		boolean result = false;
 		
 		
 		Query q = eManager.createQuery("select user_name from User where user_name='" + username +"'");
 		 Object pass = q.getSingleResult();
 		 if (pass.toString().equals(string))
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
 		Query q = eManager.createQuery("select t.id_task,t.name,t.state_of_task,t.progress_of_task,t.eta,t.ifpublic, u.user_name,t.xml_file from Task t,User u where t.user = u.id_user");

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
	public void deleteUser(String username,long id)
 	{
		User user = eManager.find(User.class, id);
		 
		  eManager.getTransaction().begin();
		  eManager.remove(user);
		  eManager.getTransaction().commit();
		
 		
 	}
	
	/**
	 * 
	 * This method delete task
	 * @param username
	 * @return
	 */
	public void deleteTask(long idTask)
 	{	
		Task task = eManager.find(Task.class, idTask);
		 
		  eManager.getTransaction().begin();
		  eManager.remove(task);
		  eManager.getTransaction().commit();
		
		
 		
 	}
	

	/**
	 * This method change userpassword
	 * @param username
	 * @param password
	 * @return
	 */
	public void changePasswordForUser(String username,String password)
 	{	
		Query query = eManager.createQuery("select id_user from User where user_name='"+username +"'");
		Object result = query.getSingleResult();
		
		
		User user = eManager.find(User.class,Long.parseLong(result.toString()));
		eManager.getTransaction().begin();
		user.setPassword(password);
		eManager.getTransaction().commit();
 	 }
	
	public void changePassword(long id, String password)
	{
		
		
		User user = eManager.getReference(User.class,id);
		eManager.getTransaction().begin();
		user.setPassword(password);
		eManager.getTransaction().commit();
	}
	
	
	
	/**
	 * Change userRole for specified user by parameter username
	 * @param username
	 * @param userRole
	 * @return
	 */
	public void changeUserRole(Long id,String userRole)
 	{
		  EntityTransaction entr = eManager.getTransaction();
	      entr.begin();
	      User user = eManager.find(User.class, id);
	      user.setRole(userRole);
	      
	      entr.commit();
 		
 	}
	
	/**
	 * Method change email for username
	 * @param username
	 * @param email
	 */
	public void changeEmail(Long id,String email)
	{
		
		  EntityTransaction entr = eManager.getTransaction();
	      entr.begin();
	      User user = eManager.find(User.class, id);
	      user.setEmail(email);
	      
	      entr.commit();
	}
	
	/**
	 * Method set username as usernameNew in database
	 * @param usernameOld
	 * @param usernameNew
	 */
	public void changeUsername(Long usernameOld,String usernameNew)
	{	
		
				
		      EntityTransaction entr = eManager.getTransaction();
		      entr.begin();
		      User user = eManager.find(User.class, usernameOld);
		      user.setUsername(usernameNew);
		      
		      entr.commit();
		   
		    
	}
	
	public void changeOrganizationForUser(long id,String org)
	{
		  EntityTransaction entr = eManager.getTransaction();
	      entr.begin();
	      User user = eManager.find(User.class, id);
	      Query query = eManager.createQuery("select id_organization from Organization where name_of_organization='"+ org +"'");
	      Object result = query.getSingleResult();
	      Organization organization = eManager.getReference(Organization.class,Long.parseLong(result.toString()));
	      user.setOrganization(organization);
	      
	      entr.commit();
 		
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
	public void changeOrganization(Long id, String newOrg)
	{

	      EntityTransaction entr = eManager.getTransaction();
	      entr.begin();
	      Organization org = eManager.find(Organization.class, id);
	      org.setNameOfOrganization(newOrg);
	      
	      entr.commit();
 	    
		
	}
	
	/**
	 * Delete organization
	 * @param org
	 */
	public void deleteOrganization(Long org)
	{			Organization organization = eManager.getReference(Organization.class, org);
		 
		  eManager.getTransaction().begin();
		  eManager.remove(organization);
		  eManager.getTransaction().commit();
		
		
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
		Object result = q.getSingleResult();
		
 	 	    answer = Long.valueOf(result.toString()).longValue(); 
 	 	  
 		
 		
 		
		return answer;
	}
	
	public long getIdUser(String username)
	{
		long answer = 0;
		
		Query q = eManager.createQuery("select id_user from User where user_name='" + username +"'");
		Object user= q.getSingleResult();
		
 	 	    answer = Long.valueOf(user.toString()).longValue(); 
 	 	   
 		
		
 		
 		
		return answer;
	}
	
	/**
	 * Method set new xml file
	 * @param idTask
	 * @param xmlFile
	 */
	public void changeXmlFile(String xmlFile, String name, String owner)
	{	
		
			createTaskState(name,xmlFile,owner);
	
	}
	
	public void changeNameOfTask(long idTask, String newName)
	{
		EntityTransaction entr = eManager.getTransaction();
	      entr.begin();
	      Task task = eManager.getReference(Task.class, idTask);
	     task.setName(newName);
	      
	      entr.commit();
	}
	
	public void changePermission(long idTask,String permission)
	{
		   EntityTransaction entr = eManager.getTransaction();
		      entr.begin();
		      Task task = eManager.find(Task.class, idTask);
		      task.setIfPublic(Integer.parseInt(permission));
		      
		      entr.commit();
	}
	
	/**
	 * Method return User entity for logging
	 * @param username
	 * @return
	 */
	public User getUserByUsername(String username)
	{
		Query q = eManager.createQuery("select id_user from User where user_name='" + username +"'");
		Object user= q.getSingleResult();
		User users = eManager.getReference(User.class,Long.parseLong(user.toString()));
		return users;
	}
	
	
}
 	

