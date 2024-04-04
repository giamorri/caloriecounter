
package confluence.caloriecounter;

 class FoodItem {
     
     String name;
     int protein;
     int calories;
     
    public FoodItem (String name, int protein, int calories){
        
        this.name = name;
        this.calories = calories;
        this.protein = protein;
    }
       public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    public int getProtein() {
        return protein;
    }
}
