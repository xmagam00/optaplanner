package org.jboss.optaplanner.controller.definition;
import java.io.Serializable;

public class UserDef implements Serializable{
	private String id;
	private String username;
	private String password;
	private String role;
	private String organization;
	private String email;
	boolean editable = false;
	
	 private static final long serialVersionUID = -8349963947101031982L;
	
	public UserDef(String id,String username,  String password, String role, String organization, String email)
	{
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.organization = organization;
		this.email = email;
	}
	
	public String getId()
	{
		return id;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setRole(String role)
	{
		this.role = role;
	}
	
	public String getRole()
	{
		return role;
	}
	
	public void setOrganization(String organization)
	{
		this.organization = organization;
	}
	
	public String getOrganization()
	{
		return organization;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	
	
	
}
