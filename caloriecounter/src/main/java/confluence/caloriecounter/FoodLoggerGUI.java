package confluential;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FoodLoggerGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField foodNameField;
    //private JTextField gramsField;
    private JButton addButton;
    private JButton cancelButton;

    private AddAndReadFood addAndReadFood;

    FoodLoggerGUI(AddAndReadFood addAndReadFood) {
        this.addAndReadFood = addAndReadFood;

        setTitle("Food Logger");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        foodNameField = new JTextField(20);
        addButton = new JButton("Add Food");
        cancelButton = new JButton("Cancel");

        add(new JLabel("Food Name:"));
        add(foodNameField);
        add(addButton);
        add(cancelButton);
        
        addButton.addActionListener((ActionEvent e) -> {
            addFoodItem();
        });
        cancelButton.addActionListener((ActionEvent e) -> {
            dispose();
        });
    }

    private void addFoodItem() {
        String foodName = foodNameField.getText().trim();

        if (foodName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the food name.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        addAndReadFood.foodReader(foodName);

        foodNameField.setText("");
    }
}

