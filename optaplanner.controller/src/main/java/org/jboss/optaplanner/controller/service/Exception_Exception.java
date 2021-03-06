
package org.jboss.optaplanner.controller.service;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.4.6
 * 2014-05-09T06:01:13.439+02:00
 * Generated source version: 2.4.6
 */

@WebFault(name = "Exception", targetNamespace = "http://server.service.optaplanner.jboss.org/")
public class Exception_Exception extends java.lang.Exception {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private org.jboss.optaplanner.controller.service.Exception exception;

    public Exception_Exception() {
        super();
    }
    
    public Exception_Exception(String message) {
        super(message);
    }
    
    public Exception_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public Exception_Exception(String message, org.jboss.optaplanner.controller.service.Exception exception) {
        super(message);
        this.exception = exception;
    }

    public Exception_Exception(String message,org.jboss.optaplanner.controller.service.Exception exception, Throwable cause) {
        super(message, cause);
        this.exception = exception;
    }

    public org.jboss.optaplanner.controller.service.Exception getFaultInfo() {
        return this.exception;
    }
}
