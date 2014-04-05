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
    
	public List<UserDef> users;
	
	
	public List<OrganizationDef> organizations;
	
	public String email;
	
	private String password;
	
	
	private String passwordValidate;
	
	
	public String data;
	
	
	public HtmlDataTable table;
	
	
	public String username;
	
	public String changeUsername;
	
	public String changeOrg;
	
	private String organization;
	
	private List<String> editableOrganizations;

	public String role;
	
	private String user;
	
	HttpServletRequest request;
	
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
            	task.add(new TaskDef(obj[0].toString(),obj[1].toString(),obj[2].toString(),obj[3].toString(),obj[4].toString(),convertIfPublic(obj[5].toString()),obj[6].toString()));
            	
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
        	org.add(new TaskDef(obj[0].toString(),obj[1].toString(),obj[2].toString(),obj[3].toString(),obj[4].toString(),convertIfPublic(obj[5].toString()),obj[6].toString()));
        	
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
		//return to current page
		return null;
 
	}
	
	
	public String editAction(UserDef user) {
		 
		user.setEditable(true);
		return null;
	}
	
	
	public String saveActionOrganization() {
		 
		//get all existing value but set "editable" to false 
		for (UserDef order : users){
			order.setEditable(false);
		}
		//return to current page
		return null;
 
	}
	
	
	public String editActionOrganization(UserDef user) {
		 
		user.setEditable(true);
		return null;
	}
	
	
	
	public void changePasswordForUser()
	{
		op.changePasswordForUser(this.username,this.password);
	}
	
	
	
	public void publishTask(ActionEvent evt)
	{
		   // We get the table object
	    HtmlDataTable table = getParentDatatable((UIComponent) evt.getSource());
	    // We get the object on the selected line.
	    Object o = table.getRowData();
	    // Eventually, if you need the index of the line, simply do:
	    int index = table.getRowIndex();
	    System.out.println(index);
	}
	
	public void runTask(ActionEvent evt)
	{
		   // We get the table object
	    HtmlDataTable table = getParentDatatable((UIComponent) evt.getSource());
	    // We get the object on the selected line.
	    Object o = table.getRowData();
	    // Eventually, if you need the index of the line, simply do:
	    int index = table.getRowIndex();
	    System.out.println(index);
	}
	
	public void stopTask(ActionEvent evt)
	{
		   // We get the table object
	    HtmlDataTable table = getParentDatatable((UIComponent) evt.getSource());
	    // We get the object on the selected line.
	    Object o = table.getRowData();
	    // Eventually, if you need the index of the line, simply do:
	    int index = table.getRowIndex();
	    System.out.println(index);
	}
	
	public void editTask(ActionEvent evt) {
	    // We get the table object
	    HtmlDataTable table = getParentDatatable((UIComponent) evt.getSource());
	    // We get the object on the selected line.
	    Object o = table.getRowData();
	    // Eventually, if you need the index of the line, simply do:
	    int index = table.getRowIndex();
	    System.out.println(index);
	}
	
	public void createUser()
	{	
		long org = op.getIdOrganization(this.organization);
		op.createUser(this.user,this.password,this.role,this.email,org);
	}
	
	public void editUser()
	{
		long id = op.getIdUser(this.username);
	}
	
	public void deleteUser()
	{
		
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
		if (this.password.equals(this.passwordValidate))
				{
			System.out.println("ZHODA");
			
				}
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
	
	public void editOrganization()
	{
		op.editOrganization(this.organization,this.changeOrg);
	}
	
	public void deleteOrganization()
	{
		op.deleteOrganization(this.organization);
	}
	
	
	// Method to get the HtmlDataTable.
	private HtmlDataTable getParentDatatable(UIComponent compo) {
	    if (compo == null) {
	        return null;
	    }
	    if (compo instanceof HtmlDataTable) {
	        return (HtmlDataTable) compo;
	    }
	    return getParentDatatable(compo.getParent());
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

	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String getValidatePassword()
	{
		return passwordValidate;
	}
	
	public void setValidatePassword(String passwordValidate)
	{
		this.passwordValidate = passwordValidate;
	}
	
	public void validatePassword(ComponentSystemEvent event) {
		 
		  FacesContext fc = FacesContext.getCurrentInstance();
	 
		  UIComponent components = event.getComponent();
	 
		  // get password
		  UIInput uiInputPassword = (UIInput) components.findComponent("Password");
		  String password = uiInputPassword.getLocalValue() == null ? ""
			: uiInputPassword.getLocalValue().toString();
		  String passwordId = uiInputPassword.getClientId();
	 
		  // get confirm password
		  UIInput uiInputConfirmPassword = (UIInput) components.findComponent("confirmPassword");
		  String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? ""
			: uiInputConfirmPassword.getLocalValue().toString();
	 
		  // Let required="true" do its job.
		  if (password.isEmpty() || confirmPassword.isEmpty()) {
			return;
		  }
	 
		  if (!password.equals(confirmPassword)) {
	 
			FacesMessage msg = new FacesMessage("Password must match confirm password");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage(passwordId, msg);
			fc.renderResponse();
	 
		  }
	 
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
	

}
