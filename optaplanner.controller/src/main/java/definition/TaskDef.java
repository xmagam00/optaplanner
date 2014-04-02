package definition;
import java.io.Serializable;

public class TaskDef implements Serializable{

	
	    private static final long serialVersionUID = -8349963947101031989L;
	    private String id;
	    private String name;
	    private String progress;
	    private String state;
	    private String estimatedTime;
	    private String ifPublic;
	    public TaskDef(String id, String name, String state, String progress,String estimatedTime,String ifPublic) {
	        this.id = id;
	        this.name = name;
	        this.state = state;
	        this.progress = progress;
	        this.estimatedTime = estimatedTime;
	        this.ifPublic = ifPublic;
	    }
	    public String getId() {
	        return id;
	    }
	    public void setId(String id) {
	        this.id = id;
	    }
	    public String getName() {
	        return name;
	    }
	    public void setName(String name) {
	        this.name = name;
	    }
	    
	    public void setProgress(String progress)
	    {
	    	this.progress = progress;
	    	
	    }
	    
	    public String getProgress()
	    {
	    	return progress;
	    }
	    
	    public void setState(String state)
	    {
	    	this.state = state;
	    }
	    
	    public String getState()
	    {
	    	return state;
	    }
	    
	    public void setEstimatedTime(String estimatedTime)
	    {
	    	this.estimatedTime = estimatedTime;
	    }
	    
	    public String getEstimatedTime()
	    {
	    	return estimatedTime; 
	    }
	    
	    public String getIfPublic()
	    {
	    	return ifPublic;
	    }
	    
	    public void setIfPublic(String ifPublic)
	    {
	    	this.ifPublic = ifPublic;
	    }
	    
	}

