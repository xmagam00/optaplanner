package beans;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.sun.tools.javac.util.List;

import database.Operation;
import definition.TaskDef;
public class PlannerBean {
	
	
	Operation op;
	
	String user;
	HttpServletRequest request;
	public ArrayList<TaskDef> tasks;
	
	public String password;
	
	public String passwordValidate;
	
	@PostConstruct
    public void init(){
        try{
        	this.user = request.getParameter("user");
            tasks = new ArrayList<TaskDef>();
            
            
          
         
            for(int i=0; i<5; i++){
                tasks.add(new TaskDef(String.valueOf(i),"name","CREATED","0","2:00","False","Martin"));
            }

           
        }catch(Exception e){
            e.printStackTrace();
        }
    }   
	
	
	
	
	
	public void createTask()
	{
		
		
	}
	
	public void deleteTask()
	{
		
	}
	
	public void publishTask()
	{
		
	}
	
	public void unpublishTask()
	{
		
	}
	
	
	
	public void editTask()
	{
		
	}
	
	public void runTask()
	{
		
	}
	
	public void stopTask()
	{
		
	}
	
	public void upload()
	{
		
	}
	
	public void changePassword()
	{
		
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
