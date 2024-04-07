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

public class ExitProgram {
    public void exitProgram(){
        
        Scanner rando = new Scanner(System.in);
        Scanner exitScanner = new Scanner(System.in);
        String enter;
        String cont;
        StartMenu menu = new StartMenu();
        
        
        cont = rando.nextLine();
        while(true){
                if ("x".equalsIgnoreCase(cont)||("exit".equalsIgnoreCase(cont))||("no".equalsIgnoreCase(cont))){
                    System.out.println("Goodbye!");
                    break;
                }
                else {
                System.out.println("\nenter \"x\" or \"exit\" at any time to quit the program");
                System.out.println("press enter to continue");
                enter = exitScanner.nextLine();
            
                if ("x".equalsIgnoreCase(enter)||("exit".equalsIgnoreCase(enter))||("no".equalsIgnoreCase(enter))){
                    System.out.println("Goodbye!");
                    break;
                }
                else {
                    menu.displayMenu();
                }
            }
        }
    }
}
