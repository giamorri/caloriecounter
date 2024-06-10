/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package confluence.caloriecounter;

/**
 *
 * @author yash
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FoodLoggerGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    
    private JTextField foodNameField;
    private JTextField gramsField;
    private JButton addButton;
    
    private AddAndReadFood addAndReadFood;
    
    FoodLoggerGUI(AddAndReadFood addAndReadFood){
        this.addAndReadFood = addAndReadFood;
        
        setTitle("Food Logger");
        setSize(400,200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        
        foodNameField = new JTextField(20);
        gramsField = new JTextField(10);
        addButton = new JButton("Add Food");
        
        add(new JLabel("Food Name"));
        add(foodNameField);
        add(new JLabel("Grams:"));
        add(gramsField);
        add(addButton);
        
        addButton.addActionListener((ActionEvent e) -> {
            addFoodItem();
        });
                
    }
    
    private void addFoodItem(){
        String foodName = foodNameField.getText().trim();
        String gramsText = gramsField.getText().trim();
        
        if (foodName.isEmpty() || gramsText.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please enter both the food name and grams consumed.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        double grams;
        try{
            grams = Double.parseDouble(gramsText);
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Please enter a valid number for grams.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        addAndReadFood.foodReader(foodName, grams);
        
        foodNameField.setText("");
        gramsField.setText("");
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AddAndReadFood addAndReadFood1 = new AddAndReadFood(new CalorieTracker(2000, 50, 150));
            new FoodLoggerGUI(addAndReadFood1).setVisible(true);
        });
    }
}

