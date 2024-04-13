/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package confluence.caloriecounter;

/**
 *
 * @author yash
 */
import java.util.Scanner;

public class StartMenu {
    private CalorieTracker calorieTracker;
   private Scanner menuScanner = new Scanner(System.in);
    private CalorieCounter inputFood;
    private ExitProgram exit = new ExitProgram();
  
    
    
    
    
    
    public StartMenu() {
        int[] targets = TargetReader.loadMacroTargets();
        this.calorieTracker = new CalorieTracker(targets[0], targets[1], targets[2]); 
        this.inputFood = new CalorieCounter(); 
        System.out.println("Loaded targets: Calories = " + targets[0] + ", Protein = " + targets[1] + "g, Carbs = " + targets[2] + "g");
    }
  
    public static void main(String[] args) {
    
        StartMenu startMenu = new StartMenu();
        startMenu.displayMenu();
        
    }

    public void displayMenu() {
            
            System.out.println("What would you like to do?:");
            menuOptions();
            menuChoice();
    }
    public void menuOptions(){
            System.out.println("- open food logger (fl)");
            System.out.println("- update macro targets (mt)");
            System.out.println("- see remaining calories (rc)");
            System.out.println("- see a motivational quote (mq)");
            System.out.println("- show day summary(ds)");
            System.out.println("- exit the program (x)");
            System.out.println("Enter here:");
    }
   
    
    public void menuChoice(){
        String choice = menuScanner.nextLine();
        
        switch (choice) {
            case "fl":
                inputFood.foodReader();
                break;
            case "mt":
                    System.out.println("Current Macro Targets: ");
                    calorieTracker.ShowFullMacros();
                    System.out.println("Enter new targets for Calories, Protein (g), Carbs (g) separated by commas (e.g., 1200, 200, 50):");

                    String[] inputs = menuScanner.nextLine().trim().split("\\s*,\\s*"); // to  split the input on commas and trims any whitespace
                    if (inputs.length == 3) { // to make sure only 3 numbers are put in
                try {
                    int newCalories = Integer.parseInt(inputs[0]);
                    int newProtein = Integer.parseInt(inputs[1]);
                    int newCarbs = Integer.parseInt(inputs[2]);
                    
                    calorieTracker.updateTargets(newCalories, newProtein, newCarbs);
                   
                    
                    this.calorieTracker = new CalorieTracker(newCalories, newProtein, newCarbs);
                    System.out.println("New targets set: Calories = " + newCalories + ", Protein = " + newProtein + "g, Carbs = " + newCarbs + "g");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter the numbers properly separated by commas.");
                }
                } else {
                System.out.println("Please enter exactly three numbers separated by commas.");
                }
                    System.out.println("Press any key to return to the main menu...");
                    menuScanner.nextLine(); // This just waits for the user to press any key
                    displayMenu();
                break;
            case "mq":
                System.out.println("~You must look within yourself to save yourself from your other self~");
                System.out.println("          ~only then will your true self reveal itself~");
                break;
            
            case "ds":
                    calorieTracker.ShowFullMacros();
                break;
            case "exit":
            case "x":
                exit.exitProgram(); 
                return; 
            default:
                System.out.println("Invalid choice. Please enter another item:");
                break;
        }
    }
}
