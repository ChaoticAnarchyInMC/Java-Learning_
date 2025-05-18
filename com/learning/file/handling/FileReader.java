package org.example.com.learning.file.handling;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FileReader {
    public void readFile(String fileName){
        ArrayList<LoginInfo> logInfoList = new ArrayList<>();
        HashMap<String, Integer> attemptCounter = new HashMap<>();
        try (
                BufferedReader br = new BufferedReader(new java.io.FileReader(fileName))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] splitLine = line.split("\\|");
                //System.out.println(line);
                String[] splitSuccess = splitLine[3].split("\\:");
                if (!"SUCCESS".equalsIgnoreCase(splitSuccess[1].trim())) {
                    String[] pureUsername = splitLine[1].split("\\:");
                    LoginInfo something =  new LoginInfo(pureUsername[1],splitLine[2],"FAILED".equalsIgnoreCase(splitSuccess[1]));
                    logInfoList.add(something);
                }


            }
        } catch (
                IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        for(LoginInfo loginInfo: logInfoList){

            attemptCounter.putIfAbsent(loginInfo.getUsername(), 1); // Set initial value as 1 if absent
            attemptCounter.put(loginInfo.getUsername(), attemptCounter.get(loginInfo.getUsername()) + 1);

        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            writeToFile(attemptCounter, writer);
            System.out.println("hello");
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }

    private static void writeToFile(HashMap<String, Integer> attemptCounter, BufferedWriter writer) throws IOException {
        for (HashMap.Entry<String, Integer> pair : attemptCounter.entrySet()) {

            writer.write(" username is " + pair.getKey() + " NO of failed Attempts is " + pair.getValue());
            writer.newLine(); // Adds a new line
            System.out.println("Successfully written with BufferedWriter.");
        }
    }

    public static void main(String[] args){
        FileReader question = new FileReader();
        question.readFile("/Users/keshavmanavala/IdeaProjects/fileHandling/src/main/resources/loginAttempts.txt");
    }
}
