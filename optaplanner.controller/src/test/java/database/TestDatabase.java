package database;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.*;

import org.junit.*;

import definition.*;
public class TestDatabase {
	  
		List<UserDef> user;
		List<UserDef> userChanged;
		Operation op;
	  
	  @Test
	  public void testUser()
	  {	 user = new ArrayList<UserDef>();
	  List<UserDef> userChanged = new ArrayList<UserDef>();
		  for (int i =0;i<5;i++)
		  {
			  user.add(new UserDef("Moj","Nieco","Cau","Ahoj","Bejbe","Nalej"));
			  userChanged.add(new UserDef("Moje","Nieco","Caua","Ahoja","Bejbe","Nalej"));
		  }
		
		  
		  int index = 0;
		  for (UserDef element : user) {
			    if ((element.getUsername()).equals(userChanged.get(index).getUsername()) 
			    &((element.getEmail()).equals(userChanged.get(index).getEmail()))
			    &((element.getRole()).equals(userChanged.get(index).getRole()))
			    &((element.getOrganization()).equals(userChanged.get(index).getOrganization()))
			    )
			    {
			    	
			    	continue;
			    }
			    else
			    {	
			    	if (!(element.getUsername()).equals(userChanged.get(index).getUsername()))
			    	{
			    		op.changeUsername(element.getUsername(),userChanged.get(index).getUsername());
			    	}
			    	
			    	if (!(element.getEmail()).equals(userChanged.get(index).getEmail()))
			    	{
			    		op.changeEmail(element.getUsername(),element.getEmail());
			    	}
			    	if (!(element.getOrganization()).equals(userChanged.get(index).getOrganization()))
			    	{
			    		op.changeOrganization(userChanged.get(index).getOrganization(),element.getOrganization());
			    		
			    		
			    	}
			    	if (!(element.getRole()).equals(userChanged.get(index).getRole()))
			    	{
			    		op.changeUserRole(element.getRole(),userChanged.get(index).getRole());
			    	}
			   
			    	
			    }
			    index++;
			}
		  
		  
	  
	  
	  
}
}