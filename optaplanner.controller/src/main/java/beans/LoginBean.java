package beans;
import java.io.IOException;

import database.Operation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.*;


/**
 * @author martin
 *
 */

public class LoginBean  {
	


		
		HttpServletRequest request;
	    public String username;
	    public String password;
	    private boolean usernameValid = false;
	    private boolean passwordValid = false;
	  

	    /**
	     * @return the username
	     */
	    
	   
	    
	    
	    public String getUsername() {
	        return username;
	    }
	    /**
	     * @param username
	     *            the username to set
	     */
	    public void setUsername(String username) {
	        this.username = username;
	    }
	    /**
	     * @return the password
	     */
	    public String getPassword() {
	        return password;
	    }
	    /**
	     * @param password
	     *            the password to set
	     */
	    public void setPassword(String password) {
	        this.password = password;
	    }
	    /**
	     * @paramisUsernameValid the isUsernameValid to set
	     */
	    public void setUsernameValid(boolean isUsernameValid) {
	        this.usernameValid = isUsernameValid;
	    }
	    
	    public boolean getUsernameValid()
	    {
	    	return usernameValid;
	    }
	    
	    /**
	     * @paramisPasswordValid the isPasswordValid to set
	     */
	    public void setPasswordValid(boolean isPasswordValid) {
	        this.passwordValid = isPasswordValid;
	    }
	    
	    public boolean getPasswordValid()
	    {
	    	return passwordValid;
	    }
	   
	 
	    public void checkValidity() {
	       
	       setUsernameValid(false);
	        
	        Operation op = new Operation();
	        
	        if (!op.validateUsername(this.username))
	        {
	    
	        	setUsernameValid(true);
	        	return;
	        }
	        
	        if (!op.validatePassword(this.username,this.password))
	        {
	        
	        	setPasswordValid(true);
	        	return;
	        }
	        
	        
	        //request.getSession().setAttribute("user", this.username);
	        String role = op.getUserRoleByUsername(this.username);
	        
	        if (role.equals("Administrator"))
	        {	
	        	  ExternalContext context = FacesContext.getCurrentInstance().getExternalContext(); 
	  	        try {
	  				context.redirect("Administrator.xhtml");
	  			} catch (IOException e) {
	  				// TODO Auto-generated catch block
	  				e.printStackTrace();
	  			}
	        }
	        
	        
	        if (role.equals("Planner"))
	        {
	        	  ExternalContext context = FacesContext.getCurrentInstance().getExternalContext(); 
	  	        try {
	  				context.redirect("Planner.xhtml");
	  			} catch (IOException e) {
	  				// TODO Auto-generated catch block
	  				e.printStackTrace();
	  			}
	        }
	        
	        
	        
	        if (role.equals("Reader"))
	        {	
	        
	        	  ExternalContext context = FacesContext.getCurrentInstance().getExternalContext(); 
	  	        try {
	  				context.redirect("Reader.xhtml");
	  			} catch (IOException e) {
	  				  				e.printStackTrace();
	  			}
	        }
	        
	        
	        
	        
	     
	        
	    }
	}



