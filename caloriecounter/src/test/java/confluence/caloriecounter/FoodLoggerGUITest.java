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
import static org.mockito.Mockito.*;
import javax.swing.*;
import org.mockito.Mockito;
import java.awt.event.ActionEvent;


/**
 *
 * @author Taj
 */
public class FoodLoggerGUITest {
 private AddAndReadFood MockFoodLogger;  
private FoodLoggerGUI foodLoggerGUI; 

    public FoodLoggerGUITest() {
    }
    
    
    
    @BeforeAll
    public static void setUpClass() {
        
    }
    
    
    
    @AfterAll
    public static void tearDownClass() {
        
        
    }
    
    
    
    @BeforeEach
    public void setUp() {
        
    MockFoodLogger = mock(AddAndReadFood.class);
    
    foodLoggerGUI = new FoodLoggerGUI(MockFoodLogger);
    SwingUtilities.invokeLater(() -> foodLoggerGUI.setVisible(true));    
        
    }
    
    
    
    @AfterEach
    public void tearDown() {
        foodLoggerGUI.dispose();
    }

    
    
    /**
     * Test of main method, of class FoodLoggerGUI.
     */
  
  @Test
 public void EmptyFoodAndWeightTest(){
     System.out.println("EmptyFoodAndWeightTest");
    foodLoggerGUI.foodNameField.setText(""); 
    foodLoggerGUI.gramsField.setText(""); 
    foodLoggerGUI.addButton.doClick();
    assertEquals("Please enter both the food name and grams consumed.", JOptionPane.getRootFrame().getTitle());
    }  @Test
  public void WrongWeightInputTest(){
     System.out.println("WrongWeightInputTest");
    foodLoggerGUI.foodNameField.setText("Apple"); 
    foodLoggerGUI.gramsField.setText("qwerty"); 
    foodLoggerGUI.addButton.doClick();
    assertEquals("Please enter a valid number for grams.", JOptionPane.getRootFrame().getTitle());
    }   @Test 
 public void testValidInput() {
        System.out.println("testValidInput");
        foodLoggerGUI.foodNameField.setText("Apple");
        foodLoggerGUI.gramsField.setText("100");
        foodLoggerGUI.addButton.doClick();
        verify(MockFoodLogger, times(1)).foodReader("Apple", 100);
        assertEquals("", foodLoggerGUI.foodNameField.getText());
        assertEquals("", foodLoggerGUI.gramsField.getText());
    }
   

    
}
