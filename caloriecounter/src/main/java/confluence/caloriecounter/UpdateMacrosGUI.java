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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class UpdateMacrosGUI extends JFrame {
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
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTargets();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }
    private void updateTargets() {
        try {
    int newCalories = Integer.parseInt(caloriesField.getText());
    int newProtein = Integer.parseInt(proteinField.getText());
    int newCarbs = Integer.parseInt(carbsField.getText());
        calorieTracker.updateTargets(newCalories, newProtein, newCarbs);
        JOptionPane.showMessageDialog(this, "New targets set: Calories = " + newCalories + ", Protein = " + newProtein + "g, Carbs = " + newCarbs + "g", "Success", JOptionPane.INFORMATION_MESSAGE);
        dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalorieTracker calorieTracker = new CalorieTracker(2000, 50, 150); //added defaults, please check if implementation works yash <3
            new UpdateMacrosGUI(calorieTracker);
        });
    }
}

