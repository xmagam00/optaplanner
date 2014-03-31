package database;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name="user")
public class UserTable implements Serializable {
	
	 private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long user_id;
	private String username;
	private String password;
	private String role;
	@Email
	private String email;
	@ManyToOne
	@JoinColumn(name="id_organization")
	private OrganizationTable organization;
	
	
	public void setUserId(long userId)
	{
		this.user_id = userId;
	}
	
	public long getUserId()
	{
		return user_id;
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
	
	
	
	
	
	
}
