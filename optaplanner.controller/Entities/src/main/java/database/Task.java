package database;

import javax.persistence.*;
import model.TaskStatus;

@Entity
@Table(name="task")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idTask;
	private String xmlFile;
	
	@Enumerated(EnumType.STRING)
	private TaskStatus stateOfTask;
	private int progressOfTask;
	private int ifpublic;
	private long eta;
	private String name;
	
	@ManyToOne
	@JoinColumn(name="user",referencedColumnName="idUser")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="organization",referencedColumnName="idOrganization")
	private Organization organization;
	
	public void setIdTask(long idTask)
	{
		this.idTask = idTask;
	}
	
	public long getIdTask()
	{
		return idTask;
	}
	
	public void setXmlFile(String xmlFile)
	{
		this.xmlFile = xmlFile;
	}
	
	public String getXmlFile()
	{
		return xmlFile;
		
	}
	
	public void setStateOfTask(TaskStatus state_of_task_of_task)
	{
		this.stateOfTask = state_of_task_of_task;
	}
	
	public TaskStatus getstate_of_task_of_task()
	{
		return stateOfTask;
	}
	
	public void setIfPublic(int ifPublic)
	{
		this.ifpublic = ifPublic;
	}
	
	public int getIfPublic()
	{
		return ifpublic;
	}
	
	public void setProgress(int progress)
	{
		this.progressOfTask = progress;
	}
	
	public int getProgress()
	{
		return progressOfTask;
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
