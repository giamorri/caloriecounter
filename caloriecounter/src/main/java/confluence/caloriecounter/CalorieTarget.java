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

public class CalorieTarget {
    public void CalorieTarget(){
    
    int target = 2000;
    String changecal;
    
    Scanner calories = new Scanner(System.in);
    Scanner change = new Scanner(System.in);
    
    
    
        System.out.println("You target is "+target+".");
        System.out.println("Would you like to change this? (y/n)");
        changecal = change.nextLine();
        if ("y".equalsIgnoreCase(changecal)) {
            System.out.println("What is your new target?:");
            target = calories.nextInt();
            System.out.println("your new target is "+target+".");
        }
        else if ("x".equalsIgnoreCase(changecal)) {
            System.out.println("target is still "+target+".");
        }
        else {
            System.out.println("please enter a valid choice");
            CalorieTarget();
        }
    }
}
