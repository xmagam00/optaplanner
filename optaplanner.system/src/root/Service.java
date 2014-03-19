package root;


import javax.*;
import javax.jws.WebMethod;

import javax.jws.WebService;


@WebService
public class Service
{
	@WebMethod
	 

		    public String sayHello()
		    {
	        return "Hello";
		    }

	public Service getHelloWebServicePort() {
		
		return null;
	}
}