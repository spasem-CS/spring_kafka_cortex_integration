package com.spasem.kafka.listener;

import com.google.gson.Gson;
import com.spasem.kafka.connection.MongoConnection;
import org.bson.Document;
import org.springframework.context.annotation.Lazy;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;


@Service
@Lazy(false)
public class KafkaConsumer {
    private HashMap<String,String> mongoKeyMap = null;
    private MongoConnection connection = null;

    public KafkaConsumer() throws IOException {
        readMongoJsonFile();
        connection = new MongoConnection();
    }


    @KafkaListener(topics = "user_events",
    groupId = "user_events", containerFactory = "kafkaListenerContainerFactory")
    public void consumeJson(String event) {
        try {
            System.out.println(event);
            if(event!=null){
                HashMap holder = this.parseUser(event);
                Document document = this.mongoKeyMapper(holder);

                connection.write( "user_events", document);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private HashMap<String, String> parseUser(String event){
        HashMap<String, String> holder = new HashMap();
        String[] keyVals = event.split(", ");
        for(String keyVal:keyVals)
        {
            String[] parts = keyVal.split("=",2);
            holder.put(parts[0],parts[1]);
        }
        return holder;
    }

    private Document mongoKeyMapper(HashMap<String, String> eventMap){
        Document document = new Document(  );


        for ( Map.Entry<String, String> configEntry:mongoKeyMap.entrySet()) {
            String key = configEntry.getKey();
            String value = configEntry.getValue();
            if(eventMap.containsKey( key )){
                document.put( value, eventMap.get( key ) );
            }
        }
        return document;
    }

    private void readMongoJsonFile() throws IOException {
        StringBuilder builder = new StringBuilder(  );
        try(BufferedReader br = new BufferedReader(new FileReader("mongo_keys_config.json"))) {
            for(String line; (line = br.readLine()) != null; ) {
                builder.append( line );
            }
        }
        Gson gson = new Gson();
        String json = builder.toString();
        Map<String,String> map = new HashMap<String,String>();
        this.mongoKeyMap = (HashMap<String, String>) gson.fromJson(json, map.getClass());
    }
    @PostConstruct
    public void preStart(){
        System.out.println("Started listening");
    }
}
