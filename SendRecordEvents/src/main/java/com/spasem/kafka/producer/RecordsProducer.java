
package com.spasem.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Insights Producer
 * @author spasem
 */

@Service
public class RecordsProducer {
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    private String TOPIC = "user_events";

    public void produce(){
        try {
            readFileAndProduce();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendToKafka(String message){
        System.out.println("Sending message to kafka : "+message);
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send( TOPIC, message );
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void readFileAndProduce() throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader("events.log"))) {
            for(String line; (line = br.readLine()) != null; ) {
                sendToKafka( line );
            }
        }
    }

}

