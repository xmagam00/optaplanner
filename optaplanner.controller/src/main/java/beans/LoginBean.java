package beans;



import database.User;
import databaseOp.Operation;





import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;



import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import login.UserRole;

import org.picketlink.idm.impl.api.PasswordCredential;
import org.jboss.seam.security.BaseAuthenticator;
import org.jboss.seam.security.Credentials;
/**
 * @author martin
 * 
 */


public class LoginBean extends BaseAuthenticator{

	@Inject
	private Credentials credentials;

	private String username;
	private String password;
	private boolean usernameValid = false;
	private boolean passwordValid = false;
	private boolean usernameEmpty = false;
	private boolean passwordEmpty = false;

	/**
	 * @return the username
	 */

	public void setUsernameEmpty(boolean render) {
		this.usernameEmpty = render;
	}

	public boolean getUsernameEmpty() {
		return usernameEmpty;
	}

	public void setPasswordEmpty(boolean render) {
		this.passwordEmpty = render;
	}

	public boolean getPasswordEmpty() {
		return passwordEmpty;
	}

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

	public boolean getUsernameValid() {
		return usernameValid;
	}

	/**
	 * @paramisPasswordValid the isPasswordValid to set
	 */
	public void setPasswordValid(boolean isPasswordValid) {
		this.passwordValid = isPasswordValid;
	}

	public boolean getPasswordValid() {
		return passwordValid;
	}
	
	@Override
	public void authenticate() {
	
		setUsernameValid(false);
		setPasswordValid(false);
		setUsernameEmpty(false);
		setPasswordEmpty(false);

		if (credentials.getUsername() == null || credentials.getUsername().isEmpty()) {
			setUsernameEmpty(true);
			if (getPassword() == null) {
				setPasswordEmpty(true);
				
			}
			setStatus(AuthenticationStatus.FAILURE);
			return;
		}

		if (((PasswordCredential) credentials
				.getCredential()).getValue() == null || ((PasswordCredential) credentials
						.getCredential()).getValue().isEmpty()) {
			setStatus(AuthenticationStatus.FAILURE);
			setPasswordEmpty(true);
			return;
		}

		Operation op = new Operation();

		if (!op.validateUsername(credentials.getUsername())) {
			setStatus(AuthenticationStatus.FAILURE);
			setUsernameValid(true);
			return;
		}
	
		if (!op.validatePassword(credentials.getUsername(), (((PasswordCredential) credentials
				.getCredential()).getValue())))  {
			setStatus(AuthenticationStatus.FAILURE);
			setPasswordValid(true);
			return;
		}
		final User user = op.getUserByUsername(credentials.getUsername());
		UserRole role = UserRole.ADMINISTRATOR;
		
		// request.getSession().setAttribute("user", this.username);
		String roles = op.getUserRoleByUsername(credentials.getUsername());
		if (roles.equals("ADMINISTRATOR"))
		{
			role = UserRole.ADMINISTRATOR;
		}
		else if (roles.equals("READER"))
		{
			role = UserRole.READER;
		}
		else if (roles.equals("PLANNER"))
		{
			role = UserRole.PLANNER;
		}
		
		final UserRole userRole = role;
		
		
		setUser(new org.picketlink.idm.api.User() {
			@Override
			public String getId() {
				return Long.toString(user.getUserId());
			}
			@Override
			public String getKey() {
				return userRole.toString();
			}
		});
		
		setStatus(AuthenticationStatus.SUCCESS);
		
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			FacesContext test = FacesContext.getCurrentInstance();
			if (test.getResponseComplete()) {
				return;
				}
			externalContext.responseReset();
			if (externalContext.isResponseCommitted())
			{
				
				return;
			}
			if (roles.equals("Administrator"))
			{
				externalContext.redirect("Administrator.xhtml");
			
				return;
			}
			else if (roles.equals("Reader"))
			{
				externalContext.redirect("Reader.xhtml");
				return;
			}
			else if (roles.equals("Planner"))
			{
				externalContext.redirect("Planner.xhtml");
				return;
			}

			return;
				
				
			
		} catch(Exception ex)
		{
			
		}

	}
}
