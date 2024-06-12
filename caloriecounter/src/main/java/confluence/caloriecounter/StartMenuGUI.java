package confluence.caloriecounter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenuGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private JButton foodLoggerButton;
    private JButton updateMacrosButton;
    private JButton motivationalQuoteButton;
    private JButton clearDayButton;
    private JButton daySummaryButton;
    private JButton exitButton;

    private CalorieTracker calorieTracker;
    private AddAndReadFood inputFood;
    private EatenTodayManager eatenToday;
    
    
    public static void main(String[] args) {
        
            DatabaseManager databaseManager = new DatabaseManager();
            StartMenuGUI startMenuGUI = new StartMenuGUI();
            
        // start everything
        
    }

    public StartMenuGUI() {
        // Initialize the frame
        setTitle("Calorie Counter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1));

        int[] targets = TargetReader.loadMacroTargets();
        calorieTracker = new CalorieTracker(targets[0], targets[1], targets[2]);
        inputFood = new AddAndReadFood(calorieTracker);
        eatenToday = new EatenTodayManager();
        

        
        foodLoggerButton = new JButton("Open Food Logger");
        updateMacrosButton = new JButton("Update Macro Targets");
        motivationalQuoteButton = new JButton("See a Motivational Quote");
        clearDayButton = new JButton("Clear Day");
        daySummaryButton = new JButton("Show Day Summary");
        exitButton = new JButton("Exit Program");

        // this is for adding the buttons to the frame , feel free to change the colours etc im using defaults rn
        add(foodLoggerButton);
        add(updateMacrosButton);
        add(motivationalQuoteButton);
        add(clearDayButton);
        add(daySummaryButton);
        add(exitButton);
        

        // Add action listener to the buttons, might be useful combining it in one and calling it can you try that yash
        foodLoggerButton.addActionListener((ActionEvent e) -> {
            openFoodLogger();
        });

        updateMacrosButton.addActionListener((ActionEvent e) -> {
            updateMacros();
        });
        // need to add more quotes -> added heaps of quotes in the MotivationalQuotes class
        motivationalQuoteButton.addActionListener((ActionEvent e) -> {
        String randomQuote = MotivationalQuote.getRandomQuote();
        JOptionPane.showMessageDialog(StartMenuGUI.this, randomQuote);
        });

        clearDayButton.addActionListener((ActionEvent e) -> {
            eatenToday.clearEatenToday();
            
            
        });
        daySummaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDaySummary();
            }
            private void showDaySummary() {
        new DaySummaryGUI().setVisible(true);
    }
        });
        exitButton.addActionListener((ActionEvent e) -> {
            dispose();
        });
        setVisible(true);
    }
    
    //i had to initialise the different options here, please see if theres a simpler way to implement
    private void openFoodLogger() {
       AddAndReadFood addAndReadFood = new AddAndReadFood(new CalorieTracker(2000,50,150));
       new FoodLoggerGUI(addAndReadFood).setVisible(true);
    }
    


    
    private void updateMacros(){
        
        
        new UpdateMacrosGUI(calorieTracker).setVisible(true);
    }    
}
