package org.jboss.optaplanner.controller.beans;

import java.io.IOException;

import org.jboss.optaplanner.controller.databaseOp.Operation;
import org.jboss.optaplanner.controller.login.UserRole;
import org.jboss.optaplanner.entities.User;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.picketlink.idm.impl.api.PasswordCredential;
import org.jboss.seam.security.BaseAuthenticator;
import org.jboss.seam.security.Credentials;


/**
 * @author martin
 * 
 */

public class LoginBean extends BaseAuthenticator {

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

	public void preRenderView() throws IOException {

		System.out.println("tu");

	}

	@Override
	public void authenticate() {

		setUsernameValid(false);
		setPasswordValid(false);
		setUsernameEmpty(false);
		setPasswordEmpty(false);

		if (credentials.getUsername() == null
				|| credentials.getUsername().isEmpty()) {
			setUsernameEmpty(true);
			if (getPassword() == null) {
				setPasswordEmpty(true);

			}
			setStatus(AuthenticationStatus.FAILURE);
			return;
		}

		if (((PasswordCredential) credentials.getCredential()).getValue() == null
				|| ((PasswordCredential) credentials.getCredential())
						.getValue().isEmpty()) {
			setStatus(AuthenticationStatus.FAILURE);
			setPasswordEmpty(true);
			return;
		}

		Operation op = new Operation();
		System.out.println("tu1");
		if (!op.validateUsername(credentials.getUsername())) {
			setStatus(AuthenticationStatus.FAILURE);
			setUsernameValid(true);
			return;
		}

		if (!op.validatePassword(credentials.getUsername(),
				(((PasswordCredential) credentials.getCredential()).getValue()))) {
			setStatus(AuthenticationStatus.FAILURE);
			setPasswordValid(true);
			return;
		}
		final User user = op.getUserByUsername(credentials.getUsername());
		UserRole role = UserRole.ADMINISTRATOR;

		// request.getSession().setAttribute("user", this.username);
		String roles = op.getUserRoleByUsername(credentials.getUsername());
		System.out.println(roles);
		if (roles.equals("Administrator")) {
			role = UserRole.ADMINISTRATOR;
		} else if (roles.equals("Reader")) {
			role = UserRole.READER;
		} else if (roles.equals("Planner")) {
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

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();
		try {
			if (roles.equals("Administrator"))
			{
				response.sendRedirect("Administrator.xhtml");
			}
			if (roles.equals("Reader"))
			{	System.out.println("som tu");
				response.sendRedirect("Reader.xhtml");
			}
			
			if (roles.equals("Planner"))
			{
				response.sendRedirect("Planner.xhtml");
			}
		} catch (IOException e) {
		
			e.printStackTrace();
		}

	}

}
