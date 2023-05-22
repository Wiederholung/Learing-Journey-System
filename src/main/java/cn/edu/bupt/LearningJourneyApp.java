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
    private final JButton cvGenerationButton;
    private final JButton studyAbroadAssessmentButton;
    private final JButton exitButton;

    public LearningJourneyApp(String studentID) {

        super("Learning Journey Application for International School");
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch(Exception e) {
            System.out.println(e);
        }
        //Set the label
        titleLabel = new JLabel("Learning Journey Application for International School");
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

        cvGenerationButton = new JButton("<html>CV<br>Generation</html>");
        cvGenerationButton.setFont(new Font("Helvetica", Font.PLAIN, 16));
        cvGenerationButton.setPreferredSize(new Dimension(150, 80));
        cvGenerationButton.setHorizontalAlignment(JButton.CENTER);
        cvGenerationButton.addActionListener(e -> {
        });

        studyAbroadAssessmentButton = new JButton("<html>Student<br>Skills</html>");
        studyAbroadAssessmentButton.setFont(new Font("Helvetica", Font.PLAIN, 16));
        studyAbroadAssessmentButton.setPreferredSize(new Dimension(150, 80));
        studyAbroadAssessmentButton.setHorizontalAlignment(JButton.CENTER);
        studyAbroadAssessmentButton.addActionListener(e -> {
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
        buttonPanel.add(cvGenerationButton);
        buttonPanel.add(studyAbroadAssessmentButton);
        buttonPanel.add(exitButton);

        // create the panel and add it into the frame
        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(0, 200));
        Container contentPane = getContentPane();
        contentPane.add(titleLabel, BorderLayout.NORTH);
        contentPane.add(buttonPanel, BorderLayout.CENTER);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);

        // set the size and visibility
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}