package org.jboss.optaplanner.entities;



import javax.persistence.*;

@Entity
@Table(name="organization")

public class Organization{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idOrganization;
	private String nameOfOrganization;
	
	
	public long getIdOrganization() {
	    return idOrganization;
	  }

	  public void setIdOrganization(long idOrganization) {
	    this.idOrganization = idOrganization;
	  }

	  public String getNameOfOrganization() {
	    return nameOfOrganization;
	  }
	  
	  public void setNameOfOrganization(String NameOfOrganization) {
		    this.nameOfOrganization = NameOfOrganization;
		  }
	  


}
