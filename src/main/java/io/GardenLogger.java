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

    private static final int TYPE_WIDTH = 25;
    private static final int MESSAGE_WIDTH = 50;

    /**
     * Writes a log message to the log file with a timestamp.
     * @param logType The type of log message.
     * @param message The message to log.
     */
    public synchronized static void log(String logType, String message) {
        String timestamp = formatter.format(LocalDateTime.now());
        String logTypeString = "[" + logType + "]";
        String logMessage = String.format("%s %-" + TYPE_WIDTH + "s %s%n", timestamp, logTypeString, message);

        try {
            Files.writeString(Paths.get(LOG_FILE_PATH), logMessage, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.print(logMessage);
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }

    public static void clearLog() {
        try {
            Files.writeString(Paths.get(LOG_FILE_PATH), "", StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.err.println("Error deleting log file: " + e.getMessage());
        }
    }
}