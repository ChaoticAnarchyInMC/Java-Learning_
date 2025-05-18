package org.example.com.learning.file.handling;

import com.opencsv.CSVWriter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class FileReaderCSV {

    public void readFile(String fileName) {
        HashMap<String, Integer> attemptCounter = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split on | and trim each part
                String[] splitLine = line.split("\\|");
                if (splitLine.length < 4) continue;

                // Trim each field
                String usernameField = splitLine[1].trim(); // e.g., "Username:keshav"
                String resultField = splitLine[3].trim();   // e.g., "FAILED"

                // Extract username
                if (!usernameField.toLowerCase().startsWith("username:")) continue;
                String username = usernameField.split(":", 2)[1].trim();

                // Check if result is FAILED (case-insensitive)
                if ("FAILED".equalsIgnoreCase(resultField)) {
                    attemptCounter.put(username, attemptCounter.getOrDefault(username, 0) + 1);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        writeToCSV(attemptCounter);
    }

    private void writeToCSV(HashMap<String, Integer> attemptCounter) {
        try (CSVWriter csvWriter = new CSVWriter(
                new FileWriter("output.csv"),
                '#', '\'', CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END)) {

            csvWriter.writeNext(new String[]{"username", "failedLoginAttempts"});

            for (HashMap.Entry<String, Integer> entry : attemptCounter.entrySet()) {
                csvWriter.writeNext(new String[]{entry.getKey(), String.valueOf(entry.getValue())});
            }

            System.out.println("✔ Output written to output.csv");

        } catch (IOException e) {
            System.err.println("Error writing CSV: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        FileReaderCSV reader = new FileReaderCSV();
        reader.readFile("/Users/keshavmanavala/IdeaProjects/fileHandling/src/main/resources/loginAttempts.txt");
    }
}
