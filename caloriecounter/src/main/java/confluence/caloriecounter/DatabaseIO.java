package confluence.caloriecounter;

import java.awt.GridLayout;
import java.io.*;
import javax.swing.*;

public class DatabaseIO {

    private static final String FILE_PATH = "./resources/FoodDatabase.csv";
    private EatenTodayIO day = new EatenTodayIO();

    public void searchInDatabase(String searchTerm, double gramsEaten, FoodDatabase foodDatabase) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                if (line.contains(searchTerm)) {
                    String[] parts = line.split(",");
                    if (parts.length >= 4) {
                        String foodName = parts[0];
                        double proteinPer100g = Double.parseDouble(parts[1]);
                        double carbsPer100g = Double.parseDouble(parts[2]);
                        double caloriesPer100g = Double.parseDouble(parts[3]);

                        showFoodFoundDialog(foodName, proteinPer100g, carbsPer100g, caloriesPer100g, gramsEaten);
                        found = true;
                        break;
                    }
                }
            }

            if (!found) {
                int response = JOptionPane.showConfirmDialog(null, searchTerm + " not found in the database. Would you like to add it?", "Food Not Found", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    addNewFoodItem(searchTerm, foodDatabase);
                }
            }
        } catch (IOException e) {
        }
    }

    private void showFoodFoundDialog(String foodName, double proteinPer100g, double carbsPer100g, double caloriesPer100g, double gramsEaten) {
        double protein = (proteinPer100g * gramsEaten) / 100;
        double carbs = (carbsPer100g * gramsEaten) / 100;
        double calories = (caloriesPer100g * gramsEaten) / 100;

        StringBuilder message = new StringBuilder();
        message.append("Found ").append(foodName).append(" in database:\n")
               .append(formatOutput(foodName, proteinPer100g, carbsPer100g, caloriesPer100g))
               .append("\n\nChanged macros based on ").append(gramsEaten).append(" grams eaten:\n")
               .append("Protein: ").append(protein).append("g\n")
               .append("Carbs: ").append(carbs).append("g\n")
               .append("Calories: ").append(calories).append("kcal");

        JOptionPane.showMessageDialog(null, message.toString(), "Food Found", JOptionPane.INFORMATION_MESSAGE);
        day.saveToDay(foodName + "," + protein + "," + carbs + "," + calories);
    }

    public static void saveToDatabase(String foodName, double protein, double carbs, double calories) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(foodName + "," + protein + "," + carbs + "," + calories);
            writer.newLine();
        } catch (IOException e) {
        }
    }

    private void addNewFoodItem(String foodName, FoodDatabase foodDatabase) {
        JTextField proteinField = new JTextField();
        JTextField carbsField = new JTextField();
        JTextField caloriesField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Protein (/100g):"));
        panel.add(proteinField);
        panel.add(new JLabel("Carbs (/100g):"));
        panel.add(carbsField);
        panel.add(new JLabel("Calories (/100g):"));
        panel.add(caloriesField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Enter Macros for " + foodName, JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                double protein = Double.parseDouble(proteinField.getText());
                double carbs = Double.parseDouble(carbsField.getText());
                double calories = Double.parseDouble(caloriesField.getText());
                foodDatabase.addFoodItem(foodName, protein, carbs, calories);

                StringBuilder message = new StringBuilder();
                message.append("Added ").append(foodName).append(" to the database with macros:\n")
                       .append(formatOutput(foodName, protein, carbs, calories));

                JOptionPane.showMessageDialog(null, message.toString(), "Food Added", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter valid numbers for the macros.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private String formatOutput(String foodName, double protein, double carbs, double calories) {
        return foodName + "\n- Protein: " + protein + "g\n- Carbs: " + carbs + "g\n- Calories: " + calories + "kcal";
    }
}
