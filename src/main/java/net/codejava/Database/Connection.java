package net.codejava.Database;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Connection {

    private MongoDatabase db;
    private MongoCollection<Document> collection;
    private MongoClient mongoClient;

    public boolean connect() {
        try {
            mongoClient = MongoClients.create();
            db = mongoClient.getDatabase("UserDatabase");
            db.createCollection("user");
            collection = db.getCollection("user");
            System.out.println("Connection estabilished.");
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }

    //Filip
    public void addRecord(Document document) {
        collection.insertOne(document);
    }

    public MongoCollection<Document> getCollection() {
        return collection;
    }

}
