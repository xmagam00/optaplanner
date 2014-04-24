package login;
import org.jboss.seam.faces.event.PhaseIdType;
import org.jboss.seam.faces.rewrite.FacesRedirect;
import org.jboss.seam.faces.rewrite.UrlMapping;
import org.jboss.seam.faces.security.AccessDeniedView;
import org.jboss.seam.faces.security.LoginView;
import org.jboss.seam.faces.security.RestrictAtPhase;
import org.jboss.seam.faces.view.config.ViewConfig;
import org.jboss.seam.faces.view.config.ViewPattern;
import org.jboss.seam.security.annotations.LoggedIn;


@ViewConfig
public interface Pages {
	static enum Pages1 {

		@ViewPattern("/index.xhtml")
		INDEX,

        @ViewPattern("/Administrator.xhtml")
        @Administrator
        @LoginView("/Login.xhtml")
        @AccessDeniedView("/Login.xhtml")
		@RestrictAtPhase({ PhaseIdType.RESTORE_VIEW,
			PhaseIdType.INVOKE_APPLICATION })
        ADMINISTRATOR,

		@ViewPattern("/Planner.xhtml")
		@LoginView("/Login.xhtml")
		@AccessDeniedView("/Login.xhtml")
		@Planner
		@RestrictAtPhase({ PhaseIdType.RESTORE_VIEW,
				PhaseIdType.INVOKE_APPLICATION })
		PLANNER,
		
		@ViewPattern("/Reader.xhtml")
		@LoginView("/Login.xhtml")
		@AccessDeniedView("/Login.xhtml")
		@Planner
		@RestrictAtPhase({ PhaseIdType.RESTORE_VIEW,
				PhaseIdType.INVOKE_APPLICATION })
		READER,
		
		

        @FacesRedirect
        @ViewPattern("/*")
        @AccessDeniedView("/Login.xhtml")
        ALL;

    }
}
