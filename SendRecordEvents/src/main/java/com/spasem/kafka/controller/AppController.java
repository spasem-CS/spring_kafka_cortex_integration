package com.spasem.kafka.controller;

import com.spasem.kafka.producer.EventsGeneration;
import com.spasem.kafka.producer.RecordsProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

@RestController
public class AppController {

@Autowired
    RecordsProducer recordsProducer;
    @PostMapping("/invoke")
    public ResponseEntity invoke() throws IOException {
        HashMap payload = new HashMap();
        payload.put( "success" , true );
        payload.put( "message" , "Produced 10000 records and sent to kafka" );

        HashMap success = new HashMap();
        success.put( "payload", payload);
        String[] args = {};
        //EventsGeneration.main( args );
        recordsProducer.produce();

        ResponseEntity res = ResponseEntity.ok( success );
        return res;
    }
}
