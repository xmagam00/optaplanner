package beans;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;

import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import java.util.*;

import javax.annotation.PostConstruct;


import definition.*;




public class AdministratorBean {
	  

	
	private String fileContent;

	private List<Task> tasks;
    
	private List<User> users;
    
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

	
	
	public void publishTask()
	{
		
	}
	
	public void runTask()
	{
		
	}
	
	public void stopTask()
	{
		
	}
	
	public void editTask()
	{
		
	}
	
	public void editUser()
	{
		
	}

}
