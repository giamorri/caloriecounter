package confluence.caloriecounter;

import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class EatenTodayManager {

    public boolean searchInDay(String foodName) {
        String searchQuery = "SELECT fet.quantity, fd.protein, fd.carbs, fd.calories " +
                             "FROM FoodEatenToday fet " +
                             "JOIN FoodDatabase fd ON fet.food_id = fd.id " +
                             "WHERE LOWER(fd.name) = LOWER(?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(searchQuery)) {
            pstmt.setString(1, foodName.trim().toLowerCase());
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    double quantity = rs.getDouble("quantity");
                    double proteinPer100g = rs.getDouble("protein");
                    double carbsPer100g = rs.getDouble("carbs");
                    double caloriesPer100g = rs.getDouble("calories");

                    double protein = (proteinPer100g * quantity) / 100;
                    double carbs = (carbsPer100g * quantity) / 100;
                    double calories = (caloriesPer100g * quantity) / 100;

                    showItemExistsDialog(foodName, protein, carbs, calories);
                    return true;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error searching in day: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public void saveToDay(String name, double protein, double carbs, double calories) {
        String findFoodQuery = "SELECT id FROM FoodDatabase WHERE LOWER(name) = LOWER(?)";
        String insertFoodQuery = "INSERT INTO FoodEatenToday (food_id, quantity) VALUES (?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement findFoodStmt = conn.prepareStatement(findFoodQuery);
             PreparedStatement insertStmt = conn.prepareStatement(insertFoodQuery)) {

            String formattedName = name.trim().toLowerCase();
            findFoodStmt.setString(1, formattedName);

            try (ResultSet rs = findFoodStmt.executeQuery()) {
                if (rs.next()) {
                    int foodId = rs.getInt("id");

                    insertStmt.setInt(1, foodId);
                    insertStmt.setDouble(2, 100); //its per 100g so we set that as the default

                    int rowsAffected = insertStmt.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Food item added successfully.\nAdded " + name + ": \n- " + formatOutput(name, protein, carbs, calories), "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to add food item.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Food item wasn't found in the database.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error saving to day: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void clearEatenToday() {
        String deleteQuery = "DELETE FROM FoodEatenToday";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Food log cleared successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No records to clear.", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error clearing eaten today: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showItemExistsDialog(String foodName, double protein, double carbs, double calories) {
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
                double updatedProtein = (protein / 100) * grams;
                double updatedCarbs = (carbs / 100) * grams;
                double updatedCalories = (calories / 100) * grams;
                JOptionPane.showMessageDialog(null, formatOutput(foodName, updatedProtein, updatedCarbs, updatedCalories), "Updated Macros", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number for grams.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private String formatOutput(String foodName, double protein, double carbs, double calories) {
        return foodName + "\n- protein: " + protein + "g\n- carbs: " + carbs + "g\n- calories: " + calories + "kcal";
    }
}
