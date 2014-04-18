package login;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.seam.security.Identity;

@SessionScoped
@Named
public class LoggedUserController implements Serializable{
	private static final long serialVersionUID = 32178687623188L;
	@Inject
    private Identity identity;
	
    @Inject
    private Authorization authorize;

    public boolean isAdmin() {
        return authorize.isAdmin(identity);
    }

    public boolean isReader() {
        return authorize.isReader(identity);
    }
    
    public boolean isPlanner() {
        return authorize.isPlanner(identity);
    }
}
