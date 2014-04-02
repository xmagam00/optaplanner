package database;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name="user")
public class User implements Serializable {
	
	 private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_user;
	private String username;
	private String password;
	private String role;
	@Email
	private String email;
	
	@ManyToOne
	@JoinColumn(name="organization",referencedColumnName="id_organization")
	private Organization organization;
	
	
	public void setUserId(long userId)
	{
		this.id_user = userId;
	}
	
	public long getUserId()
	{
		return id_user;
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
