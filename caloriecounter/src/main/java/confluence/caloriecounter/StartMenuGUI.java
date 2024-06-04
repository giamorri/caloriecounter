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
import java.util.Scanner;

public class StartMenuGUI {
    private JFrame frame;
    private JPanel panel;
    private JButton foodLoggerButton;
    private JButton updateMacrosButton;
    private JButton motivationalQuoteButton;
    private JButton clearDayButton;
    private JButton daySummaryButton;
    private JButton exitButton;

    private CalorieTracker calorieTracker;
    private AddAndReadFood inputFood;
    private UpdateMacros updateTarget;
    private DaySummary daySummary;
    private EatenTodayIO eatenToday;
    private ExitProgram exit;

    public StartMenuGUI() {
        // Initialize components
        int[] targets = TargetReader.loadMacroTargets();
        calorieTracker = new CalorieTracker(targets[0], targets[1], targets[2]);
        inputFood = new AddAndReadFood(calorieTracker);
        updateTarget = new UpdateMacros(calorieTracker, new Scanner(System.in));
        daySummary = new DaySummary();
        eatenToday = new EatenTodayIO();
        exit = new ExitProgram();

        // Setup frame
        frame = new JFrame("Calorie Counter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Setup panel
        panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        // Food Logger Button
        foodLoggerButton = new JButton("Open Food Logger");
        foodLoggerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputFood.foodReader();
                exit.exitProgram();
            }
        });

        // Update Macros Button
        updateMacrosButton = new JButton("Update Macro Targets");
        updateMacrosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTarget.updateTargets();
                exit.exitProgram();
            }
        });

        // Motivational Quote Button
        motivationalQuoteButton = new JButton("See a Motivational Quote");
        motivationalQuoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "~You must look within yourself to save yourself from your other self~\n~only then will your true self reveal itself~");
                exit.exitProgram();
            }
        });

        // Clear Day Button
        clearDayButton = new JButton("Clear Day");
        clearDayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eatenToday.clearEatenToday();
                exit.exitProgram();
            }
        });

        // Day Summary Button
        daySummaryButton = new JButton("Show Day Summary");
        daySummaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                daySummary.readFoodData("./resources/FoodEatenToday.csv");
                daySummary.displayDaySummary();
                exit.exitProgram();
            }
        });

        // Exit Button
        exitButton = new JButton("Exit Program");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        // Add buttons to panel
        panel.add(foodLoggerButton);
        panel.add(updateMacrosButton);
        panel.add(motivationalQuoteButton);
        panel.add(clearDayButton);
        panel.add(daySummaryButton);
        panel.add(exitButton);

        // Add panel to frame
        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StartMenuGUI();
            }
        });
    }
}
