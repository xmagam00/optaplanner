package beans;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class ReaderBean {
		
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
}
