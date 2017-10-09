package com.scratch;

import com.scratch.config.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by roshane on 4/22/2017.
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RestController
    public static class ApiController {


        private final WebConfig webConfig;

        public ApiController(WebConfig webConfig) {
            this.webConfig = webConfig;
        }

        @RequestMapping
        public ResponseEntity<String> index() {
            return ResponseEntity.ok("explore /api");
        }

        @RequestMapping("/api")
        public ResponseEntity<?> echo() {
            Map<String, String> resp = new HashMap<>();
            resp.put("message", "hello from " + Application.class.getSimpleName());
            resp.put("appName",webConfig.getName());
            return ResponseEntity.ok(resp);
        }
    }
}
