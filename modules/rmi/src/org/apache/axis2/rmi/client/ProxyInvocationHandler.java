/*
 * Copyright 2004,2005 The Apache Software Foundation.
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
package org.apache.axis2.rmi.client;

import org.apache.axis2.rmi.Configurator;
import org.apache.axis2.AxisFault;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


public class ProxyInvocationHandler implements InvocationHandler {

    private RMIClient rmiClient;

    public ProxyInvocationHandler(Class interfaceClass,
                                  Configurator configurator,
                                  String epr) throws AxisFault {
        this.rmiClient = new RMIClient(interfaceClass,configurator,epr);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // invoke the rmiclient object
        return this.rmiClient.invokeMethod(method.getName(),args);
    }
}
