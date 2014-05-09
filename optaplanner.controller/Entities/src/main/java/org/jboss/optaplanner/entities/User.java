package org.jboss.optaplanner.entities;

import java.io.Serializable;

import javax.persistence.*;



@Entity
@Table(name="user")
public class User implements Serializable {
	
	 private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idUser;
	private String username;
	private String password;
	private String role;
	
	private String email;
	
	@ManyToOne
	@JoinColumn(name="organization",referencedColumnName="idOrganization")
	private Organization organization;
	
	
	public void setUserId(long userId)
	{
		this.idUser = userId;
	}
	
	public long getUserId()
	{
		return idUser;
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
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public void setOrganization(Organization organization)
	{
		this.organization = organization;
	}
	
	public Organization getOrganization()
	{
		return organization;
	}
	
	
	
	
}
