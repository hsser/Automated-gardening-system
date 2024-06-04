package io;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GardenLogger {
    private static final String LOG_FILE_PATH = "src/main/files/garden_log.txt";  // Path to the log file
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    /**
     * Writes a log message to the log file with a timestamp.
     * @param currentDayInGarden The current day in the garden.
     * @param message The message to log.
     */
    public static void log(int currentDayInGarden, String message) {
        String timestamp = formatter.format(LocalDateTime.now());
        String logMessage = String.format("%s: [Day %d] %s%n", timestamp, currentDayInGarden, message);
        System.out.print(logMessage);  // Print the log message to the console

        try {
            Files.writeString(Paths.get(LOG_FILE_PATH), logMessage, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}