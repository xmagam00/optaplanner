package root;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;


@WebService
public class Convertor
{
	
	public class Converter
	{
	  public float celsiusToFarenheit ( float celsius )
	  {
	    return (celsius * 9 / 5) + 32;
	  }

	  public float farenheitToCelsius ( float farenheit )
	  {
	    return (farenheit - 32) * 5 / 9;
	  }
	}
}