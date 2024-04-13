/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package confluence.caloriecounter;

import java.util.Scanner;

/**
 *
 * @author giamorri
 */
public class UpdateMacros {
    private CalorieTracker calorieTracker;
    private Scanner scanner;

    public UpdateMacros(CalorieTracker calorieTracker, Scanner scanner) {
        this.calorieTracker = calorieTracker;
        this.scanner = scanner;
    }

        public void updateTargets() {
        boolean validInput = false;

        while (!validInput) {
            System.out.println("Current Macro Targets: ");
            calorieTracker.ShowFullMacros();
            System.out.println("Enter new targets for Calories, Protein (g), Carbs (g) separated by commas (e.g., 1200, 200, 50):");

            String[] inputs = scanner.nextLine().trim().split("\\s*,\\s*");
            if (inputs.length == 3) {
                try {
                    int newCalories = Integer.parseInt(inputs[0]);
                    int newProtein = Integer.parseInt(inputs[1]);
                    int newCarbs = Integer.parseInt(inputs[2]);
                    
                    calorieTracker.updateTargets(newCalories, newProtein, newCarbs);
                    System.out.println("New targets set: Calories = " + newCalories + ", Protein = " + newProtein + "g, Carbs = " + newCarbs + "g");
                    validInput = true; 
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter the numbers properly separated by commas.");
                }
            } else {
                System.out.println("Please enter exactly three numbers separated by commas.");
            }
        }

        System.out.println("Press any key to return to the main menu...");
        scanner.nextLine(); 
    }
}

