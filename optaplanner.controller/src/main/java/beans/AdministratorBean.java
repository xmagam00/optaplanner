package beans;


import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;

import java.util.*;

import javax.annotation.PostConstruct;

import definition.*;






@ManagedBean(eager=true)
@SessionScoped
public class AdministratorBean {
	  

	
	private String fileContent;

	private List<Task> tasks;
    
	private List<User> users;
	
	@ManagedProperty(value="#{password}")
	private String password;
	
	@ManagedProperty(value="#{passwordValidate}")
	private String passwordValidate;
	
	@ManagedProperty(value="#{data}")
	public String data;
	
	private HtmlDataTable table;
	
	@ManagedProperty(value="#{username}")
	private String username;
	
	@ManagedProperty(value="#{organization}")
	private String organization;
	
	@ManagedProperty(value="#{role}")
	private String role;
	
	@PostConstruct
    public void init(){
        try{
            tasks = new ArrayList<Task>();
            users = new ArrayList<User>();
            
            for(int i=0; i<5; i++){
                users.add(new User(String.valueOf(i),"martin","tajneheslo","Reader","Red Hat"));
            }
         
            for(int i=0; i<5; i++){
                tasks.add(new Task(String.valueOf(i),"nieco","nieco","uloha","nieco"));
            }

           
        }catch(Exception e){
            e.printStackTrace();
        }
    }   
    
	public List<Task> updateProgress()
	{
		return this.tasks;
	}
	
	public List<User> getUser()
	{
		return this.users;
	}
	
	public List<Task> getTask()
	{
		return this.tasks;
	}
	
	public void publishTask()
	{
		
	}
	
	public void runTask()
	{
		
	}
	
	public void stopTask()
	{
		
	}
	
	public void editTask(ActionEvent evt) {
	    // We get the table object
	    HtmlDataTable table = getParentDatatable((UIComponent) evt.getSource());
	    // We get the object on the selected line.
	    Object o = table.getRowData();
	    // Eventually, if you need the index of the line, simply do:
	    int index = table.getRowIndex();
	    // ...
	}
	
	public void createUser()
	{
		
	}
	
	public void editUser()
	{
		
	}
	
	public void deleteUser()
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
		
	}
	
	public void upload()
	{
	
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
			context.redirect("Login.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	
	

}
