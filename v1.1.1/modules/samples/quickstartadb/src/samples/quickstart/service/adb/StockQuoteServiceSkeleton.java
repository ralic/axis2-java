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
package samples.quickstart.service.adb;
import samples.quickstart.service.adb.xsd.GetPriceResponse;
import samples.quickstart.service.adb.xsd.Update;
import samples.quickstart.service.adb.xsd.GetPrice;

import java.util.HashMap;
public class StockQuoteServiceSkeleton implements StockQuoteServiceSkeletonInterface {
    private HashMap map = new HashMap();

    public void update(Update param0) {
        map.put(param0.getSymbol(), new Double(param0.getPrice()));
    }

    public GetPriceResponse getPrice(GetPrice param1) {
        Double price = (Double) map.get(param1.getSymbol());
        double ret = 42;
        if(price != null){
            ret = price.doubleValue();
        }
        GetPriceResponse res =
                new GetPriceResponse();
        res.set_return(ret);
        return res;
    }
}
