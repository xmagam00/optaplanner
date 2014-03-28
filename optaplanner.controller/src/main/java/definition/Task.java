package definition;
import java.io.Serializable;

public class Task implements Serializable{

	
	    private static final long serialVersionUID = -8349963947101031989L;
	    private String id;
	    private String name;
	    private String progress;
	    private String state;
	    private String estimatedTime;
	    public Task(String id, String name,String progress, String state,String estimatedTime) {
	        this.id = id;
	        this.name = name;
	        this.state = state;
	        this.progress = progress;
	        this.estimatedTime = estimatedTime;
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
	    
	    
	}

