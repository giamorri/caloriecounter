package confluence.caloriecounter;

public class CalorieTracker {
    private int calorieTarget;
    private int consumedCalories = 0;
    private int consumedProtein = 0;
    private int consumedCarbs = 0;
    private int proteinTarget;
    private int carbsTarget;

    public CalorieTracker(int calorieTarget, int proteinTarget, int carbsTarget) {
        // Direct initialization from parameters
        this.calorieTarget = calorieTarget;
        this.proteinTarget = proteinTarget;
        this.carbsTarget = carbsTarget;
    }

    public int getCalorieTarget() {
        return calorieTarget;
    }

    public void addCalories(double calories) {
        consumedCalories += calories;
        System.out.println(calories + " calories added. Total eaten: " + consumedCalories);
    }

    public void addProtein(double protein) {
        consumedProtein += protein;
        System.out.println(protein + "g protein added. Total consumed: " + consumedProtein);
    }

    public void addCarbs(double carbs) {
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
        showFullMacros();
    }

    public void showFullMacros() {
        System.out.println("Calorie intake: " + calorieTarget + " kcal");
        System.out.println("Protein intake: " + proteinTarget + " g");
        System.out.println("Carbs intake: " + carbsTarget + " g");
    }
}
