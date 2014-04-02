package database;


import java.util.List;

import javax.persistence.*;

import org.junit.*;

public class TestDatabase {
	private static final String PERSISTENCE_UNIT_NAME = "user";
	  private static EntityManagerFactory factory;
	  
	  @Test
	  public void testUser()
	  {	
		  factory = Persistence.createEntityManagerFactory("optaplanner");
		    EntityManager em = factory.createEntityManager();
		    // read the existing entries and write to console
		    Query q = em.createQuery("select t from Todo t");
	  
	  
	  
}
}