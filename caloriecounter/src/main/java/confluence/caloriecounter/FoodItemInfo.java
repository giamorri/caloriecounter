/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package confluential;

 class FoodItemInfo {
     
     double carbs;
     double protein;
     double calories;
     
    public FoodItemInfo (double carbs, double protein, double calories){
        
        
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
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
