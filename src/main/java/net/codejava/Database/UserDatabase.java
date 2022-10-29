package net.codejava.Database;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static javax.management.Query.and;
import static javax.management.Query.eq;


public class UserDatabase {

    public static void addUser(String username, String password) throws IOException, InterruptedException {
        try {
            Connection.executeCommand("touch insert.js", false);
            String insertCommand = String.format("db.users.insertOne({Username:%s, Password:%s})", "'" + username + "'", "'$$" + password + "'");
            Files.writeString(Path.of("insert.js"), insertCommand);
            String command = "mongosh --host 127.0.0.1 --port 27017 --file insert.js"; //dodelat script
            Connection.executeCommand(command, false);
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                Connection.executeCommand("rm insert.js", false);
            }
            catch (Exception e2) {
                e2.getMessage();
            }
        }
    }

    public static boolean validateUser(String username, String enteredPassword) throws IOException, InterruptedException {
        try {
            Connection.executeCommand("touch find.js", false);
            String findCommand = String.format("db.users.find({Username:%s},{ Password:1,_id:0}).forEach(function(myDocument){print(myDocument.Password);});", '"' + username + '"');
            Files.writeString(Path.of("find.js"), findCommand);
            String command = "mongosh --host 127.0.0.1 --port 27017 --file find.js";
            String savedPassword = Connection.executeCommand(command, true);
            System.out.println("Saved: " + savedPassword);
            if (("$$" + enteredPassword).equals(savedPassword)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.getMessage();
            return false;
        } finally {
            Connection.executeCommand("rm find.js", false);
        }
    }

    //dodělá Filip
    public static boolean isInjected() {
        return false;
    }

}
