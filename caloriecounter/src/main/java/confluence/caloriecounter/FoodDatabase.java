package confluence.caloriecounter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class FoodDatabase {

    // Method to get food item information from the database
    public FoodItemInfo getFoodItemInfo(String foodName) {
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT protein, carbs, calories FROM FoodDatabase WHERE name = ?")) {
            pstmt.setString(1, foodName);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    double protein = rs.getDouble("protein");
                    double carbs = rs.getDouble("carbs");
                    double calories = rs.getDouble("calories");
                    return new FoodItemInfo(protein, carbs, calories);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving food item: " + e.getMessage());
        }
        return null;
    }

    // Method to add a food item to the database
    public void addFoodItem(String foodName, double protein, double carbs, double calories) {
        if (Objects.nonNull(foodName) && !foodName.trim().isEmpty() && protein >= 0 && carbs >= 0 && calories >= 0) {
            try (Connection conn = DatabaseManager.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement("INSERT INTO FoodDatabase (name, protein, carbs, calories) VALUES (?, ?, ?, ?)")) {
                pstmt.setString(1, foodName);
                pstmt.setDouble(2, protein);
                pstmt.setDouble(3, carbs);
                pstmt.setDouble(4, calories);
                pstmt.executeUpdate();
                System.out.println("Food item added successfully.");
            } catch (SQLException e) {
                System.out.println("Error adding food item: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid input. Please check the values and try again.");
        }
    }
}
