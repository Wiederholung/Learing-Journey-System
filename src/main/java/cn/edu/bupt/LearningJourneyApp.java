/**
 * Main Interface of the system
 * Can navigate to every function in the project
 */

package cn.edu.bupt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LearningJourneyApp extends JFrame {
    private final String studentID;

    public LearningJourneyApp(String studentID) {
        super("Learning Journey Application for International School");
        this.studentID = studentID;

        createUI();
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private void createUI() {
        JLabel titleLabel = createTitleLabel();

        JButton studentStatusButton = createButton("Personal<br>Information", e -> {
            new StudentInfoApp(studentID);
            dispose();
        });
        JButton studentHonorsButton = createButton("Honors", e -> {
            new StudentHonorApp(studentID);
            dispose();
        });
        JButton studentProjectButton = createButton("Projects", e -> {
            new StudentProjectApp(studentID);
            dispose();
        });
        JButton studentExamResultsButton = createButton("Grades", e -> {
            new StudentScoreApp(studentID);
            dispose();
        });
        JButton learningJourneyButton = createButton("Learning<br>Journey", e -> {
            new LearningJourney(studentID);
            dispose();
        });
        JButton dataExportButton = createButton("Export Data", e -> {
            ExportInfo exportInfo = new ExportInfo();
            exportInfo.export(studentID);
        });
        JButton studentSkillButton = createButton("Skills", e -> {
            new SkillApp(studentID);
            dispose();
        });
        JButton exitButton = createButton("Exit", e -> {
            new LoginFrame();
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

    private JLabel createTitleLabel() {
        JLabel titleLabel = new JLabel("<html><br><br>Learning Journey Application for International School<br><br><html>");
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        return titleLabel;
    }

    private JButton createButton(String label, ActionListener actionListener) {
        JButton button = new JButton("<html>" + label + "</html>");
        button.setFont(new Font("Helvetica", Font.PLAIN, 16));
        button.setPreferredSize(new Dimension(150, 80));
        button.setHorizontalAlignment(JButton.CENTER);
        button.addActionListener(actionListener);
        return button;
    }

    private JPanel createButtonPanel(JButton... buttons) {
        JPanel buttonPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        for (JButton button : buttons) {
            buttonPanel.add(button);
        }
        return buttonPanel;
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setVisible(true);
        bottomPanel.setPreferredSize(new Dimension(0, 50));
        return bottomPanel;
    }

}
