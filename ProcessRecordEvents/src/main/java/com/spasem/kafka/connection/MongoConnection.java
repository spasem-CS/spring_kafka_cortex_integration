package com.spasem.kafka.connection;

import com.google.gson.JsonObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.types.ObjectId;

public class MongoConnection {
    private MongoDatabase connection;
    private MongoClient mongoClient;

    public MongoConnection(){
        this.connection = getConnection();
    }
    private MongoDatabase getConnection() {
        String mongoURI = "mongodb://localhost:27017/admin";
        MongoClientURI connectionString = new MongoClientURI(mongoURI);
        mongoClient = new MongoClient(connectionString);
        MongoDatabase database = mongoClient.getDatabase(connectionString.getDatabase());
        return database;
    }
    private MongoCollection<Document> getMongoCollection(String collectionName) {
        return connection.getCollection(collectionName);
    }

    public ObjectId write(String collection, Document document){
        if(collection==null | document==null){
            throw new RuntimeException("Collection name or passed document empty");
        }

        getMongoCollection(collection).insertOne(document);
        ObjectId _id = (ObjectId) document.get( "_id" );

        return _id;
    }

    public FindIterable<Document> read(String collection, Document filter){
        MongoCollection<Document> mongoCollection =  getMongoCollection(collection);
        FindIterable<Document> updateResults = mongoCollection.find(filter);

        return updateResults;
    }


}
