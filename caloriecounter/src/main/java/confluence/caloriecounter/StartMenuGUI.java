package confluence.caloriecounter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenuGUI extends JFrame {

    private JButton foodLoggerButton;
    private JButton updateMacrosButton;
    private JButton motivationalQuoteButton;
    private JButton clearDayButton;
    private JButton daySummaryButton;
    private JButton exitButton;

    private CalorieTracker calorieTracker;
    private AddAndReadFood inputFood;
  
    private EatenTodayIO eatenToday;
    private ExitProgram exit;

    public StartMenuGUI() {
        // Initialize the frame
        setTitle("Calorie Counter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1));

        int[] targets = TargetReader.loadMacroTargets();
        calorieTracker = new CalorieTracker(targets[0], targets[1], targets[2]);
        inputFood = new AddAndReadFood(calorieTracker);
        eatenToday = new EatenTodayIO();
        exit = new ExitProgram();

        
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
        foodLoggerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
openFoodLogger();
            }
        });

        updateMacrosButton.addActionListener((ActionEvent e) -> {
            updateMacros();
        });
// need to add more quotes
        motivationalQuoteButton.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(StartMenuGUI.this, "~You must look within yourself to save yourself from your other self~\n~only then will your true self reveal itself~");
            
        });
        clearDayButton.addActionListener((ActionEvent e) -> {
            eatenToday.clearEatenToday();
            exit.exitProgram();
            
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
       
    }
    

    private void showDaySummary() {
        new DaySummaryGUI().setVisible(true);
    }
    
    private void updateMacros(){
        
        new UpdateMacrosGUI(calorieTracker).setVisible(true);
    }
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StartMenuGUI startMenuGUI = new StartMenuGUI(); // start everything
        });
    }
}
