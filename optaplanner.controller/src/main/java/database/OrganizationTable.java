package database;



import javax.persistence.*;

@Entity
@Table(name="organization")
public class OrganizationTable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_organization;
	private String name_of_organization;
	
	public long getIdOrganization() {
	    return id_organization;
	  }

	  public void setIdOrganization(long idOrganization) {
	    this.id_organization = idOrganization;
	  }

	  public String getNameOfOrganization() {
	    return name_of_organization;
	  }
	  
	  public void setNameOfOrganization(String NameOfOrganization) {
		    this.name_of_organization = NameOfOrganization;
		  }


}
