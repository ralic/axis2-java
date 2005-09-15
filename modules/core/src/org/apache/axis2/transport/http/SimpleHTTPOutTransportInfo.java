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

package org.apache.axis2.transport.http;

import org.apache.axis2.transport.http.server.SimpleResponse;
import org.apache.commons.httpclient.Header;

public class SimpleHTTPOutTransportInfo implements HTTPOutTransportInfo {
    private SimpleResponse outInfo;
    private String encoding;

    public SimpleHTTPOutTransportInfo(SimpleResponse outInfo) {
        this.outInfo = outInfo;
    }

    public void setContentType(String contentType) {
        if(encoding != null){
            contentType = contentType + ";charset=" + encoding;
        }
        outInfo.setHeader(new Header(HTTPConstants.HEADER_CONTENT_TYPE,contentType));
    }

    public void setCharacterEncoding(String encoding) {
        this.encoding = encoding;
    }
}
