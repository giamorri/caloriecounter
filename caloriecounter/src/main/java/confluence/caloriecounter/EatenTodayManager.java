/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package confluence.caloriecounter;

/**
 *
 * @author giamorri
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Taj
 */
import java.awt.GridLayout;
import java.io.*;
import javax.swing.*;

public class EatenTodayManager {
    private static final String FILE_PATH = "./resources/FoodEatenToday.csv";

    public boolean searchInDay(String foodItem) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(foodItem)) {
                    showItemExistsDialog(line);
                    return true;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading food log.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public void saveToDay(String foodItem) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(foodItem);
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Try Again!!", "Make sure you are using letters", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void clearEatenToday() {
        try (FileWriter fileWriter = new FileWriter(FILE_PATH, false)) {
            fileWriter.write("");
            JOptionPane.showMessageDialog(null, "Food log cleared successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Try Again!!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showItemExistsDialog(String line) {
        String[] parts = line.split(",");
        if (parts.length >= 4) {
            String foodName = parts[0];
            double protein = Double.parseDouble(parts[1]);
            double carbs = Double.parseDouble(parts[2]);
            double calories = Double.parseDouble(parts[3]);

            JPanel panel = new JPanel(new GridLayout(5, 1));
            panel.add(new JLabel("Item already added today:"));
            panel.add(new JLabel(formatOutput(foodName, protein, carbs, calories)));
            JTextField gramsField = new JTextField();
            panel.add(new JLabel("Update grams consumed:"));
            panel.add(gramsField);

            int result = JOptionPane.showConfirmDialog(null, panel, "Update Food Item", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    double grams = Double.parseDouble(gramsField.getText());
                    // Update logic here, like saving new amount, if required
                    // For now, just showing the updated values
                    double updatedProtein = (protein / 100) * grams;
                    double updatedCarbs = (carbs / 100) * grams;
                    double updatedCalories = (calories / 100) * grams;
                    JOptionPane.showMessageDialog(null, formatOutput(foodName, updatedProtein, updatedCarbs, updatedCalories), "Updated Macros", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number for grams.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private String formatOutput(String foodName, double protein, double carbs, double calories) {
        return foodName + "\n- protein: " + protein + "g\n- carbs: " + carbs + "g\n- calories: " + calories + "kcal";
    }
}

