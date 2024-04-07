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
    
        Scanner menuScanner = new Scanner(System.in);
        Scanner foodScanner = new Scanner(System.in);
        String choice;

        //FoodDatabase foodDatabase = new FoodDatabase();
        Caloriecounter inputFood = new Caloriecounter();
        DayCounter newDay = new DayCounter();
        
    public static void main(String[] args) {
        StartMenu startMenu = new StartMenu();
        startMenu.displayMenu();
        
        
    }

    public void displayMenu() {
            System.out.println("Hello, today is day of Macro logging");
            System.out.println("What would you like to do?:");
            System.out.println("- start a new day (nd)");
            System.out.println("- continue day (cd)");
            System.out.println("- update calorie target (ct)");
            System.out.println("- see a motivational quote (mq)");
            System.out.println("- edit existing meal sets (ms)");
            System.out.println(" exit the program (x)");
            System.out.println("Enter here:");
            choice = menuScanner.nextLine();
            
            switch (choice) {
                case "nd":
                    newDay.newDay();
                    //System.out.println("You are entering food for day #.");
                    inputFood.FoodReader();
                    //need to run Calorie counter or macroDatabase(); from here but idk how
                    System.out.println("What did you have today?:");
                    foodScanner.nextLine();
                    //FoodItemInfo eggMacros = foodDatabase.getFoodItemInfo("Egg");
                    //System.out.println("Egg");
                    break;
                case "cd":
                    newDay.sameDay();
                    //System.out.println("Continuing the day...");
                    inputFood.FoodReader();
                    break;
                case "ct":
                    System.out.println("Your current target is #, what you like your new target to be?");
                    break;
                case "mq":
                    System.out.println("You must look within yourself to save yourself from your other self,");
                    System.out.println("only then will your true self reveal itself.");
                    break;
                case "ms":
                    System.out.println("Would you like to edit your breakfast, lunch or dinner?");
                    break;
                case "exit":
                case "x":
                    choice = "exit";
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
            }

//        menuScanner.close();
//        foodScanner.close();
    }
