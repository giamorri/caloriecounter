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
    private AddAndReadFood inputFood;
    private ExitProgram exit = new ExitProgram();
    private UpdateMacros UpdateTarget; 
    private DaySummary daySummary = new DaySummary(); 
    private EatenTodayIO eatenToday = new EatenTodayIO();
    
    public StartMenu() {
        int[] targets = TargetReader.loadMacroTargets();
        this.calorieTracker = new CalorieTracker(targets[0], targets[1], targets[2]); 
        this.inputFood = new AddAndReadFood(calorieTracker);
        this.UpdateTarget = new UpdateMacros(calorieTracker, menuScanner);
        this.eatenToday = new EatenTodayIO();
    }
  
    public static void main(String[] args) {
    
        StartMenu startMenu = new StartMenu();
        startMenu.welcomeText();
        startMenu.displayMenu();
       
    }
    public void welcomeText(){
        System.out.println("Welcome to the Calorie Counter");
        System.out.println("------------------------------");
    }
    public void displayMenu() {
            
            System.out.println("What would you like to do?:");
            menuOptions();
            menuChoice();
    }
    public void menuOptions(){
            System.out.println("- open food logger (fl)");
            System.out.println("- update macro targets (mt)");
            System.out.println("- see a motivational quote (mq)");
            System.out.println("- clear day(cd)");
            System.out.println("- show day summary(ds)");
            System.out.println("- exit the program (x)");
            System.out.println("Enter here:");
    }
   
    
    public void menuChoice(){
        String choice = menuScanner.nextLine();
        
        switch (choice) {
            
            case "fl":
                inputFood.foodReader();
                exit.exitProgram();
                break;
            case "cd":
                eatenToday.clearEatenToday();
                exit.exitProgram();
                break;
                
            case "mt":
                UpdateTarget.updateTargets();
                exit.exitProgram();
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
                System.out.println("Invalid choice. Please enter another item");
                menuChoice();
                break;
        }
    }

}