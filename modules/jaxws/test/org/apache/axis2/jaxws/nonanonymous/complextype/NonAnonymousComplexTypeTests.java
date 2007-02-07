/**
 * 
 */
package org.apache.axis2.jaxws.nonanonymous.complextype;

import javax.xml.ws.WebServiceException;

import junit.framework.TestCase;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.apache.axis2.jaxws.nonanonymous.complextype.sei.EchoMessagePortType;
import org.apache.axis2.jaxws.nonanonymous.complextype.sei.EchoMessageService;
import org.apache.axis2.jaxws.framework.AbstractTestCase;

public class NonAnonymousComplexTypeTests extends AbstractTestCase {

	/**
	 * 
	 */
	public NonAnonymousComplexTypeTests() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public NonAnonymousComplexTypeTests(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

    public static Test suite() {
        return getTestSetup(new TestSuite(NonAnonymousComplexTypeTests.class));
    }

	public void testSimpleProxy() {
		System.out.println("------------------------------");
		System.out.println("Test : "+getName());
		try {
			String msg = "Hello Server";
		    EchoMessagePortType myPort = (new EchoMessageService()).getEchoMessagePort();
		    String response = myPort.echoMessage(msg);
		    System.out.println(response);
		    System.out.println("------------------------------");
		} catch (WebServiceException webEx) {
			webEx.printStackTrace();
			fail();
		}
	}

		    


}
