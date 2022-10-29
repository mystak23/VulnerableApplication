package net.codejava.Database;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.IOException;

import static javax.management.Query.and;
import static javax.management.Query.eq;


public class UserDatabase {

    private static int count = 0;

    public static void addUser(String username, String password) throws IOException, InterruptedException {
        System.out.println(username + password);
        String formatedUsername = '"' + username + '"';
        String formatedPassword = '"' + password + '"';
        System.out.println(formatedUsername + formatedPassword);
        String script = String.format("db.users.insertOne({Username:%s, Password: %s})", formatedUsername, formatedPassword);
        System.out.println(script);
        String fileName = "insert.js";
        System.out.println(fileName);
        Connection.writeCommand("touch " + fileName);
        Connection.writeCommand("chmod 777 " + fileName);
        String echoCommand = "echo " + '"' + script + '"' + " > " + fileName;
        System.out.println(echoCommand);
        Connection.writeCommand(echoCommand);
        Connection.writeCommand("cat " + fileName);
        String command = "mongosh --host 127.0.0.1 --port 27017 --file " + fileName; //dodelat script
        System.out.println(command);
        Connection.writeCommand(command);
        //Connection.writeCommand("rm " + fileName);
    }

    public static boolean validateUser(String username, String enteredPassword) throws IOException, InterruptedException {
        String formatedUsername = '"'+ username + '"';
        String script = String.format("printjson(db.users.find({Username:%s, Password:1}))", formatedUsername);
        String fileName = "find.js";
        Connection.writeCommand("echo " + script + " > " + fileName);
        String command = "mongosh --host 127.0.0.1 --port 27017 --file find.js";
        Connection.writeCommand(command);
        Connection.writeCommand("rm find.js");
        String savedPassword = enteredPassword; //nejak parsovat string z toho inputu na password
        if (enteredPassword.equals(savedPassword)) {
            return true;
        } else {
            return false;
        }
    }

    private static String count() {
        count++;
        return String.valueOf(count);
    }
    //Filip
    public static boolean isInjected() {
        return false;
    }

}
