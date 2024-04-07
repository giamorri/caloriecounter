/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package confluence.caloriecounter;

/**
 *
 * @author yash
 */

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//this has been stolen from pdc lab 2, gonna see if i can use this for our program//
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


import java.io.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class CollectionAndFileIO {
    public static void main(String[] args) {
        Map<String, Integer> scoresMap = loadScoresFromFile();
        
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Enter student's first name (or 'x' to quit): ");
            String name = scanner.nextLine().trim().toLowerCase();
            
            if (name.equals("x")){
                break;
            }
            System.out.println("Enter exam mark");
            int mark = scanner.nextInt();
            scanner.nextLine();
            
            if(scoresMap.containsKey(name)){
                System.out.println("Record already exits for " + name + ".");
                System.out.println("Do you want to overwrite the record? (y/n):");
                String choice = scanner.nextLine().trim().toLowerCase();
                if (choice.equals("y")){
                    scoresMap.put(name, mark);
                    System.out.println("Record updated successfully.");
                }
            } else {
                scoresMap.put(name, mark);
                    System.out.println("Record added successfully.");
            }
            
            saveScoresToFile(scoresMap);
            System.out.println("Exiting program");
        }
    }
        private static Map<String, Integer> loadScoresFromFile(){
            Map<String, Integer> scoresMap = new HashMap<>();
            try (BufferedReader reader = new BufferedReader(new FileReader("./resources/T02_scores.txt"))){
                String line;
                while ((line = reader.readLine()) != null){
                    String[] parts = line.split("\\s+");
                    if (parts.length == 2){
                        String name = parts[0].trim().toLowerCase();
                        int mark = Integer.parseInt(parts[1].trim());
                        scoresMap.put(name, mark);
                    }
                }
            }
                catch (IOException | NumberFormatException e){
                        System.out.println("Error loading scores from file: " + e.getMessage());
                        }
                return scoresMap;
            }
            private static void saveScoresToFile(Map<String, Integer> scoresMap){
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources/T02_score.txt"))){
                    for (Map.Entry<String, Integer> entry : scoresMap.entrySet()) {
                writer.write(entry.getKey() + " " + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
                System.out.println("Error saving scores to file: " + e.getMessage());
        }
    }
}
