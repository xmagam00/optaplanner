package beans;


import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;

import java.util.*;

import javax.annotation.PostConstruct;

import database.Operation;
import definition.*;





@ManagedBean
@SessionScoped
public class AdministratorBean {
	  

	
	private String fileContent;

	public List<TaskDef> task;
	private List<TaskDef> oldTask;
    
	public List<UserDef> users;
	
	private List<UserDef> oldUsers;
	
	public List<OrganizationDef> organizations;
	private List<OrganizationDef> oldOrganizations;
	
	public String email;
	
	private String password;
	
	private String pass;
	
	private String passwordValidate;
	
	private long idUser;

	

	
	
	public String username;
	
	public String changeUsername;
	
	public String changeOrg;
	
	private String organization;
	
	private List<String> editableOrganizations;

	public String role;
	
	private String user;
	
	
	
	Operation op;
	
	
	
	
	@PostConstruct
    public void init(){
        try{
        	op = new Operation();
        	
        	organizations = new ArrayList<OrganizationDef>();
            task = new ArrayList<TaskDef>();
            users = new ArrayList<UserDef>();
    
            editableOrganizations = new ArrayList<String>();
            List<OrganizationDef> resultsOrg = op.getOrganizations();
            for (Object item : resultsOrg)
            {	
            	Object[] obj = (Object[]) item;  
            	organizations.add(new OrganizationDef(obj[0].toString(),obj[1].toString()));
            	editableOrganizations.add(obj[1].toString());
            }
            
            List<TaskDef> resultsTask = op.getAllTasks();
            for (Object item : resultsTask)
            {	
            	Object[] obj = (Object[]) item;  
            	task.add(new TaskDef(obj[0].toString(),obj[1].toString(),obj[2].toString(),obj[3].toString(),obj[4].toString(),convertIfPublic(obj[5].toString()),obj[6].toString(),obj[7].toString()));
            	
            }
            
            List<UserDef> resultsUser = op.getAllUsers();
            for (Object item : resultsUser)
            {	
            	Object[] obj = (Object[]) item;  
            	users.add(new UserDef(obj[0].toString(),obj[1].toString(),obj[2].toString(),obj[3].toString(),obj[4].toString(),obj[5].toString()));
            	
            }
            
         

           
        }catch(Exception e){
            e.printStackTrace();
        }
    }   
    
	public void updateEditableOrganizations()
	{ 	this.editableOrganizations = null;
		List<String> org = new ArrayList<String>();
		List<OrganizationDef> resultsOrg  = op.getOrganizations();
        for (Object item : resultsOrg)
        {	
        	Object[] obj = (Object[]) item;  
        	
        	org.add(obj[1].toString());
        }
        this.editableOrganizations = org;
	}
	public void updateTasks()
	{

		 List<TaskDef> org = new ArrayList<TaskDef>();
		 List<TaskDef> resultsOrg = op.getAllTasks();
        for (Object item : resultsOrg)
        {	
        	Object[] obj = (Object[]) item;  
        	org.add(new TaskDef(obj[0].toString(),obj[1].toString(),obj[2].toString(),obj[3].toString(),obj[4].toString(),convertIfPublic(obj[5].toString()),obj[6].toString(),obj[7].toString()));
        	
        }
        this.task = org;
	}
	
	
	
	public  void setTasks(List<TaskDef> tasks)
	{
		this.task = tasks;
	}
	
	/**
	 * Method convert number from database to Private|Public
	 * @param ifPublic
	 * @return
	 */
	private String convertIfPublic(String ifPublic)
	{
		if(ifPublic.equals("0"))
		{
			return "Private";
		}
		else
		{
			return "Public";
		}
	}
	
	public List<OrganizationDef> getOrganizations()
	{
		return organizations;
	}
	
	public void setOrganizations(List<OrganizationDef> organizations)
	{
		this.organizations = organizations;
	}
	
	public List<TaskDef> updateProgress()
	{
		return this.task;
	}
	
	public List<UserDef> getUser()
	{
		return this.users;
	}
	
	public List<TaskDef> getTask()
	{
		return this.task;
	}
	
	
	public String saveAction() {
		
		//get all existing value but set "editable" to false 
		for (UserDef order : users){
			order.setEditable(false);
			
			
			
		}
		updateUsers(this.oldUsers,this.users);
		List<UserDef> user = new ArrayList<UserDef>();
		 List<UserDef> resultsUsers = op.getAllUsers();
       for (Object item : resultsUsers)
       {	
       	Object[] obj = (Object[]) item;  
       	user.add(new UserDef(obj[0].toString(),obj[1].toString(),obj[2].toString(),obj[3].toString(),obj[4].toString(),obj[5].toString()));
       	
       }
       this.users = user;
		//return to current page
		return null;
 
	}
	
	
	public void updateUser(UserDef user)
	{
		setIdUser(Long.parseLong(user.getId()));
	}
	
	
	private void updateOrganizations(List<OrganizationDef> org, List<OrganizationDef> orgChanged)
	{
		int index = 0;
		  for (OrganizationDef element : org) 
		  {
			    if ((org.get(index).getNameOfOrganization()).equals(orgChanged.get(index).getNameOfOrganization())) 
			    
			    
			    {
			    	
			    	
			    }
			    else
			    {	
			    	if (!(org.get(index).getNameOfOrganization()).equals(orgChanged.get(index).getNameOfOrganization()))
			    	{	
			    		
			    		op.changeOrganization(Long.parseLong(org.get(index).getIdOrganization()),org.get(index).getNameOfOrganization());
			    	}
			   
			    	
			    }
				//System.out.println(org.get(index).getNameOfOrganization());
				//System.out.println(orgChanged.get(index).getNameOfOrganization());
			    index++;
			}
	
		
		
	}
	
	
	/**
	 * Method update information about users in database
	 * @param user
	 * @param userChanged
	 */
	private void updateUsers(List<UserDef> user, List<UserDef> userChanged)
	{
		int index = 0;
		  for (UserDef element : user) {
			    if ((user.get(index).getUsername()).equals(userChanged.get(index).getUsername()) 
			    &((user.get(index).getEmail()).equals(userChanged.get(index).getEmail()))
			    &((user.get(index).getRole()).equals(userChanged.get(index).getRole()))
			    &((user.get(index).getOrganization()).equals(userChanged.get(index).getOrganization()))
			    )
			    {
			    	
			    	
			    }
			    else
			    {	
			    	if (!(user.get(index).getUsername()).equals(userChanged.get(index).getUsername()))
			    	{	
			    		
			    		op.changeUsername(Long.parseLong(user.get(index).getId()),userChanged.get(index).getUsername());
			    	}
			    	
			    	if (!(user.get(index).getEmail()).equals(userChanged.get(index).getEmail()))
			    	{	
			    	
			    		op.changeEmail(Long.parseLong(user.get(index).getId()),element.getEmail());
			    	}
			    	if (!(user.get(index).getOrganization()).equals(userChanged.get(index).getOrganization()))
			    	{	
			    		
			    		op.changeOrganizationForUser(Long.parseLong(user.get(index).getId()),userChanged.get(index).getOrganization());
			    		
			    		
			    	}
			    	if (!(user.get(index).getRole()).equals(userChanged.get(index).getRole()))
			    	{		
			    		op.changeUserRole(Long.parseLong(user.get(index).getId()),userChanged.get(index).getRole());
			    	}
			 
			   
			
			    	
			    }
			   
			    index++;
			}
	}
	
	public String editActionTask(TaskDef task)
	{	
		 this.oldTask = null;
		 this.oldTask = new ArrayList<TaskDef>();
		 int index = 0;
		task.setEditable(true);
		for (TaskDef u : this.task)
		{
			this.oldTask.add(new TaskDef(this.task.get(index).getId(),this.task.get(index).getName(),this.task.get(index).getState(),this.task.get(index).getProgress(),this.task.get(index).getEstimatedTime(),this.task.get(index).getIfPublic(),this.task.get(index).getOwner(),this.task.get(index).getXmlFile()));
			
			index++;
		
		}
		
		
		return null;
	}
	
	public String saveActionTask()
	{
		for (TaskDef order : this.task)
		{
			order.setEditable(false);
					}
		updateTaskName(this.task,this.oldTask);
		return null;
	}
	
	private void updateTaskName(List<TaskDef> task,List<TaskDef> changedTask)
	{
		int index = 0;
		  for (TaskDef element : task) 
		  {
			    if ((task.get(index).getName()).equals(changedTask.get(index).getName())) 
			    
			    
			    {
			    	
			    	
			    }
			    else
			    {	
			    	if (!(task.get(index).getName()).equals(changedTask.get(index).getName()))
			    	{	
			    		
			    		System.out.println(task.get(index).getName());
			    		System.out.println(changedTask.get(index).getName());
			    		//op.changeNameOfTask(Long.parseLong(org.get(index).getIdOrganization()),org.get(index).getNameOfOrganization());
			    	}
			   
			    	
			    }
				System.out.println(task.get(index).getName());
				System.out.println(changedTask.get(index).getName());
			    index++;
			}
	
	}
	
	
	public String editAction(UserDef user) {
		
		 this.oldUsers = null;
		 this.oldUsers = new ArrayList<UserDef>();
		 int index = 0;
		
		user.setEditable(true);
		for (UserDef u : users)
		{
			this.oldUsers.add(new UserDef(users.get(index).getId(),users.get(index).getUsername(),users.get(index).getPassword(),users.get(index).getRole(),users.get(index).getOrganization(),users.get(index).getEmail()));
			
			index++;
		
		}
		
		return null;
	}
	
	
	public void deleteUser(UserDef user)
	{
		op.deleteUser(user.getUsername(),Long.parseLong(user.getId()));
		List<UserDef> userList = new ArrayList<UserDef>();
		 List<UserDef> resultsUsers = op.getAllUsers();
      for (Object item : resultsUsers)
      {	
      	Object[] obj = (Object[]) item;  
      	userList.add(new UserDef(obj[0].toString(),obj[1].toString(),obj[2].toString(),obj[3].toString(),obj[4].toString(),obj[5].toString()));
      	
      }
      this.users = userList;
	}
	
	
	
	
	public String saveActionOrganization() {
		 
		//get all existing value but set "editable" to false 
		for (OrganizationDef order : organizations){
			order.setEditable(false);
					}
		updateOrganizations(this.organizations,this.oldOrganizations);
		
		//return to current page
		return null;
 
	}
	
	
	public String editActionOrganization(OrganizationDef org) {
		 int index = 0;
		org.setEditable(true);
		 this.oldOrganizations = null;
		 this.oldOrganizations = new ArrayList<OrganizationDef>();
		for (OrganizationDef u : this.organizations)
		{
			this.oldOrganizations.add(new OrganizationDef(organizations.get(index).getIdOrganization(),organizations.get(index).getNameOfOrganization()));
			
			index++;
		
		}
		
		return null;
	}
	
	
	
	public void changePasswordForUser()
	{	
		//op.(this.idUser,this.password);
		
	//	op.changePassword(get,this.password);
	}
	
	
	
	public void publishTask(ActionEvent evt)
	{
		
	}
	
	public void runTask(ActionEvent evt)
	{
		
	}
	
	public void stopTask(ActionEvent evt)
	{
		
	}
	
	public void editTask(ActionEvent evt) {
	  
	}
	
	
	
	public void createUser()
	{	
		long org = op.getIdOrganization(getOrganization());
		
		op.createUser(getUsername(),getPass(),getRole(),getEmail(),org);
		setUsername("");
		setPass("");
		setEmail("");
		
		List<UserDef> user = new ArrayList<UserDef>();
		 List<UserDef> resultsUsers = op.getAllUsers();
        for (Object item : resultsUsers)
        {	
        	Object[] obj = (Object[]) item;  
        	user.add(new UserDef(obj[0].toString(),obj[1].toString(),obj[2].toString(),obj[3].toString(),obj[4].toString(),obj[5].toString()));
        	
        }
        this.users = user;
		
		
	}
	
	public void editUser()
	{
		long id = op.getIdUser(this.username);
	}
	
	
	
	public void createTask()
	{
		
	}
	
	public void unpublishTask()
	{
		
	}
	
	public void deleteTask()
	{
	
	}
	
	public void changePassword()
	{
		

	op.changePassword(getIdUser(),getPass());	
	setPass("");
	setPasswordValidate("");
	List<UserDef> user = new ArrayList<UserDef>();
	 List<UserDef> resultsUsers = op.getAllUsers();
   for (Object item : resultsUsers)
   {	
   	Object[] obj = (Object[]) item;  
   
   	user.add(new UserDef(obj[0].toString(),obj[1].toString(),obj[2].toString(),obj[3].toString(),obj[4].toString(),obj[5].toString()));
   	
   }
   this.users = user;
	}
	
	public void upload()
	{
	
	}
	
	public void createOrganization()
	{   
		op.createOrganization(this.organization);
		updateOrganization();
		
	}
	
	public void updateOrganization()
	{	 
		
		 List<OrganizationDef> org = new ArrayList<OrganizationDef>();
		 List<OrganizationDef> resultsOrg = op.getOrganizations();
         for (Object item : resultsOrg)
         {	
         	Object[] obj = (Object[]) item;  
         	org.add(new OrganizationDef(obj[0].toString(),obj[1].toString()));
         	
         }
         this.organizations = org;
	}
	
	
	
	public void deleteOrganization(OrganizationDef org)
	{   
	
		op.deleteOrganization(Long.parseLong(org.getIdOrganization()));
		updateOrganization();
	}
	
	
	public void logout() {
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		try {
			context.redirect("Login.xhtml");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String getPasswordValidate()
	{
		return passwordValidate;
	}
	
	public void setPasswordValidate(String passwordValidate)
	{
		this.passwordValidate = passwordValidate;
	}
	
	public void validatePassword()
	{
		op.changePasswordForUser("martin",this.password);
	 
	 
	}
	public String getOrganization()
	{
		return organization;
	}
	
	public void setOrganization(String organization)
	{
		this.organization = organization;
	}
	
	public void setEditableOrganizations(List<String> org)
	{
		this.editableOrganizations = org;
	}
	
	public List<String> getEditableOrganizations()
	{
		return editableOrganizations;
	}
	
	public String getRole()
	{
		return role;
	}
	
	public void setRole(String role)
	{
		this.role = role;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public String getPass()
	{
		return this.pass;
	}
	
	public void setPass(String password)
	{
		this.pass = password;
	}
	
	public void setIdUser(long id)
	{
		this.idUser = id;
	}
	
	public long getIdUser()
	{
		return idUser;
	}

	
}
