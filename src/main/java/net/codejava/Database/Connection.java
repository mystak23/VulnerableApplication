package net.codejava.Database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Connection {

    public static String executeCommand(String command, boolean isInsertingPassword) throws IOException, InterruptedException {
        try {
            Process proc = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = "";
            String output = "";
            while((line = reader.readLine()) != null) {
                if (isInsertingPassword) {
                    if (line.contains("$$")) {
                        output = line;
                    }
                }
            }
            proc.waitFor();
            return output;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
}
