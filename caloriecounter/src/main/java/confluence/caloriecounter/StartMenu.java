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
    public static void main(String[] args) {
        StartMenu startMenu = new StartMenu();
        startMenu.displayMenu();
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        String choice;

        do {
            System.out.println("Hello, today is day of Macro logging");
            System.out.println("What would you like to do?:");
            System.out.println("- start a new day (nd)");
            System.out.println("- continue day (cd)");
            System.out.println("- update calorie target (ct)");
            System.out.println("- see a motivational quote (mq)");
            System.out.println("- edit existing meal sets (eg)");
            System.out.println("Enter here:");
            choice = scanner.nextLine();

            switch (choice) {
                case "nd":
                    System.out.println("Starting a new day...");
                    break;
                case "cd":
                    System.out.println("Continuing the day...");
                    break;
                case "ct":
                    System.out.println("Updating calorie target...");
                    break;
                case "mq":
                    System.out.println("Displaying a motivational quote...");
                    break;
                case "eg":
                    System.out.println("Editing existing meal sets...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (!choice.equals("exit"));

        scanner.close();
    }
}
