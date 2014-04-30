package database;

import javax.ejb.EJB;
import javax.persistence.EntityManager;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.core.api.annotation.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(Arquillian.class)

public class TestDatabase {

   @Deployment

   public static Archive createTestArchive() {

      return ShrinkWrap.create(WebArchive.class,"test.war")

    		  .addClass(Task.class)
              .addClass(Organization.class);

   }
    
   @Inject
   EntityManager em;

   @Test
   public void TestDatabase() throws Exception {

	   Organization org = new Organization();
	  
	   
	   em.getTransaction().begin();
	   org.setNameOfOrganization("nieco");
	  em.getTransaction().commit();
	   
	   
	   
   }

}