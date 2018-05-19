package io.secondconstantine.shell.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UnixShell {

    private UnixShell() {
    }

    public static String executeCommand(String command) {
        StringBuilder output = new StringBuilder();

        try {
            Process p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
        } catch (InterruptedException | IOException var5) {
            return var5.toString();
        }

        return output.toString();
    }
}