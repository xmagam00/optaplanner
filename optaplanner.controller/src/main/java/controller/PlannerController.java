package controller;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import database.User;


@Named
@RequestScoped
public class PlannerController {
	
	@Inject
	EntityManager eManager;
	
	@Inject
	private FacesContext facesContext;
	
	@Produces
	private User user;
	
	private Long id;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void retrieve() {

			user = eManager.getReference(User.class,id);
	}
	
	public User getUser() {
		return user;
	}

}
