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
    
        //FoodDatabase foodDatabase = new FoodDatabase();
        CalorieCounter inputFood = new CalorieCounter();
        DayCounter newDay = new DayCounter();
        DayCounter today = new DayCounter();
        ExitProgram exit = new ExitProgram();
        CalorieTarget showtarget = new CalorieTarget();
        Meal sets = new Meal();
        
        
    
        Scanner menuScanner = new Scanner(System.in);
        //Scanner foodScanner = new Scanner(System.in);
        //Scanner continueOrExit = new Scanner(System.in);
        String choice;

        
        
    public static void main(String[] args) {
        
        
        
        StartMenu startMenu = new StartMenu();
        startMenu.displayMenu();
        
    }

    public void displayMenu() {
            System.out.println("Hello, today is day "+today.currentDay+" of Macro logging");
            System.out.println("What would you like to do?:");
            System.out.println("- start a new day (nd)");
            System.out.println("- continue day (cd)");
            System.out.println("- update calorie target (ct)");
            System.out.println("- see a motivational quote (mq)");
            System.out.println("- edit existing meal sets (ms)");
            System.out.println("- exit the program (x)");
            System.out.println("Enter here:");
            choice = menuScanner.nextLine();
            
            switch (choice) {
                //starts a new day
                case "nd":
                    newDay.newDay();
                    //System.out.println("You are entering food for day #.");
                    inputFood.FoodReader();
                    //need to run Calorie counter or macroDatabase(); from here but idk how
                    //System.out.println("What did you have today?:");
                    //foodScanner.nextLine();
                    //FoodItemInfo eggMacros = foodDatabase.getFoodItemInfo("Egg");
                    //System.out.println("Egg");
                    
                    exit.exitProgram();
                    break;
                //continues the same day
                case "cd":
                    newDay.sameDay();
                    //System.out.println("Continuing the day...");
                    inputFood.FoodReader();
                    System.out.println("\nWould you like to go back to the main menu?");
                    exit.exitProgram();
                    break;
                //lets you change the target
                case "ct":
                    showtarget.CalorieTarget();
                    System.out.println("Would you like to go back to the main menu?");
                    exit.exitProgram();
                    break;
                //gives a motivational quote
                case "mq":
                    System.out.println("~You must look within yourself to save yourself from your other self~");
                    System.out.println("          ~only then will your true self reveal itself~");
                    System.out.println("\nWould you like to go back to the main menu?");
                    exit.exitProgram();
                    break;
                //lets you edit meal sets
                case "ms":
                    System.out.println("Would you like to edit your breakfast, lunch or dinner?");
                   
                    System.out.println("\nWould you like to go back to the main menu?");
                    exit.exitProgram();
                    break;
                //exits the program
                case "exit":
                case "x":
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    
                    break;
            }
    }

//        menuScanner.close();
//        foodScanner.close();
    }
