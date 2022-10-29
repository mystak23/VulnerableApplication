package net.codejava.Database;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Connection {

    public static String writeCommand(String command) throws IOException, InterruptedException {
        try {
            Process proc = Runtime.getRuntime().exec(command);

            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));

            String line = "";
            String output = "";
            while((line = reader.readLine()) != null) {
                //System.out.print(line + "\n");
                output += (line + "\n");
                System.out.println(output);
            }
            proc.waitFor();
            return output;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

}
