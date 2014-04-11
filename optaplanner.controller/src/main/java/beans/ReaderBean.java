package beans;
import definition.OrganizationDef;
import definition.TaskDef;
import definition.UserDef;

import java.util.*;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import database.Operation;


@ManagedBean
@SessionScoped
public class ReaderBean {
	
	@ManagedProperty("#{tasks}")
	public List<TaskDef> tasks;
	

	@ManagedProperty("#{password}")
	private String password;

	@ManagedProperty("#{passwordValidate}")
	private String passwordValidate;
	Operation op;
	HttpServletRequest request;
	private String user;
	
	
		
	
	
	
	@PostConstruct
    public void init(){
        try{
        	this.user = request.getParameter("user");
            tasks = new ArrayList<TaskDef>();
            
            
          
         
            for(int i=0; i<5; i++){
               // tasks.add(new TaskDef(String.valueOf(i),"name","CREATED","0","2:00","False","Martin"));
            }

           
        }catch(Exception e){
            e.printStackTrace();
        }
    }   
	
	
	public void unpublishTask()
	{
		
	}
	
	public void publishTask()
	{
		
	}
	
	public void changePassword()
	{
		if (password.equals(passwordValidate))
		{
			
		}
	}
	
	
	
	
	public void logout()
	{
	FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	 ExternalContext context = FacesContext.getCurrentInstance().getExternalContext(); 
        try {
			context.redirect("Login.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
