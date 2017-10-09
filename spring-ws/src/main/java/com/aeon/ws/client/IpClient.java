package com.aeon.ws.client;

import com.aeon.ws.integration.GeoIP;
import com.aeon.ws.integration.GetGeoIP;
import com.aeon.ws.integration.GetGeoIPResponse;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

/**
 * Created by roshane on 10/5/2017.
 */
public class IpClient extends WebServiceGatewaySupport {

    public GeoIP getDestailsByIp(String ip) {
        GetGeoIP request = new GetGeoIP();
        request.setIPAddress(ip);

        GetGeoIPResponse response = (GetGeoIPResponse) getWebServiceTemplate().
                marshalSendAndReceive("http://www.webservicex.net/geoipservice.asmx", request,
                        new SoapActionCallback("http://www.webservicex.net/GetGeoIP"));

        return response.getGetGeoIPResult();
    }
}
