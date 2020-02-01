package com.spasem.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;

@RestController
public class AppController {

    @PostMapping("/invoke")
    public ResponseEntity invoke() throws IOException {
        HashMap payload = new HashMap();
        payload.put( "success" , true );
        payload.put( "message" , "Started listening" );

        HashMap success = new HashMap();
        success.put( "payload", payload);
        String[] args = {};

        ResponseEntity res = ResponseEntity.ok( success );
        return res;
    }
}
