package beans;
import java.io.IOException;
import java.io.Serializable;

import database.Operation;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
/**
 * @author martin
 *
 */

public class LoginBean  {
	


		
	
	    private String username;
	    private String password;
	    private boolean isUsernameValid;
	    private boolean isPasswordValid;
	    private boolean validationComplete = false;
	
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
	        }
	        else {
	            isUsernameValid = true;
	        }
	        if (this.password == null  || this.password.equals("")) {
	            isPasswordValid = false;
	        }
	        else {
	            isPasswordValid = true;
	        }
	        validationComplete = true;
	       ExternalContext context = FacesContext.getCurrentInstance().getExternalContext(); 
	        try {
				context.redirect("Administrator.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	    }
	}



