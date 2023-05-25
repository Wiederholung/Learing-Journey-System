/**

 The main interface of the learning journey application for an International School.
 It provides navigation to various functions in the project.
 */
package com.metattri.app;
import com.metattri.dao.DB;
import com.metattri.dao.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class WelcomeApp extends JFrame {
    private final String studentID;

    /**
     * Constructs a WelcomeApp object with the given student ID.
     *
     * @param studentID the ID of the student using the application
     */
    public WelcomeApp(String studentID) {
        super("Learning Journey Application for International School");
        this.studentID = studentID;

        createUI();
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    /**
     * Creates the user interface of the WelcomeApp.
     */
    private void createUI() {
        JLabel titleLabel = createTitleLabel();

        JButton studentStatusButton = createButton("Personal<br>Information", e -> {
            new InfoApp(studentID);
            dispose();
        });

        // Create buttons for different functionalities
        JButton studentHonorsButton = createButton("Honors", e -> {
            new HonorApp(studentID);
            dispose();
        });

        JButton studentProjectButton = createButton("Projects", e -> {
            new ProjApp(studentID);
            dispose();
        });

        JButton studentExamResultsButton = createButton("Grades", e -> {
            new GradeApp(studentID);
            dispose();
        });

        JButton learningJourneyButton = createButton("Learning<br>Journey", e -> {
            new LearningJourneyApp(studentID);
            dispose();
        });

        JButton dataExportButton = createButton("Export Data", e -> {
            Student student = DB.getStudent(studentID);
            if (student != null) {
                String path = "src/main/resources/" + student.getName() + ".json";
                DB.writeToJson(DB.exportStudent(student), path);
                JOptionPane.showMessageDialog(null, "Data export successful!");
            }
        });

        JButton studentSkillButton = createButton("Skills", e -> {
            new SkillApp(studentID);
            dispose();
        });

        JButton exitButton = createButton("Exit", e -> {
            new LoginApp();
            dispose();
        });

        JPanel buttonPanel = createButtonPanel(studentExamResultsButton, studentHonorsButton, studentProjectButton, studentSkillButton,
                studentStatusButton, learningJourneyButton, dataExportButton, exitButton);

        JPanel bottomPanel = createBottomPanel();

        Container contentPane = getContentPane();
        contentPane.add(titleLabel, BorderLayout.NORTH);
        contentPane.add(buttonPanel, BorderLayout.CENTER);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Creates the title label for the WelcomeApp.
     *
     * @return the title label component
     */
    private JLabel createTitleLabel() {
        JLabel titleLabel = new JLabel("<html><br><br>Learning Journey Application for International School<br><br><html>");
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        return titleLabel;
    }

    /**
     * Creates a button with the given label and action listener.
     *
     * @param label          the label text of the button
     * @param actionListener the action listener to be attached to the button
     * @return the created button component
     */
    private JButton createButton(String label, ActionListener actionListener) {
        JButton button = new JButton("<html>" + label + "</html>");
        button.setFont(new Font("Helvetica", Font.PLAIN, 16));
        button.setPreferredSize(new Dimension(150, 80));
        button.setHorizontalAlignment(JButton.CENTER);
        button.addActionListener(actionListener);
        return button;
    }

    /**
     * Creates a panel to hold the buttons in a grid layout.
     *
     * @param buttons the buttons to be added to the panel
     * @return the created button panel component
     */
    private JPanel createButtonPanel(JButton... buttons) {
        JPanel buttonPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        for (JButton button : buttons) {
            buttonPanel.add(button);
        }
        return buttonPanel;
    }

    /**
     * Creates a bottom panel with a fixed height.
     *
     * @return the created bottom panel component
     */
    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setVisible(true);
        bottomPanel.setPreferredSize(new Dimension(0, 50));
        return bottomPanel;
    }
}
