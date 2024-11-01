package io.skcloud.goblintest.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class EchoController {
    @RequestMapping("/echo")
    public ResponseEntity<Map<String, Object>> echo(HttpServletRequest request, @RequestBody(required = false) Map<String, Object> body) {
        Map<String, Object> response = new LinkedHashMap<>();
        Map<String, String> headers = new LinkedHashMap<>();
        request.getHeaderNames().asIterator().forEachRemaining(headerName -> headers.put(headerName, request.getHeader(headerName)));
        response.put("method", request.getMethod());
        response.put("body", body);
        response.put("headers", headers);
        response.put("params", request.getParameterMap());
        response.put("query", request.getQueryString());
        return ResponseEntity.ok(response);
    }

    @RequestMapping("/body")
    public ResponseEntity<Map<String, Object>> body(HttpServletRequest request, @RequestBody(required = false) Map<String, Object> body) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("body", body);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/soap")
    public ResponseEntity<String> handleSoapRequest(HttpServletRequest request) throws IOException {
        StringBuilder soapMessage = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                soapMessage.append(line);
            }
        }

        return ResponseEntity.ok()
                .header("Content-Type", "application/soap+xml")
                .body(soapMessage.toString());
    }

}
