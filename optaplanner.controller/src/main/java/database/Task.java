package database;

import javax.persistence.*;

@Entity
@Table(name="task")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_task;
	private String xml_file;
	private String state_of_task;
	private int progress_of_task;
	private int ifpublic;
	private long eta;
	private String name;
	
	@ManyToOne
	@JoinColumn(name="user",referencedColumnName="id_user")
	private User user;
	
	public void setIdTask(long idTask)
	{
		this.id_task = idTask;
	}
	
	public long getIdTask()
	{
		return id_task;
	}
	
	public void setXmlFile(String xmlFile)
	{
		this.xml_file = xmlFile;
	}
	
	public String getXmlFile()
	{
		return xml_file;
		
	}
	
	public void setStateOfTask(String state_of_task_of_task)
	{
		this.state_of_task = state_of_task_of_task;
	}
	
	public String getstate_of_task_of_task()
	{
		return state_of_task;
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
		this.progress_of_task = progress;
	}
	
	public int getProgress()
	{
		return progress_of_task;
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
	
	
	

}
