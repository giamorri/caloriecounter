/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package confluence.caloriecounter;

 class FoodItemInfo {
     
     double carbs;
     double protein;
     double calories;
     
    public FoodItemInfo (double name, double protein, double calories){
        
        
        this.calories = calories;
        this.protein = protein;
    }

    public double  getCalories() {
        return calories;
    }

    public double getProtein() {
        return protein;
    }
    
    public double getCarbs(){
        return carbs;
    }
}
