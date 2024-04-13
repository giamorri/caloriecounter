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
    private UpdateMacros UpdateTarget; 
  
    
    
    
    
    
    public StartMenu() {
        int[] targets = TargetReader.loadMacroTargets();
        this.calorieTracker = new CalorieTracker(targets[0], targets[1], targets[2]); 
        this.inputFood = new CalorieCounter(); 
        this.UpdateTarget = new UpdateMacros(calorieTracker, menuScanner);
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
                    UpdateTarget.updateTargets(); 
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
