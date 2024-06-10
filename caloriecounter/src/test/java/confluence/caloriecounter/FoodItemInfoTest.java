/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package confluence.caloriecounter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Taj
 */
public class FoodItemInfoTest {
    @Test
void CaloriesTest(){
FoodItemInfo foodItem = new FoodItemInfo(50.0, 20.0, 300.0);
assertEquals(300.0, foodItem.getCalories(), 0.001);
    
}  
@Test
void ProteinTest(){
FoodItemInfo foodItem = new FoodItemInfo(50.0, 20.0, 300.0);
assertEquals(20.0, foodItem.getProtein(), 0.001);
    
}   @Test 
void CarbsTest(){
FoodItemInfo foodItem = new FoodItemInfo(50.0, 20.0, 300.0);
assertEquals(50.0, foodItem.getCarbs(), 0.001);
    
}    
    
    
    
    
}
