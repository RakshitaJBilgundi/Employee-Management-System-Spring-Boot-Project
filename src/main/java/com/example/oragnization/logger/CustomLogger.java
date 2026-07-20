package com.example.oragnization.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomLogger {

    private static final String LOG_FILE = "logs/app.log";

    public static void log(String level, String message) {

        try {

            File folder = new File("logs");
            if (!folder.exists()) {
                folder.mkdirs();
            }

            File file = new File(LOG_FILE);

            if (!file.exists()) {
                file.createNewFile();
            }

            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            String log =
                    "[" + LocalDateTime.now().format(formatter) + "] "
                    + level + " - " + message;

            BufferedWriter writer =
                    new BufferedWriter(new FileWriter(file, true));

            writer.write(log);
            writer.newLine();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}