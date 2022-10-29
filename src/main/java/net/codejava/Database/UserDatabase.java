package net.codejava.Database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;


public class UserDatabase {

    private static final Logger logger = Logger.getLogger(String.valueOf(UserDatabase.class));

    public static void addUser(String username, String password) throws IOException, InterruptedException {
        try {
            Connection.executeCommand("touch insert.js", false);
            String insertCommand = String.format("db.users.insertOne({Username:%s, Password:%s})", "'" + username + "'", "'$$" + password + "'");
            Files.writeString(Path.of("insert.js"), insertCommand);
            String command = "mongosh --host 127.0.0.1 --port 27017 --file insert.js"; //dodelat script
            Connection.executeCommand(command, false);
            logger.info("User " + username + " added.");
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
            if (("$$" + enteredPassword).equals(savedPassword)) {
                logger.info("Login of " + username + " successful. Password: " + enteredPassword);
                return true;
            } else {
                logger.info("Login of " + username + " unsuccesful.");
                return false;
            }
        } catch (Exception e) {
            e.getMessage();
            return false;
        } finally {
            Connection.executeCommand("rm find.js", false);
        }
    }

    public static boolean isInjected() {

        //nastuduj a připrav si příkaz, kterým v té appce exploituješ aplikaci (jakkoliv)
        //tady ten příkaz napiš a jakože pokud se rovnají, tak to vrátí true a tím pádem to napíše "injected"

        return false;
    }

}
