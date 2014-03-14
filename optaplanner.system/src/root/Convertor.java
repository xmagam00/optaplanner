package root;

import javax.xml.ws.Endpoint;



public class Convertor
{
	public static void main(String[] args) {
		 
        Endpoint.publish("http://localhost:9090/HelloWeb", new Service());
 
        System.out.println("HelloWeb service is ready");
 
    }
}