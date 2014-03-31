package database;

import javax.persistence.*;

@Entity
@Table(name="task")
public class TaskTable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_task;
	private String xml_file;
	private String state;
	private int progress;
	private int ifpublic;
	private long eta;
	private String name;
	
	public void setIdTask(long idTask)
	{
		this.id_task = idTask;
	}
	
	public long getIdTask()
	{
		return id_task;
	}
	
	public void SetXmlFile(String xmlFile)
	{
		this.xml_file = xmlFile;
	}
	
	public String getXmlFile()
	{
		return xml_file;
		
	}
	
	public void setState(String state)
	{
		this.state = state;
	}
	
	public String getState()
	{
		return state;
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
	
	

}
