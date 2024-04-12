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
    
        CalorieCounter inputFood = new CalorieCounter();
        DayCounter newDay = new DayCounter();
        //DayCounter today = new DayCounter(); <-- no more day counter
        ExitProgram exit = new ExitProgram();
        CalorieTarget showtarget = new CalorieTarget();
    
        Scanner menuScanner = new Scanner(System.in);
        String choice;

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
        System.out.println("- open food logger (log)");
        System.out.println("- update calorie target (cal)");
        System.out.println("- see a motivational quote (quo)");
        System.out.println("- edit existing meal sets (set)");
        System.out.println("- see todays summary (sum)");
        System.out.println("- Finish day (fin)");
        System.out.println("- exit the program (x)");
        System.out.println("Enter here:");
    }
    public void menuChoice(){
        choice = menuScanner.nextLine();
        
        switch (choice) {
                case "log":
                    newDay.sameDay();
                    //System.out.println("Continuing the day...");
                    inputFood.foodReader();
                    exit.exitProgram();
                    break;
                //lets you change the target
                case "cal":
                    showtarget.CalorieTarget();
                    System.out.println("Would you like to go back to the main menu?");
                    exit.exitProgram();
                    break;
                //gives a motivational quote
                case "quo":
                    System.out.println("~You must look within yourself to save yourself from your other self~");
                    System.out.println("          ~only then will your true self reveal itself~");
                    System.out.println("\nWould you like to go back to the main menu?");
                    exit.exitProgram();
                    break;
                //lets you edit meal sets
                case "set":
                    System.out.println("Would you like to edit your breakfast, lunch or dinner?");
                   //doesnt do anything yet
                    System.out.println("\nWould you like to go back to the main menu?");
                    exit.exitProgram();
                    break;
                case "sum":
                    System.out.println("heres a summary of todays food:");
                //exits the program
                case "exit":
                case "x":
                    break;
                default:
                    System.out.println("Invalid choice. Please enter another item.");
                    menuChoice();
                    break;    
        }
        
        menuScanner.close();
        
    }
}
