/**
 * Copyright 2001-2004 The Apache Software Foundation.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.axis.wsdl.wom.impl;

import java.util.HashMap;



import org.apache.axis.wsdl.wom.Component;
import org.apache.axis.wsdl.wom.WSDLConstants;
import javax.xml.namespace.QName; 




/**
 * @author Chathura Herath
 *
 */
public class ComponentImpl implements  WSDLConstants, Component{

    protected HashMap componentProperties = new HashMap();
    
    /**
     * Returns the properties that are specific to this WSDL Component.
     * 
     */
    public HashMap getComponentProperties() {
        return componentProperties;
    }
    /**
     * Sets the properties of the Component if any.
     */
    public void setComponentProperties(HashMap properties) {
        this.componentProperties = properties;
    }
    
    protected void checkValidityOfNamespaceWRTWSDLContext(QName qName){
        for(int i=0; i< WSDL_NAMESPACES.length; i++){
            if(qName.getNamespaceURI() == WSDL_NAMESPACES[i]) 
               return;
        }
        throw new WSDLProcessingException("The Namespace of the QName is not a valid WSDL namespace");
    }
}
