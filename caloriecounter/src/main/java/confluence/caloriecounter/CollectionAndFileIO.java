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
        Map<String, Integer> scoresMap = loadFoodsFromFile();
        
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Enter food's name (or 'x' to quit): ");
            String foodName = scanner.nextLine().trim().toLowerCase();
            
            if (foodName.equals("x")){
                break;
            }
            System.out.println("Enter food protein");
            int protein = scanner.nextInt();
            scanner.nextLine();
            
            if(scoresMap.containsKey(foodName)){
                System.out.println("Record already exits for " + foodName + ".");
                System.out.println("Do you want to overwrite the record? (y/n):");
                String choice = scanner.nextLine().trim().toLowerCase();
                if (choice.equals("y")){
                    scoresMap.put(foodName, protein);
                    System.out.println("Record updated successfully.");
                }
            } else {
                scoresMap.put(foodName, protein);
                    System.out.println("Record added successfully.");
            }
            
            saveFoodsToFile(scoresMap);
            System.out.println("Exiting program");
        }
    }
        private static Map<String, Integer> loadFoodsFromFile(){
            Map<String, Integer> scoresMap = new HashMap<>();
            try (BufferedReader reader = new BufferedReader(new FileReader("./resources/FoodCustomDatabase.txt"))){
                String line;
                while ((line = reader.readLine()) != null){
                    String[] parts = line.split("\\s+");
                    if (parts.length == 2){
                        String foodName = parts[0].trim().toLowerCase();
                        int protein = Integer.parseInt(parts[1].trim());
                        scoresMap.put(foodName, protein);
                    }
                }
            }
                catch (IOException | NumberFormatException e){
                        System.out.println("Error loading scores from file: " + e.getMessage());
                        }
                return scoresMap;
            }
            private static void saveFoodsToFile(Map<String, Integer> scoresMap){
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources/FoodCustomDatabase.txt"))){
                    for (Map.Entry<String, Integer> entry : scoresMap.entrySet()) {
                writer.write(entry.getKey() + " " + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
                System.out.println("Error saving food item to file: " + e.getMessage());
        }
    }
}
