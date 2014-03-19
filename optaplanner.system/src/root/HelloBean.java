package root;

public class HelloBean {
	 public HelloBean()
	 	    {
	 	    }
		 
		    public void callWebService()
	 	    {
	 	        try
	 	        {
	            Service service =
		                new Service();
	             Service port =
	                 service.getHelloWebServicePort();
	  
	 	            java.lang.String result = port.sayHello();
	 	            System.out.println("Result = "+result);
		        }
		        catch (Exception e)
	 	        {
	 	            e.printStackTrace();
		        }
	 	    }
}
