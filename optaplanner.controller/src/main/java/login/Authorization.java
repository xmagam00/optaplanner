package login;

import java.io.Serializable;

import org.jboss.annotation.ejb.Service;
import org.jboss.seam.security.Identity;
import org.jboss.seam.security.annotations.Secures;

@Service
public class Authorization implements Serializable {
	private static final long serialVersionUID = 1329890238401L;

	@Secures
	@Administrator
	public static boolean isAdmin(Identity identity) {

		if (!identity.isLoggedIn()) {
			return false;
		}
		
		return identity.getUser().getKey().equals(UserRole.ADMIN.toString());
	}

	@Secures
	@Reader
	public static boolean isReader(Identity identity) {

		if (!identity.isLoggedIn()) {
			return false;
		}

		return identity.getUser().getKey().equals(UserRole.READER.toString());
	}
	
	@Secures
	@Planner
	public static boolean isPlanner(Identity identity) {

		if (!identity.isLoggedIn()) {
			return false;
		}

		return identity.getUser().getKey().equals(UserRole.PLANNER.toString());
	}
	
}
