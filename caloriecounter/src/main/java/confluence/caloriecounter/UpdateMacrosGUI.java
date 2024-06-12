package confluence.caloriecounter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateMacrosGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    
    private CalorieTracker calorieTracker;
    private JTextField caloriesField;
    private JTextField proteinField;
    private JTextField carbsField;
    private JButton updateButton;
    private JButton cancelButton;
    
    public UpdateMacrosGUI(CalorieTracker calorieTracker) {
        this.calorieTracker = calorieTracker;
        
        // Initialize the frame
        setTitle("Update Macro Targets");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));
        
        // Initialize components
        JLabel caloriesLabel = new JLabel("Calories:");
        caloriesField = new JTextField(String.valueOf(calorieTracker.getCalorieTarget()));
        JLabel proteinLabel = new JLabel("Protein (g):");
        proteinField = new JTextField(String.valueOf(calorieTracker.getRemainingProtein()));
        JLabel carbsLabel = new JLabel("Carbs (g):");
        carbsField = new JTextField(String.valueOf(calorieTracker.getRemainingCarbs()));
        updateButton = new JButton("Update");
        cancelButton = new JButton("Cancel");
        
        // added buttons to the frame
        add(caloriesLabel);
        add(caloriesField);
        add(proteinLabel);
        add(proteinField);
        add(carbsLabel);
        add(carbsField);
        add(updateButton);
        add(cancelButton);

        // Add action listeners to the buttons
        updateButton.addActionListener((ActionEvent e) -> {
            updateTargets();
        });
        cancelButton.addActionListener((ActionEvent e) -> {
            dispose();
        });
        setVisible(true);
    }
    
    private void updateTargets() {
        try {
            int newCalories = Integer.parseInt(caloriesField.getText());
            int newProtein = Integer.parseInt(proteinField.getText());
            int newCarbs = Integer.parseInt(carbsField.getText());

            // Update the database with the new targets
            try (Connection conn = DatabaseManager.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement("UPDATE MacroTargets SET target_calories = ?, target_protein = ?, target_carbs = ?")) {
                pstmt.setInt(1, newCalories);
                pstmt.setInt(2, newProtein);
                pstmt.setInt(3, newCarbs);
                pstmt.executeUpdate();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error updating targets: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Update the local calorie tracker
            calorieTracker.updateTargets(newCalories, newProtein, newCarbs);
            JOptionPane.showMessageDialog(this, "New targets set: Calories = " + newCalories + ", Protein = " + newProtein + "g, Carbs = " + newCarbs + "g", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
