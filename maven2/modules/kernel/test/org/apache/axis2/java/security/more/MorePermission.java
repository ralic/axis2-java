/*
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
 
package org.apache.axis2.java.security.more;

import org.apache.axis2.java.security.AccessController;
import org.apache.axis2.java.security.interf.Actor;

import java.security.PrivilegedAction;

/**
 * MorePermission has read permission to both public.txt and private.txt
 */

public class MorePermission implements Actor {
    
    private Actor  _actor;
    private boolean _usingDoPrivilege;

    // Constructor
    public MorePermission(Actor a, boolean usingDoPrivilege) {
	_actor = a;
	_usingDoPrivilege = usingDoPrivilege;

    }

    // Implementing Actor's takeAction method
    public void takeAction() {
	try {
	    if (_usingDoPrivilege) {
		// Use AccessController's doPrivilege
		AccessController.doPrivileged(
		    new PrivilegedAction() {
			public Object run() {
			    _actor.takeAction();
			    return null;
			    }
			});
	    }
	    else {
		// Use no AccessController's doPrivilege
		_actor.takeAction();   
	    }
	} catch (Exception e) {
	    e.printStackTrace(System.out);
	}
    }    
}
