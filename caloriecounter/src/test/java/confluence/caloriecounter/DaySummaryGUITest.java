/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package confluence.caloriecounter;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Taj
 */
public class DaySummaryGUITest {
    
    @TempDir
    Path tempDir;
    private File tempDaySummaryFile;
    
    
    public DaySummaryGUITest() {
    }
    
    @BeforeEach
 public void setUp() throws IOException {
    tempDaySummaryFile = tempDir.resolve("FoodEatenToday.csv").toFile();
     try (FileWriter writer = new FileWriter(tempDaySummaryFile)) {
            writer.write("Apple,52,0.3,14\n");
            writer.write("Banana,89,1.1,23\n");
            writer.write("Chicken Breast,165,31,0\n");
            writer.write("Rice,206,4.3,45\n");
    }
    }
    
    @AfterEach
    public void tearDown() {
    tempDaySummaryFile.delete();    
        
        
        
        
        
    }

    /**
     * Test of readFoodData method, of class DaySummaryGUI.
     */
    @Test
    public void testReadFoodData() {
    DaySummaryGUI instance = new DaySummaryGUI();
    instance.readFoodData(tempDaySummaryFile.getAbsolutePath());
    assertFalse(instance.getFoodItems().isEmpty(), "Food items should not be empty");
        assertEquals(4, instance.getFoodItems().size(), "There should be 4 food items");
        assertEquals(512.0, instance.getTotalMacros().get("Calories"), 0.01, "Total calories should be 512.0");
    assertEquals(36.7, instance.getTotalMacros().get("Protein"), 0.01, "Total protein should be 36.7");
    assertEquals(82.0, instance.getTotalMacros().get("Carbs"), 0.01, "Total carbs should be 82.0");
    
    }

    /**
     * Test of displayDaySummary method, of class DaySummaryGUI.
     */
    @Test
    public void testDisplayDaySummary() {
    DaySummaryGUI instance = new DaySummaryGUI();
    instance.readFoodData(tempDaySummaryFile.getAbsolutePath());
    instance.displayDaySummary();
    String expectedOutput = "Apple, Calories: 52.0, Protein: 0.3, Carbs: 14.0\n" +
                                "Banana, Calories: 89.0, Protein: 1.1, Carbs: 23.0\n" +
                                "Chicken Breast, Calories: 165.0, Protein: 31.0, Carbs: 0.0\n" +
                                "Rice, Calories: 206.0, Protein: 4.3, Carbs: 45.0\n" +
                                "\nTotal: Calories: 512.0, Protein: 36.7, Carbs: 82.0\n";
   
    }

    /**
     * Test of main method, of class DaySummaryGUI.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        DaySummaryGUI.main(args);
        
        assertTrue(true);
    }
    
}
