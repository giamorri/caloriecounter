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
    
    
    //need to read existing txt or csv file before running program, if(there is no txt file) then(make a new txt file)
       // try{
        //todo
        //check for exisiting file
        //IF file exisits
        //DO
        ///open file with exisiting calories
        ///read contents of files and store as objects in new runtime
        //if file does not exist goto catch
      
       // }catch{
        //put this in a try catch
        //no log file exists, start a new instance
        //}
        //FoodDatabase foodDatabase = new FoodDatabase();
        CalorieCounter inputFood = new CalorieCounter();
        DayCounter newDay = new DayCounter();
        DayCounter today = new DayCounter();
        ExitProgram exit = new ExitProgram();
        CalorieTarget showtarget = new CalorieTarget();
        EndDay end = new EndDay();
    
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
            menuOptions();
            menuChoice();
    }
    public void menuOptions(){
            System.out.println("- open food logger (fl)");
            //System.out.println("input meal (im)");
            //System.out.println("- start a new day (nd)");
            System.out.println("- update calorie target (ct)");
            System.out.println("- see a motivational quote (mq)");
            System.out.println("- edit existing meal sets (ms)");
            System.out.println("- Finish day (fd)");
            System.out.println("- exit the program (x)");
            System.out.println("Enter here:");
    }
    public void menuChoice(){
        choice = menuScanner.nextLine();
        
        switch (choice) {
//                case "im":
//                    newDay.sameDay();
//                    //System.out.println("Continuing the day...");
//                    inputFood.foodReader();
//                    System.out.println("\nWould you like to go back to the main menu?");
//                    exit.exitProgram();
//                    break;
//                //add meas
//                case "nd":
//                    newDay.sameDay();
//                    //System.out.println("Continuing the day...");
//                    inputFood.foodReader();
//                    System.out.println("\nWould you like to go back to the main menu?");
//                    exit.exitProgram();
//                    break;
                case "fl":
                    newDay.sameDay();
                    //System.out.println("Continuing the day...");
                    inputFood.foodReader();
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
                   //doesnt do anything yet
                    System.out.println("\nWould you like to go back to the main menu?");
                    exit.exitProgram();
                    break;
                case "fd":
                    System.out.println("Do you wish to end the day and view the foods from today? (y/n)");
                    end.endDay();
                    break;
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
        //foodScanner.close();
    }
}
