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
    private DaySummary daySummary = new DaySummary(); 
  
    
    
    
    
    
    public StartMenu() {
        int[] targets = TargetReader.loadMacroTargets();
        this.calorieTracker = new CalorieTracker(targets[0], targets[1], targets[2]); 
        this.inputFood = new CalorieCounter(calorieTracker); // Initialize with CalorieTracker
        this.UpdateTarget = new UpdateMacros(calorieTracker, menuScanner);
    }
  
    public static void main(String[] args) {
    
        StartMenu startMenu = new StartMenu();
        startMenu.displayMenu();
        
    }
    public void showRemainingMacros() {
    int remainingProtein = calorieTracker.getRemainingProtein();
    int remainingCarbs = calorieTracker.getRemainingCarbs();

    System.out.println("Remaining protein for the day: " + remainingProtein + "g");
    System.out.println("Remaining carbs for the day: " + remainingCarbs + "g");
}

    public void displayMenu() {
            
            System.out.println("What would you like to do?:");
            menuOptions();
            menuChoice();
    }
    public void menuOptions(){
            System.out.println("- open food logger (fl)");
            System.out.println("- update macro targets (mt)");
            System.out.println("- see remaining macros (rc)");
            System.out.println("- see a motivational quote (mq)");
            System.out.println("- show day summary(ds)");
            System.out.println("- exit the program (x)");
            System.out.println("Enter here:");
    }
   
    
    public void menuChoice(){
        String choice = menuScanner.nextLine();
        
        switch (choice) {
            case "rc":
                
                calorieTracker.ShowFullMacros();
                showRemainingMacros();
                exit.exitProgram();
                break;
      
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
                exit.exitProgram();
                break;
            
            case "ds":
                daySummary.readFoodData("./resources/FoodEatenToday.csv");
                    daySummary.displayDaySummary();
                    
                 exit.exitProgram();

                break;
            case "exit":
            case "x":
                
                break;
                 
            default:
                System.out.println("Invalid choice. Please enter another item:");
                break;
        }
    }
}