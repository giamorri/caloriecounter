/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package confluence.caloriecounter;

/**
 *
 * @author Taj
 */
public class DayCounter {
    int currentDay = 0;
    
    public void newDay(){
        currentDay = currentDay+1;
        System.out.println("Starting a New Day, Today is day "+currentDay+".");
    }
    public void sameDay(){
        System.out.println("Continuing Day, Today is day "+currentDay+".");
    }
}
