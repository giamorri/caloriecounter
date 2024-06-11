package confluential;

import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class DatabaseIO {

    private EatenTodayManager day = new EatenTodayManager();

    public void searchInDatabase(String searchTerm) {
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM FoodDatabase WHERE LOWER(name) LIKE LOWER(?)")) {
            pstmt.setString(1, "%" + searchTerm + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String foodName = rs.getString("name");
                    double proteinPer100g = rs.getDouble("protein");
                    double carbsPer100g = rs.getDouble("carbs");
                    double caloriesPer100g = rs.getDouble("calories");

                    showFoodFoundDialog(foodName, proteinPer100g, carbsPer100g, caloriesPer100g);
                } else {
                    int response = JOptionPane.showConfirmDialog(null, searchTerm + " not found in the database. Would you like to add it?", "Food Not Found", JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.YES_OPTION) {
                        addNewFoodItem(searchTerm);
                        searchInDatabase(searchTerm);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error searching in database: " + e.getMessage());
        }
    }

    private void showFoodFoundDialog(String foodName, double proteinPer100g, double carbsPer100g, double caloriesPer100g) {
        JTextField gramsField = new JTextField();
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Food Name:"));
        panel.add(new JLabel(foodName));
        panel.add(new JLabel("Protein (/100g):"));
        panel.add(new JLabel(String.valueOf(proteinPer100g)));
        panel.add(new JLabel("Carbs (/100g):"));
        panel.add(new JLabel(String.valueOf(carbsPer100g)));
        panel.add(new JLabel("Calories (/100g):"));
        panel.add(new JLabel(String.valueOf(caloriesPer100g)));
        panel.add(new JLabel("Grams Eaten:"));
        panel.add(gramsField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Food Found", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                double gramsEaten = Double.parseDouble(gramsField.getText().trim());
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
                day.saveToDay(foodName, protein, carbs, calories); // Save to day with proper params
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number for grams.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void addNewFoodItem(String foodName) {
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
                double protein = Double.parseDouble(proteinField.getText().trim());
                double carbs = Double.parseDouble(carbsField.getText().trim());
                double calories = Double.parseDouble(caloriesField.getText().trim());
                addFoodToDatabase(foodName, protein, carbs, calories);

                StringBuilder message = new StringBuilder();
                message.append("Added ").append(foodName).append(" to the database with macros:\n")
                        .append(formatOutput(foodName, protein, carbs, calories));

                JOptionPane.showMessageDialog(null, message.toString(), "Food Added", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter valid numbers for the macros.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void addFoodToDatabase(String foodName, double protein, double carbs, double calories) {
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO FoodDatabase (name, protein, carbs, calories) VALUES (?, ?, ?, ?)")) {
            pstmt.setString(1, foodName.trim().toLowerCase());
            pstmt.setDouble(2, protein);
            pstmt.setDouble(3, carbs);
            pstmt.setDouble(4, calories);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Food item added successfully.");
            } else {
                System.out.println("Failed to add food item.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding food item: " + e.getMessage());
        }
    }

    private String formatOutput(String foodName, double protein, double carbs, double calories) {
        return foodName + "\n- Protein: " + protein + "g\n- Carbs: " + carbs + "g\n- Calories: " + calories + "kcal";
    }
}
