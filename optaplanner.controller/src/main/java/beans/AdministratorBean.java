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
import javax.servlet.http.HttpServletRequest;

import java.util.*;

import javax.annotation.PostConstruct;

import definition.*;






@ManagedBean(eager=true)
@SessionScoped
public class AdministratorBean {
	  

	
	private String fileContent;

	private List<TaskDef> tasks;
    
	private List<UserDef> users;
	
	@ManagedProperty(value="#{organizations}")
	private List<OrganizationDef> organizations;
	
	@ManagedProperty(value="#{password}")
	private String password;
	
	@ManagedProperty(value="#{passwordValidate}")
	private String passwordValidate;
	
	@ManagedProperty(value="#{data}")
	public String data;
	
	@ManagedProperty(value="#{table}")
	private HtmlDataTable table;
	
	@ManagedProperty(value="#{username}")
	private String username;
	
	@ManagedProperty(value="#{organization}")
	private String organization;
	
	@ManagedProperty(value="#{role}")
	private String role;
	
	private String user;
	
	HttpServletRequest request;
	
	@PostConstruct
    public void init(){
        try{
        	this.user = request.getParameter("user");
        	organizations = new ArrayList<OrganizationDef>();
            tasks = new ArrayList<TaskDef>();
            users = new ArrayList<UserDef>();
            
            for (int i = 0;i< 5 ;i++)
            {
            	organizations.add(new OrganizationDef(String.valueOf(i),"Nieco"));
            }
            
            for(int i=0; i<5; i++){
                users.add(new UserDef(String.valueOf(i),"martin","tajneheslo","Reader","Red Hat","martin.maga@centrum.sk"));
            }
         
            for(int i=0; i<5; i++){
                tasks.add(new TaskDef(String.valueOf(i),"name","CREATED","0","2:00"));
            }

           
        }catch(Exception e){
            e.printStackTrace();
        }
    }   
    
	public List<TaskDef> updateProgress()
	{
		return this.tasks;
	}
	
	public List<UserDef> getUser()
	{
		return this.users;
	}
	
	public List<TaskDef> getTask()
	{
		return this.tasks;
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
	
	public void createOrganization()
	{
		
	}
	
	public void editOrganization()
	{
		
	}
	
	public void deleteOrganization()
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
