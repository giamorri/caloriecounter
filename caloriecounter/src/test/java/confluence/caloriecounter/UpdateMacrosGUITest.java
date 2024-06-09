/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package confluence.caloriecounter;
import javax.swing.*;

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
public class UpdateMacrosGUITest {
    
  UpdateMacrosGUI updateMacrosGUI;
  CalorieTracker calorieTracker;
    
    
    
    public UpdateMacrosGUITest() {
    }
    
    @BeforeAll
    public static void setUpClass() throws Exception {
    
     
    }
    
    @AfterAll
    public static void tearDownClass() {

    }
    //initialise calorieTracker and the GUI for updateMacros before the test
    @BeforeEach
    public void setUp() throws Exception {
   calorieTracker = new CalorieTracker(2500, 150, 100); 
     SwingUtilities.invokeAndWait(() -> {
         updateMacrosGUI = new UpdateMacrosGUI(calorieTracker);
         updateMacrosGUI.setVisible(false); // to stop the GUI from launching
   });
    }
    
    @AfterEach //just gets rid of UpdatemacrosGUI class after the test
    public void tearDown()throws Exception{
        
        
        SwingUtilities.invokeAndWait(() -> {
            updateMacrosGUI.dispose();
        });
        
    }@Test 
    public void testInitialise(){
        
  JTextField caloriesField = (JTextField) updateMacrosGUI.getContentPane().getComponent(1);
        JTextField proteinField = (JTextField) updateMacrosGUI.getContentPane().getComponent(3);
        JTextField carbsField = (JTextField) updateMacrosGUI.getContentPane().getComponent(5);
        
        assertEquals("2500", caloriesField.getText());
        assertEquals("150", proteinField.getText());
        assertEquals("100", carbsField.getText());
    }@Test
  public void testInput() throws Exception{
  SwingUtilities.invokeAndWait(() -> {
      JTextField caloriesField = (JTextField) updateMacrosGUI.getContentPane().getComponent(1);
      JTextField proteinField = (JTextField) updateMacrosGUI.getContentPane().getComponent(3);
      JTextField carbsField = (JTextField) updateMacrosGUI.getContentPane().getComponent(5);
      JButton updateButton = (JButton) updateMacrosGUI.getContentPane().getComponent(7);
      caloriesField.setText("3000");
      proteinField.setText("200");
      carbsField.setText("150");
      updateButton.doClick();
  });  
assertEquals(3000, calorieTracker.getCalorieTarget());  
assertEquals(200, calorieTracker.getRemainingProtein());  
assertEquals(150, calorieTracker.getRemainingCarbs());  

  
  } 
    /**
     * Test of main method, of class UpdateMacrosGUI.
     */

    
    
}
