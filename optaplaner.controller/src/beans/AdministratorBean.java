package beans;

import java.io.IOException;



import javax.faces.application.FacesMessage;

import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import java.util.*;

import javax.servlet.http.Part;




public class AdministratorBean {
	  public Part file;
	  public String fileContent;		
	
		 

	 
	  public void upload() {
	    try {
	      fileContent = new Scanner(file.getInputStream()).useDelimiter("\\A").next();
	    } catch (IOException e) {
	      // Error handling
	    }
	  }
	 
	  public Part getFile() {
	    return file;
	  }
	 
	  public void setFile(Part file) {
	    this.file = file;
	  }
		
		public void logout()
		{
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		 ExternalContext context = FacesContext.getCurrentInstance().getExternalContext(); 
	        try {
				context.redirect("Login.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void validateFile(FacesContext ctx,
                UIComponent comp,
                Object value) {
List<FacesMessage> msgs = new ArrayList<FacesMessage>();
Part file = (Part)value;
if (file.getSize() > 1024) {
msgs.add(new FacesMessage("file too big"));
}
if (!"text/plain".equals(file.getContentType())) {
msgs.add(new FacesMessage("not a text file"));
}
if (!msgs.isEmpty()) {
throw new ValidatorException(msgs);
}
}


}


	
	

