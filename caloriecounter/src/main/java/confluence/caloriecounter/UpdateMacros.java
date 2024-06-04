/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package confluence.caloriecounter;


import java.util.Scanner;

public class UpdateMacros {
    private CalorieTracker calorieTracker;
    private Scanner scanner;
    private TargetReader targetReader;
    private ExitProgram exit;

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
            if ("x".equalsIgnoreCase(inputs[0])||("n".equalsIgnoreCase(inputs[0]))||("no".equalsIgnoreCase(inputs[0]))){
                    System.out.println("exiting macro tracker...");
                    break;
            } else {
                if (inputs.length == 3) {
                    try {
                        int newCalories = Integer.parseInt(inputs[0]);
                        int newProtein = Integer.parseInt(inputs[1]);
                        int newCarbs = Integer.parseInt(inputs[2]);

                        calorieTracker.updateTargets(newCalories, newProtein, newCarbs);
                        System.out.println("New targets set: Calories = " + newCalories + ", Protein = " + newProtein + "g, Carbs = " + newCarbs + "g");
                        validInput = true; 
                        targetReader.saveMacroTargets(newCalories, newProtein, newCarbs);
                    } catch (NumberFormatException e) {
                        System.out.println("Please make sure the numbers are separated by commas.");
                    }
                } else {
                    System.out.println("Please enter exactly three numbers separated by commas.");
                }
            }     
        }
    } 
}

