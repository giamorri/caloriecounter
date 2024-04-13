/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package confluence.caloriecounter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Taj
 */
public class TargetReader {
    private static final String FILE_NAME = "./resources/MacroTargets.csv";  
 
    public static void saveMacroTargets(int calorieTarget, int proteinTarget, int carbsTarget) {
        try (FileWriter fileWriter = new FileWriter(FILE_NAME, false)) {
            fileWriter.append("Calories,Protein,Carbs\n");
            fileWriter.append(String.valueOf(calorieTarget)).append(",");
            fileWriter.append(String.valueOf(proteinTarget)).append(",");
            fileWriter.append(String.valueOf(carbsTarget)).append("\n");
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println("Try Again! check the commas between values are present. ");
        }
    }

    public static int[] loadMacroTargets() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                reader.readLine(); 
                String line = reader.readLine();
                if (line != null) {
                    String[] values = line.split(",");
                    return new int[]{ Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]) };
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Check the formatting and try again ");
            }
        }
        // Defaults if there is issues with the file reading or writing
        //System.out.println("Default is 2000 calories, 150g protein, 300g carbs");
        return new int[]{ 2000, 150, 300 };
    }
}

