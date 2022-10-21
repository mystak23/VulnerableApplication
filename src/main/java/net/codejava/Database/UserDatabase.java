package net.codejava.Database;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import static javax.management.Query.and;
import static javax.management.Query.eq;


public class UserDatabase {

    private static Connection connection;
    private static Document document;

    //Filip
    public static void addUser(String username, String password) {
        if (connection == null) {
            connection = new Connection();
            if (!connection.connect()) {
                System.out.println("Cannot connect to the database.");
                return;
            }
        }
        document = new Document("username", "password").append(username, password);
        connection.addRecord(document);
     }

     //Filip
    public static boolean validateUser(String username, String password) {
        //FindIterable<Document> findIterable = connection.getCollection().find(new Document());
        try {
            FindIterable<Document>  findIterable = connection.getCollection().find(Filters.and(Filters.eq("username", username), Filters.eq("password", password)));
            if (findIterable != null) {
                return true;
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

    //Filip
    public static boolean isInjected() {
        return false;
    }

}
