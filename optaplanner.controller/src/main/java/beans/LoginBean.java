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
	    private boolean isUsernameValid;
	    private boolean isPasswordValid;
	    private boolean validationComplete = false;
	    private static final String PERSISTENCE_UNIT_NAME = "user";
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
	     * @return the isUsernameValid
	     */
	    public boolean getIsUsernameValid() {
	      return true;
	    }
	    /**
	     * @paramisUsernameValid the isUsernameValid to set
	     */
	    public void setUsernameValid(boolean isUsernameValid) {
	        this.isUsernameValid = isUsernameValid;
	    }
	    /**
	     * @return the isPasswordValid
	     */
	    public boolean getIsPasswordValid() {
	      return true;
	    }
	    /**
	     * @paramisPasswordValid the isPasswordValid to set
	     */
	    public void setPasswordValid(boolean isPasswordValid) {
	        this.isPasswordValid = isPasswordValid;
	    }
	    /**
	     * @return the validationComplete
	     */
	    public boolean getValidationComplete() {
	        return validationComplete;
	    }
	    /**
	     * @paramvalidationComplete the validationComplete to set
	     */
	    public void setValidationComplete(boolean validationComplete) {
	        this.validationComplete = validationComplete;
	    }
	 
	    public void checkValidity() {
	        if (this.username == null || this.username.equals("") ){
	            isUsernameValid = false;
	            validationComplete = false;
	        	return;
	            
	        }
	        else {
	            isUsernameValid = true;
	        }
	        if (this.password == null  || this.password.equals("")) {
	            isPasswordValid = false;
	            validationComplete = false;
	        	return;
	        }
	        else {
	            isPasswordValid = true;
	        }
	        validationComplete = true;
	        
	        Operation op = new Operation();
	        
	        if (!op.validateUsername(this.username))
	        {
	        	validationComplete = false;
	        	return;
	        }
	        
	        if (!op.validatePassword(this.username,this.password))
	        {
	        	validationComplete = false;
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
	  				// TODO Auto-generated catch block
	  				e.printStackTrace();
	  			}
	        }
	        
	        
	        
	        
	     
	        
	    }
	}



