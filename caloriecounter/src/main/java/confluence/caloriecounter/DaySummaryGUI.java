/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package confluence.caloriecounter;

/**
 *
 * @author giamorri
 */
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class DaySummaryGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextArea summaryArea;
    private JLabel wellDoneLabel;
    private Map<String, Map<String, Double>> foodItems;
    private Map<String, Double> totalMacros;

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

        // Panel for summary and message
        JPanel summaryPanel = new JPanel();
        summaryPanel.setLayout(new BorderLayout(10, 10));
        summaryPanel.add(wellDoneLabel, BorderLayout.NORTH);
        summaryPanel.add(new JScrollPane(summaryArea), BorderLayout.CENTER);

        add(summaryPanel, BorderLayout.CENTER);

        // Read and display the food data
        readFoodData("./resources/FoodEatenToday.csv");
        displayDaySummary();
    }
public Map<String, Map<String, Double>> getFoodItems() {
    return foodItems;
}

public Map<String, Double> getTotalMacros() {
    return totalMacros;
}
    // Reads data from FoodEatenToday.csv
    public void readFoodData(String filePath) {
        File file = new File(filePath);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processLine(line);
            }
        } catch (IOException | NumberFormatException e) {
            summaryArea.append("No food added yet!\n");
        }
    }

    // Processes each line of the CSV and updates the food items and total macros
    private void processLine(String line) {
        String[] parts = line.split(",");

        String foodName = parts[0];
        double calories = Double.parseDouble(parts[1]);
        double protein = Double.parseDouble(parts[2]);
        double carbs = Double.parseDouble(parts[3]);

        Map<String, Double> macros = new HashMap<>();
        macros.put("Calories", calories);
        macros.put("Protein", protein);
        macros.put("Carbs", carbs);
        foodItems.put(foodName, macros);

        totalMacros.put("Calories", totalMacros.get("Calories") + calories);
        totalMacros.put("Protein", totalMacros.get("Protein") + protein);
        totalMacros.put("Carbs", totalMacros.get("Carbs") + carbs);
    }

    // Displays the day summary in the JTextArea
    public void displayDaySummary() {
        for (Map.Entry<String, Map<String, Double>> entry : foodItems.entrySet()) {
            summaryArea.append(entry.getKey() + ", Calories: " + entry.getValue().get("Calories") + ", Protein: " + entry.getValue().get("Protein") + ", Carbs: " + entry.getValue().get("Carbs") + "\n");
        }
        summaryArea.append("\nTotal: Calories: " + totalMacros.get("Calories") + ", Protein: " + totalMacros.get("Protein") + ", Carbs: " + totalMacros.get("Carbs") + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DaySummaryGUI().setVisible(true);
        });
    }
}
