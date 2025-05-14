package Design;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main_GUI extends JFrame {

    private CardLayout cardLayout;
    private JPanel cardPanel;

    public Main_GUI() {
    
        setTitle("Altas Fitness - Daily Fitness Partner");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       
        ImageIcon icon = new ImageIcon(Login_GUI.class.getResource("/Design/images/Calling_Card.png"));
        setIconImage(icon.getImage());

       
        getContentPane().setBackground(Color.BLACK);
        setLayout(new BorderLayout());

       
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

    
        JPanel activityPanel = ActivityPanel();
        JPanel workoutPanel = WorkoutPanel();
        JPanel nutritionPanel = NutritionPanel();
        JPanel goalPanel = GoalPanel();

        cardPanel.add(activityPanel, "Activity Log");
        cardPanel.add(workoutPanel, "Workout Log");
        cardPanel.add(nutritionPanel, "Nutrition Dairy");
        cardPanel.add(goalPanel, "Goal Log");

     
        add(cardPanel, BorderLayout.CENTER);

      
        JButton ActivityCheck = new JButton("Activity Calculator");
        JButton WorkoutLog = new JButton("Workout Regiment");
        JButton NutritionLog = new JButton("Nutrition Log");
        JButton GoalLog = new JButton("Goal");

        ActivityCheck.setBounds(12,50,150,50);
        WorkoutLog.setBounds(12,100,150,50);
        NutritionLog.setBounds(12,220,150,50);
        GoalLog.setBounds(12,450,150,50);

        ActivityCheck.setBackground(new Color(0, 78, 126));
        WorkoutLog.setBackground(new Color(0, 78, 126));
        NutritionLog.setBackground(new Color(0, 78, 126));
        GoalLog.setBackground(new Color(0, 78, 126));

        ActivityCheck.setForeground(Color.BLACK);
        WorkoutLog.setForeground(Color.BLACK);
        NutritionLog.setForeground(Color.BLACK);
        GoalLog.setForeground(Color.BLACK);

     
        ActivityCheck.setFocusPainted(false);
        WorkoutLog.setFocusPainted(false);
        NutritionLog.setFocusPainted(false);
        GoalLog.setFocusPainted(false);

        Font buttonFont = new Font("Segoe UI", Font.BOLD, 12);
        ActivityCheck.setFont(buttonFont);
        WorkoutLog.setFont(buttonFont);
        NutritionLog.setFont(buttonFont);
        GoalLog.setFont(buttonFont);

    
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());

    
        buttonPanel.add(ActivityCheck);
        buttonPanel.add(Box.createVerticalStrut(20)); // Spacing between buttons
        buttonPanel.add(WorkoutLog);
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(NutritionLog);
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(GoalLog);

      
        buttonPanel.setPreferredSize(new Dimension(180, 0));

 
        add(buttonPanel, BorderLayout.WEST);

        setVisible(true);

      
        ActivityCheck.addActionListener(e -> cardLayout.show(cardPanel, "Activity Log"));
        WorkoutLog.addActionListener(e -> cardLayout.show(cardPanel, "Workout Log"));
        NutritionLog.addActionListener(e -> cardLayout.show(cardPanel, "Nutrition Dairy"));
        GoalLog.addActionListener(e -> cardLayout.show(cardPanel, "Goal Log"));
    }

    private JPanel ActivityPanel() {
        JPanel activity = new JPanel();
        activity.setBackground(new Color(6, 7, 20));
        activity.setLayout(null);

        JLabel Title = new JLabel("Atlas Fitness - Activity ");
        Font titleFont = new Font("SansSerif", Font.BOLD, 32);
        Title.setFont(titleFont);
        Title.setForeground(new Color(255, 215, 0));
        Title.setBounds(155, 20, 400, 50); 
        activity.add(Title);  


        JLabel act = new JLabel("Select Activity Type: ");
        JLabel actTime = new JLabel("Duration (in minutes): ");
        String[] options = { "Running", "Walking", "Cycling", "Swimming" };
        JTextField duration = new JTextField(10); 
        JComboBox<String> comboBox = new JComboBox<>(options);


        act.setBounds(155, 100, 150, 30);
        actTime.setBounds(155, 150, 150, 30);
        comboBox.setBounds(400, 100, 150, 30);
        duration.setBounds(400, 150, 150, 30);

        act.setForeground(new Color(255, 215, 0));
        actTime.setForeground(new Color(255, 215, 0));

        activity.add(act);
        activity.add(comboBox);
        activity.add(actTime);
        activity.add(duration);

        JButton button = new JButton("Calculate");
        button.setBounds(300, 200, 120, 40); 
        activity.add(button);

        JTextArea resultArea = new JTextArea(10, 20);
        resultArea.setEditable(false);
        resultArea.setBackground(new Color(6, 7, 20));
        resultArea.setForeground(new Color(255, 215, 0));
        resultArea.setMargin(new Insets(20, 20, 20, 20));
        resultArea.setFont(new Font("Arial", Font.PLAIN, 14));
        resultArea.setText("Calories burned will be displayed here.");

       
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(155, 250, 400, 100); 
        activity.add(scrollPane); 


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) comboBox.getSelectedItem();
                double choice;
                if (selectedOption.equals("Running")) {
                    choice = 2.5;
                } else if (selectedOption.equals("Walking")) {
                    choice = 3.0;
                } else if (selectedOption.equals("Cycling")) {
                    choice = 4.0;
                } else {
                    choice = 3.5;
                }

                try {
                    double selected = Double.parseDouble(duration.getText());
                    double burned = choice * selected;
                    resultArea.setText("Calories burned: " + burned+". Data saved successfully!!");
                    saveToFile(selectedOption, selected, burned);
                } catch (NumberFormatException ex) {
                    resultArea.setText("Invalid input. Please enter a valid number for duration.");
                }
            }
        });

        return activity;
    }

    private void saveToFile(String activityType, double duration, double burnedCalories) {
            File file = new File("data.txt");

            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write("Activity: " + activityType + ", Duration: " + duration + " minutes, Calories burned: " + burnedCalories);
                writer.newLine();
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }
        }




    private JPanel WorkoutPanel() {
        JPanel workout = new JPanel();
        workout.setBackground(new Color(6, 7, 20));
        workout.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel Title = new JLabel("Atlas Fitness - Workout ");
        Font titleFont = new Font("SansSerif", Font.BOLD, 32);
        Title.setFont(titleFont);
        Title.setForeground(new Color(255, 215, 0));

        workout.add(Title);

        return workout;
    }

    private JPanel NutritionPanel() {
        JPanel nutrition = new JPanel();
        nutrition.setBackground(new Color(6, 7, 20));
        nutrition.setLayout(null);

        JLabel Title = new JLabel("Atlas Fitness - Nutrition Dairy ");
        Font titleFont = new Font("SansSerif", Font.BOLD, 32);
        Title.setFont(titleFont);
        Title.setForeground(new Color(255, 215, 0));
        Title.setBounds(150, 20, 500, 50); 
        nutrition.add(Title);  
        

        JLabel nutri_amount = new JLabel("Ammount of Food (CUP): ");
        JLabel nutri_type = new JLabel("Select the Food: ");
        JTextField amount = new JTextField(10);
        String[] options = { "Rice", "Milk", "Meat", "Vegetables" };
        JComboBox<String> comboBox = new JComboBox<>(options);

        
        
        nutri_amount.setBounds(155, 100, 150, 30);
        nutri_type.setBounds(155, 150, 150, 30);
        comboBox.setBounds(400, 100, 150, 30);
        amount.setBounds(400, 150, 150, 30);

        
        nutri_type.setForeground(new Color(255, 215, 0));
        nutri_amount.setForeground(new Color(255, 215, 0));

        nutrition.add(nutri_type);
        nutrition.add(comboBox);
        nutrition.add(nutri_amount);
        nutrition.add(amount);

        JButton button = new JButton("Calculate");
        button.setBounds(300, 200, 120, 40);
        nutrition.add(button);

        
        JTextArea resultArea = new JTextArea(10, 20);
        resultArea.setEditable(false);
        resultArea.setBackground(new Color(6, 7, 20));
        resultArea.setForeground(new Color(255, 215, 0));
        resultArea.setMargin(new Insets(20, 20, 20, 20));
        resultArea.setFont(new Font("Arial", Font.PLAIN, 14));
        resultArea.setText("Intake Calories will be displayed here.");

     
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(155, 250, 400, 100);
        nutrition.add(scrollPane);

 
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) comboBox.getSelectedItem();
                double choice;
                if (selectedOption.equals("Rice")) {
                    choice = 220;
                } else if (selectedOption.equals("Milk")) {
                    choice = 146;
                } else if (selectedOption.equals("Meat")) {
                    choice = 410;
                } else {
                    choice = 110;
                }

                try {
                    double selected = Double.parseDouble(amount.getText());
                    double intake = choice * selected;
                    resultArea.setText("Calories took: " +intake+". Data saved successfully!!");
                    saveToFile(selectedOption, selected, intake);
                } catch (NumberFormatException ex) {
                    resultArea.setText("Invalid input. Please enter a valid number for amount.");
                }
            }
        });

        return nutrition;
    }

    private JPanel GoalPanel() {
        JPanel goals = new JPanel();
        goals.setBackground(new Color(6, 7, 20));
        goals.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel Title = new JLabel("Atlas Fitness - Goals ");
        Font titleFont = new Font("SansSerif", Font.BOLD, 32);
        Title.setFont(titleFont);
        Title.setForeground(new Color(255, 215, 0));

        goals.add(Title);

        return goals;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main_GUI());
    }
}
