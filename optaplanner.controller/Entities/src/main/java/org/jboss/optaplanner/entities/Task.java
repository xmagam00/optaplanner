package org.jboss.optaplanner.entities;

import javax.persistence.*;

@Entity
@Table(name="task")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String xmlFile;
	
	@Enumerated(EnumType.STRING)
	private TaskStatus status;
	private int progress;
	private boolean pub;
	private long eta;
	private String name;
	
	@ManyToOne
	@JoinColumn(name="user",referencedColumnName="idUser")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="organization",referencedColumnName="idOrganization")
	private Organization organization;
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public long getId()
	{
		return id;
	}
	
	public void setXmlFile(String xmlFile)
	{
		this.xmlFile = xmlFile;
	}
	
	public String getXmlFile()
	{
		return xmlFile;
		
	}
	
	public void setStatus(TaskStatus status)
	{
		this.status = status;
	}
	
	public TaskStatus getStatus()
	{
		return status;
	}
	
	public void setPublic(boolean pub)
	{
		this.pub = pub;
	}
	
	public boolean isPublic()
	{
		return pub;
	}
	
	public void setProgress(int progress)
	{
		this.progress = progress;
	}
	
	public int getProgress()
	{
		return progress;
	}
	
	public long getETA()
	{
		return eta;
	}
	
	public void setETA(long eta)
	{
		this.eta = eta;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public User getUser()
	{
		return user;
	}
	
	public void setUser(User user)
	{
		this.user = user;
	}
	
	public Organization getOrganization()
	{
		return organization;
	}
	
	public void setOrganization(Organization organization)
	{
		this.organization = organization;
	}
	
	
	

}
