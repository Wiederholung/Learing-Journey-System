/**
 * Main Interface of the system
 * Can navigate to every function int the project
 */

package cn.edu.bupt;

import javax.swing.*;
import java.awt.*;

public class LearningJourneyApp extends JFrame {
    private final JLabel titleLabel;
    private final JButton studentStatusButton;
    private final JButton studentHonorsButton;
    private final JButton studentProjectButton;
    private final JButton studentExamResultsButton;
    private final JButton learningJourneyButton;
    private final JButton dataExportButton;
    private final JButton studentSkillButton;
    private final JButton exitButton;

    public LearningJourneyApp(String studentID) {

        super("Learning Journey Application for International School");

        //Set the label
        titleLabel = new JLabel("<html><br><br>Learning Journey Application for International School<br><br><html>");
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        /**
         * Create all the Buttons and navigate to the functions
         */
        studentStatusButton = new JButton("<html>Student<br>Personal<br>Information</html>");
        studentStatusButton.setFont(new Font("Helvetica", Font.PLAIN, 16));
        studentStatusButton.setPreferredSize(new Dimension(150, 80));
        studentStatusButton.setHorizontalAlignment(JButton.CENTER);
        studentStatusButton.addActionListener(e -> {
            StudentInfoApp studentInfoApp = new StudentInfoApp(studentID);
            studentInfoApp.setVisible(true);
            dispose();
        });

        studentHonorsButton = new JButton("<html>Student<br>Honors</html>");
        studentHonorsButton.setFont(new Font("Helvetica", Font.PLAIN, 16));
        studentHonorsButton.setPreferredSize(new Dimension(150, 80));
        studentHonorsButton.setHorizontalAlignment(JButton.CENTER);
        studentHonorsButton.addActionListener(e -> {
            StudentHonorApp studentHonorApp = new StudentHonorApp(studentID);
            studentHonorApp.setVisible(true);
            dispose();
        });

        studentProjectButton = new JButton("<html>Student<br>Project</html>");
        studentProjectButton.setFont(new Font("Helvetica", Font.PLAIN, 16));
        studentProjectButton.setPreferredSize(new Dimension(150, 80));
        studentProjectButton.setHorizontalAlignment(JButton.CENTER);
        studentProjectButton.addActionListener(e -> {
            StudentProjectApp studentProject = new StudentProjectApp(studentID);
            studentProject.setVisible(true);
            dispose();
        });

        studentExamResultsButton = new JButton("<html>Student Exam<br>Results</html>");
        studentExamResultsButton.setFont(new Font("Helvetica", Font.PLAIN, 16));
        studentExamResultsButton.setPreferredSize(new Dimension(150, 80));
        studentExamResultsButton.setHorizontalAlignment(JButton.CENTER);
        studentExamResultsButton.addActionListener(e -> {
            StudentScoreApp studentExamResults = new StudentScoreApp(studentID);
            studentExamResults.setVisible(true);
            dispose();
        });

        learningJourneyButton = new JButton("<html>Learning<br>Journey</html>");
        learningJourneyButton.setFont(new Font("Helvetica", Font.PLAIN, 16));
        learningJourneyButton.setPreferredSize(new Dimension(150, 80));
        learningJourneyButton.setHorizontalAlignment(JButton.CENTER);
        learningJourneyButton.addActionListener(e -> {
            LearningJourney learningJourney = new LearningJourney(studentID);
            learningJourney.setVisible(true);
            dispose();
        });

        dataExportButton = new JButton("<html>Export<br>Data</html>");
        dataExportButton.setFont(new Font("Helvetica", Font.PLAIN, 16));
        dataExportButton.setPreferredSize(new Dimension(150, 80));
        dataExportButton.setHorizontalAlignment(JButton.CENTER);
        dataExportButton.addActionListener(e -> {
            ExportInfo exportInfo = new ExportInfo();
            exportInfo.export(studentID);
        });

        studentSkillButton = new JButton("<html>Student<br>Skills</html>");
        studentSkillButton.setFont(new Font("Helvetica", Font.PLAIN, 16));
        studentSkillButton.setPreferredSize(new Dimension(150, 80));
        studentSkillButton.setHorizontalAlignment(JButton.CENTER);
        studentSkillButton.addActionListener(e -> {
            SkillApp skillApp = new SkillApp(studentID);
            skillApp.setVisible(true);
            dispose();
        });

        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Helvetica", Font.PLAIN, 16));
        exitButton.setPreferredSize(new Dimension(150, 80));
        exitButton.setHorizontalAlignment(JButton.CENTER);
        exitButton.addActionListener(e -> {
            JFrame newFrame = new LoginFrame();
            newFrame.pack();
            newFrame.setVisible(true);
            dispose();
        });

        // create the panel and set the layout
        JPanel buttonPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        buttonPanel.add(studentStatusButton);
        buttonPanel.add(studentHonorsButton);
        buttonPanel.add(studentProjectButton);
        buttonPanel.add(studentExamResultsButton);
        buttonPanel.add(learningJourneyButton);
        buttonPanel.add(dataExportButton);
        buttonPanel.add(studentSkillButton);
        buttonPanel.add(exitButton);

         //create the panel and add it into the frame
        JPanel bottomPanel = new JPanel();
        bottomPanel.setVisible(true);
        bottomPanel.setPreferredSize(new Dimension(0, 50));
        Container contentPane = getContentPane();
        contentPane.add(titleLabel, BorderLayout.NORTH);
        contentPane.add(buttonPanel, BorderLayout.CENTER);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);

        // set the size and visibility
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}