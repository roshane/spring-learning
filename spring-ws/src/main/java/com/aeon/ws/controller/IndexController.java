package com.aeon.ws.controller;

import com.aeon.ws.client.IpClient;
import com.aeon.ws.integration.GeoIP;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by roshane on 10/5/2017.
 */
@RestController
@RequestMapping("/api")
public class IndexController {

    private final IpClient ipClient;

    public IndexController(final IpClient ipClient) {
        this.ipClient = ipClient;
    }

    @GetMapping("/quota")
    public ResponseEntity<?> getQuotas(@RequestParam("ip") String ip) {
        GeoIP response = ipClient.getDestailsByIp(ip);
        return ResponseEntity.ok(response);
    }
}
