package definition;

import java.io.Serializable;




	  public class OrganizationDef implements Serializable{

		  private static final long serialVersionUID = -8349963947101031989L;
		  private String id_organization;
		  private String name_of_organization;
	
		  boolean editable;

	  
	  public OrganizationDef(String idOrganization, String nameOfOrganization)
	  {
		  this.id_organization = idOrganization;
		  this.name_of_organization = nameOfOrganization;
	  }
	  
	  public String getNameOfOrganization()
	  {
		  return name_of_organization;
	  }
	  
	  
	  public void setNameOfOrganization(String organization)
	  {
		  this.name_of_organization = organization;
	  }
	  
	  public void setIdOrganization(String id)
	  {
		  this.id_organization = id;
	  }
	  
	  public String getIdOrganization()
	  {
		  return id_organization;
	  }
	  
	 
	  
	  public boolean isEditable() {
			return editable;
		}
		public void setEditable(boolean editable) {
			this.editable = editable;
		}
	  
	  

}
