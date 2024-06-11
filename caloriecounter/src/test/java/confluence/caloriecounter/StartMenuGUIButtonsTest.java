/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package confluence.caloriecounter;

import org.junit.jupiter.api.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import static org.junit.jupiter.api.Assertions.*;



/**
 *
 * @author Taj
 */
public class StartMenuGUIButtonsTest {
    
        public StartMenuGUI startMenuGUI;

    
    public StartMenuGUIButtonsTest() {
    }
//setup is to initialise the startmenu before a test to stop it interfering
    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }
//rids of startmenu after test
    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    SwingUtilities.invokeAndWait(() -> {
            startMenuGUI = new StartMenuGUI();
            startMenuGUI.setVisible(false); 
        });
}
    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
SwingUtilities.invokeAndWait(() -> {
            startMenuGUI.dispose();
        });
}
  /**
     * Test of main method, of class StartMenuGUI.
     */
    @org.junit.jupiter.api.Test
    //to make sure main works with no issue
    public void testMain() {
        String[] args = null;
        StartMenuGUI.main(args);
        
        assertNotNull(startMenuGUI);
    }

    @org.junit.jupiter.api.Test
    
public void testInitialise(){
    //tests to make sure the buttons are initialised properly
    
    assertEquals("Calorie Counter", startMenuGUI.getTitle());
    assertNotNull(ButtonText("Open Food Logger"));
    assertNotNull(ButtonText("Update Macro Targets"));
    assertNotNull(ButtonText("See a Motivational Quote"));
    assertNotNull(ButtonText("See a Motivational Quote"));
    assertNotNull(ButtonText("Show Day Summary"));
    assertNotNull(ButtonText("Exit Program"));



}
    //this is to find buttons with tet (makes life easier)
private JButton ButtonText(String text) {
        for (Component component : startMenuGUI.getContentPane().getComponents()) {
            if (component instanceof JButton) {
            JButton button = (JButton) component;
            if (button.getText().equals(text)) {
            return button;
    }
    }
    }
        return null;
    } 
//all the below methods check if a button is there and is clickable
public void FoodLoggerTest(){
    
    JButton button = ButtonText("Open Food Logger");
    assertNotNull(button);
        button.doClick();
}   
public void UpdateMacrosTest() {
        JButton button = ButtonText("Update Macro Targets");
        assertNotNull(button);
        button.doClick();   
}      
        
    public void MQTest() {
        JButton button = ButtonText("See a Motivational Quote");
        assertNotNull(button);
        button.doClick();  
    }
 public void ClearDayTest() {
        JButton button = ButtonText("Clear Day");
        assertNotNull(button);
        button.doClick();    
    
 }
 
  public void DaySummaryTest() {
        JButton button = ButtonText("Show Day Summary");
        assertNotNull(button);
        button.doClick();    
    
 }
  
   
  public void testExitButton() {
        JButton button = ButtonText("Exit Program");
        assertNotNull(button);
        button.doClick();    
    
 }
  
    
    
    
    
    
    
    
}
    
    

  
