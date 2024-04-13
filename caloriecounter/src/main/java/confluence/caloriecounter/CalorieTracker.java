package confluence.caloriecounter;

import java.util.Scanner;

public class CalorieTracker {
    private int calorieTarget; 
    private int consumedCalories = 0;
    private int consumedProtein = 0;
    private int consumedCarbs = 0;
    private int proteinTarget;
    private int carbsTarget;

    public CalorieTracker(int calorieTarget, int proteinTarget, int carbTarget) {
         int[] targets = TargetReader.loadMacroTargets();
        this.calorieTarget = targets[0];
        this.proteinTarget = targets[1];
        this.carbsTarget = targets[2];
        this.consumedCalories = 0;
        this.proteinTarget = proteinTarget;
        this.carbsTarget = carbsTarget;
    }

    public int getCalorieTarget() {
        return calorieTarget;
    }
    public void addCalories(int calories) {
        consumedCalories += calories;
         System.out.println(calories + " calories added. Total consumed: " + consumedCalories);
 
    }
    public void addProtein(int protein) {
        consumedProtein += protein;
        System.out.println(protein + "g protein added. Total consumed: " + consumedProtein);
    }

    public void addCarbs(int carbs) {
        consumedCarbs += carbs;
        System.out.println(carbs + "g carbs added. Total consumed: " + consumedCarbs);
    }
    public int getRemainingCalories() {
        return calorieTarget - consumedCalories;
    }
    public int getRemainingProtein() {
    return proteinTarget - consumedProtein;
}

public int getRemainingCarbs() {
    return carbsTarget - consumedCarbs;
}
    public void resetCalories() {
        consumedCalories = 0;
    }

    public void setCalorieTarget(int calorieTarget) {
        this.calorieTarget = calorieTarget;
        
    }

    public void updateTargets(int calories, int protein, int carbs) {
    this.calorieTarget = calories;
    this.proteinTarget = protein;
    this.carbsTarget = carbs;
    TargetReader.saveMacroTargets(calorieTarget, proteinTarget, carbsTarget);
    ShowFullMacros();
}
    
    public void ShowFullMacros() {
        System.out.println("Calorie intake: " + consumedCalories + "/" + calorieTarget + " kcal");
        System.out.println("Protein intake: " + consumedProtein + "/" + proteinTarget + " g");
        System.out.println("Carbs intake: " + consumedCarbs + "/" + carbsTarget + " g");
    }
    
}

