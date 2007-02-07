/*
 * Copyright 2006 The Apache Software Foundation.
 * Copyright 2006 International Business Machines Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.axis2.jaxws.sample;

import javax.xml.bind.JAXBContext;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.Service.Mode;
import javax.xml.ws.soap.SOAPBinding;

import junit.framework.TestCase;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.apache.axis2.jaxws.sample.addressbook.AddEntry;
import org.apache.axis2.jaxws.sample.addressbook.AddEntryResponse;
import org.apache.axis2.jaxws.sample.addressbook.AddressBook;
import org.apache.axis2.jaxws.sample.addressbook.AddressBookEntry;
import org.apache.axis2.jaxws.sample.addressbook.ObjectFactory;
import org.apache.axis2.jaxws.framework.AbstractTestCase;

/**
 * This tests the AddressBook same service that exists under
 * org.apache.axis2.jaxws.sample.addressbook.*
 */
public class AddressBookTests extends AbstractTestCase {

    private static final String NAMESPACE = "http://org/apache/axis2/jaxws/sample/addressbook";
    private static final QName QNAME_SERVICE = new QName(
            NAMESPACE, "AddressBookService");
    private static final QName QNAME_PORT = new QName(
            NAMESPACE, "AddressBook");
    private static final String URL_ENDPOINT = "http://localhost:8080/axis2/services/AddressBookService";
    
    public static Test suite() {
        return getTestSetup(new TestSuite(AddressBookTests.class));
    }

    /**
     * Test the endpoint by invoking it with a JAX-WS Dispatch.  
     */
    
    public void testAddressBookWithDispatch() throws Exception {
        try {
        System.out.println("----------------------------------");
        System.out.println("test: " + getName());
        
        JAXBContext jbc = JAXBContext.newInstance("org.apache.axis2.jaxws.sample.addressbook");
        
        // Create the JAX-WS client needed to send the request
        Service service = Service.create(QNAME_SERVICE);
        service.addPort(QNAME_PORT, SOAPBinding.SOAP12HTTP_BINDING, URL_ENDPOINT);
        Dispatch<Object> dispatch = service.createDispatch(
                QNAME_PORT, jbc, Mode.PAYLOAD);
                
        // Create the JAX-B object that will hold the data
        ObjectFactory factory = new ObjectFactory();
        AddEntry request = factory.createAddEntry();
        AddressBookEntry content = factory.createAddressBookEntry();
        
        content.setFirstName("Ron");
        content.setLastName("Testerson");
        content.setPhone("512-459-2222");
        
        // Since this is a doc/lit wrapped WSDL, we need to set the 
        // data inside of a request wrapper element.
        request.setEntry(content);
        
        AddEntryResponse response = (AddEntryResponse) dispatch.invoke(request);
        
        // Validate the results
        assertNotNull(response);
        assertTrue(response.isStatus());
        System.out.println("[pass]     - valid response received");
        System.out.println("[response] - " + response.isStatus());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    
    /**
     * Test the "addEntry" operation.  This sends a complex type and returns
     * a simple type.
     */
    public void testAddEntry() throws Exception {
        System.out.println("----------------------------------");
        System.out.println("test: " + getName());
        
        // Create the JAX-WS client needed to send the request
        Service service = Service.create(QNAME_SERVICE);
        AddressBook ab = service.getPort(QNAME_PORT, AddressBook.class);
        BindingProvider p1 = (BindingProvider) ab;
        p1.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, URL_ENDPOINT);
        
        ObjectFactory factory = new ObjectFactory();
        AddressBookEntry content = factory.createAddressBookEntry();
        content.setFirstName("Foo");
        content.setLastName("Bar");
        content.setPhone("512-459-2222");
        
        boolean added = ab.addEntry(content);
        
        // Validate the results
        assertNotNull(added);
        assertTrue(added);
    }
    
    /**
     * Test the "findEntryByName" operation.  This sends a simple type and 
     * returns a complex type.
     */
    public void testFindEntryByName() throws Exception {
        System.out.println("----------------------------------");
        System.out.println("test: " + getName());
        
        // Create the JAX-WS client needed to send the request
        Service service = Service.create(QNAME_SERVICE);
        AddressBook ab = service.getPort(QNAME_PORT, AddressBook.class);
        BindingProvider p1 = (BindingProvider) ab;
        p1.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, URL_ENDPOINT);
        
        String fname = "Joe";
        String lname = "Test";
        AddressBookEntry result = ab.findEntryByName(fname, lname);
        
        // Validate the results
        assertNotNull(result);
        assertNotNull(result.getFirstName());
        assertNotNull(result.getLastName());
        assertTrue(result.getFirstName().equals(fname));
        assertTrue(result.getLastName().equals(lname));
    }
    
}
