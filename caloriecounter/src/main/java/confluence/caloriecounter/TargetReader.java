/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package confluence.caloriecounter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TargetReader {
    public static void saveMacroTargets(int calorieTarget, int proteinTarget, int carbsTarget) {
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("UPDATE MacroTargets SET target_calories = ?, target_protein = ?, target_carbs = ?")) {
            pstmt.setInt(1, calorieTarget);
            pstmt.setInt(2, proteinTarget);
            pstmt.setInt(3, carbsTarget);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Macro targets saved successfully.");
            } else {
                System.out.println("Failed to save macro targets.");
            }
        } catch (SQLException e) {
            System.out.println("Error saving macro targets: " + e.getMessage());
        }
    }

    public static int[] loadMacroTargets() {
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM MacroTargets")) {
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int calorieTarget = rs.getInt("target_calories");
                    int proteinTarget = rs.getInt("target_protein");
                    int carbsTarget = rs.getInt("target_carbs");
                    return new int[]{calorieTarget, proteinTarget, carbsTarget};
                }
            }
        } catch (SQLException e) {
            System.out.println("Error loading macro targets: " + e.getMessage());
        }

        return new int[]{2000, 150, 300}; // Default values
    }
}


