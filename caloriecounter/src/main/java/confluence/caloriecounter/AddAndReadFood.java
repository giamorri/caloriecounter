package confluence.caloriecounter;

import javax.swing.*;

public class AddAndReadFood {

    private DatabaseIO dataIO = new DatabaseIO();
    private EatenTodayManager eatenTodayManager = new EatenTodayManager();
    private CalorieTracker calorieTracker;

    public AddAndReadFood(CalorieTracker tracker) {
        this.calorieTracker = tracker;
    }

    public void addFood(String foodName, int calories, int protein, int carbs) {
        // Update CalorieTracker with macros of eaten food
        this.calorieTracker.addCalories(calories);
        this.calorieTracker.addProtein(protein);
        this.calorieTracker.addCarbs(carbs);
        JOptionPane.showMessageDialog(null, "Added " + foodName + " with " + calories + " calories, " + protein + "g of protein, " + carbs + "g of carbs.", "Food Added", JOptionPane.INFORMATION_MESSAGE);
    }

    public void foodReader(String foodName) {
        boolean foundInDay = eatenTodayManager.searchInDay(foodName);
        if (!foundInDay) {
            dataIO.searchInDatabase(foodName);
        }
    }
}
