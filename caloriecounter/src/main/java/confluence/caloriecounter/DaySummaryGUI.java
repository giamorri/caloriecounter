/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package confluence.caloriecounter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public final class DaySummaryGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextArea summaryArea;
    private JLabel wellDoneLabel;
    private Map<String, Map<String, Double>> foodItems;
    private Map<String, Double> totalMacros;
    private JButton cancelButton;

    public DaySummaryGUI() {
        foodItems = new HashMap<>();
        totalMacros = new HashMap<>();
        totalMacros.put("Calories", 0.0);
        totalMacros.put("Protein", 0.0);
        totalMacros.put("Carbs", 0.0);

        
        // Initialize the frame
        setTitle("Day Summary");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Initialize components
        summaryArea = new JTextArea();
        summaryArea.setEditable(false);
        summaryArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        summaryArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        wellDoneLabel = new JLabel("Well Done!", SwingConstants.CENTER);
        wellDoneLabel.setFont(new Font("Serif", Font.BOLD, 24));
        wellDoneLabel.setForeground(Color.GREEN);
        wellDoneLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener((ActionEvent e) -> dispose());

        // Panel for summary and message
        JPanel summaryPanel = new JPanel(new BorderLayout(10, 10));
        summaryPanel.add(wellDoneLabel, BorderLayout.NORTH);
        summaryPanel.add(new JScrollPane(summaryArea), BorderLayout.CENTER);

        // Panel for the cancel button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(cancelButton);

        // Add panels to frame
        add(summaryPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Read and display the food data
        readFoodData();
        displayDaySummary();
    }

    public void readFoodData() {
        try (Connection conn = DatabaseManager.getConnection(); 
             Statement stmt = conn.createStatement()) {
            String query = "SELECT f.name, f.calories, f.protein, f.carbs FROM FoodEatenToday e JOIN FoodDatabase f ON e.food_id = f.id";
            try (ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    String foodName = rs.getString("name");
                    double calories = rs.getDouble("calories");
                    double protein = rs.getDouble("protein");
                    double carbs = rs.getDouble("carbs");
                    processLine(foodName, calories, protein, carbs);
                }
            }
        } catch (SQLException e) {
            summaryArea.append("Error reading from database: " + e.getMessage() + "\n");
        }
    }

    private void processLine(String foodName, double calories, double protein, double carbs) {
        Map<String, Double> macros = new HashMap<>();
        macros.put("Calories", calories);
        macros.put("Protein", protein);
        macros.put("Carbs", carbs);
        foodItems.put(foodName, macros);

        totalMacros.put("Calories", totalMacros.get("Calories") + calories);
        totalMacros.put("Protein", totalMacros.get("Protein") + protein);
        totalMacros.put("Carbs", totalMacros.get("Carbs") + carbs);
    }

    public void displayDaySummary() {
        StringBuilder summary = new StringBuilder();
        for (Map.Entry<String, Map<String, Double>> entry : foodItems.entrySet()) {
            summary.append(entry.getKey())
                   .append(", Calories: ").append(entry.getValue().get("Calories"))
                   .append(", Protein: ").append(entry.getValue().get("Protein"))
                   .append(", Carbs: ").append(entry.getValue().get("Carbs"))
                   .append("\n");
        }
        summary.append("Total: Calories: ").append(totalMacros.get("Calories"))
               .append(", Protein: ").append(totalMacros.get("Protein"))
               .append(", Carbs: ").append(totalMacros.get("Carbs"))
               .append("\n");

        summaryArea.setText(summary.toString());
    }


}
