package cn.edu.bupt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LearningJourneyApp extends JFrame {
    private JLabel titleLabel;
    private JButton studentStatusButton, studentHonorsButton, studentProjectButton, studentExamResultsButton,
            learningJourneyButton, cvGenerationButton, studyAbroadAssessmentButton, exitButton;
    private String studentID;

    public LearningJourneyApp(String studentID) {

        super("Learning Journey Application for International School");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.studentID=studentID;
        // 创建标题标签，设置字体和尺寸，居中对齐
        titleLabel = new JLabel("Learning Journey Application for International School");
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        // 创建按钮，设置字体和尺寸，居中对齐
        studentStatusButton = new JButton("<html>Student status<br>data</html>");
        studentStatusButton.setFont(new Font("Helvetica", Font.PLAIN, 16));
        studentStatusButton.setPreferredSize(new Dimension(150, 80));
        studentStatusButton.setHorizontalAlignment(JButton.CENTER);
        studentStatusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 跳转到 StudentStatusData 页面
//                StudentStatusData studentStatusData = new StudentStatusData();
//                studentStatusData.setVisible(true);
//                dispose();
                StudentInfoApp studentInfoApp=new StudentInfoApp(studentID);
                studentInfoApp.setVisible(true);
                dispose();
            }
        });

        studentHonorsButton = new JButton("<html>Student<br>Honors</html>");
        studentHonorsButton.setFont(new Font("Helvetica", Font.PLAIN, 16));
        studentHonorsButton.setPreferredSize(new Dimension(150, 80));
        studentHonorsButton.setHorizontalAlignment(JButton.CENTER);
        studentHonorsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                 跳转到 StudentHonors 页面
               StudentHonorApp studentHonorApp=new StudentHonorApp(studentID);
                studentHonorApp.setVisible(true);
                dispose();
            }
        });

        studentProjectButton = new JButton("<html>Student<br>Project</html>");
        studentProjectButton.setFont(new Font("Helvetica", Font.PLAIN, 16));
        studentProjectButton.setPreferredSize(new Dimension(150, 80));
        studentProjectButton.setHorizontalAlignment(JButton.CENTER);
        studentProjectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                 跳转到 StudentProject 页面
                StudentProjectApp studentProject = new StudentProjectApp(studentID);
                studentProject.setVisible(true);
                dispose();
            }
        });

        studentExamResultsButton = new JButton("<html>Student Exam<br>Results</html>");
        studentExamResultsButton.setFont(new Font("Helvetica", Font.PLAIN, 16));
        studentExamResultsButton.setPreferredSize(new Dimension(150, 80));
        studentExamResultsButton.setHorizontalAlignment(JButton.CENTER);
        studentExamResultsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 跳转到 StudentExamResults 页面
                StudentScoreApp studentExamResults = new StudentScoreApp(studentID);
                studentExamResults.setVisible(true);
                dispose();
            }
        });

        learningJourneyButton = new JButton("<html>Learning<br>Journey</html>");
        learningJourneyButton.setFont(new Font("Helvetica", Font.PLAIN, 16));
        learningJourneyButton.setPreferredSize(new Dimension(150, 80));
        learningJourneyButton.setHorizontalAlignment(JButton.CENTER);
        learningJourneyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                // 跳转到 LearningJourney 页面
//                LearningJourney learningJourney = new LearningJourney();
//                learningJourney.setVisible(true);
//                dispose();
            }
        });

        cvGenerationButton = new JButton("<html>CV<br>Generation</html>");
        cvGenerationButton.setFont(new Font("Helvetica", Font.PLAIN, 16));
        cvGenerationButton.setPreferredSize(new Dimension(150, 80));
        cvGenerationButton.setHorizontalAlignment(JButton.CENTER);
        cvGenerationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 跳转到 CVGeneration 页面
//                CVGeneration cvGeneration = new CVGeneration();
//                cvGeneration.setVisible(true);
//                dispose();
            }
        });

        studyAbroadAssessmentButton = new JButton("<html>Study Abroad<br>Assessment</html>");
        studyAbroadAssessmentButton.setFont(new Font("Helvetica", Font.PLAIN, 16));
        studyAbroadAssessmentButton.setPreferredSize(new Dimension(150, 80));
        studyAbroadAssessmentButton.setHorizontalAlignment(JButton.CENTER);
        studyAbroadAssessmentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 跳转到 StudyAbroadAssessment 页面
//                StudyAbroadAssessment studyAbroadAssessment = new StudyAbroadAssessment();
//                studyAbroadAssessment.setVisible(true);
//                dispose();
            }
        });

        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Helvetica", Font.PLAIN, 16));
        exitButton.setPreferredSize(new Dimension(150, 80));
        exitButton.setHorizontalAlignment(JButton.CENTER);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 跳转到 LoginFrame 页面
//                LoginFrame loginFrame = new LoginFrame();
//                loginFrame.setVisible(true);
//                dispose();
                JFrame newFrame =new LoginFrame();
                newFrame.pack();
                newFrame.setVisible(true);
                dispose();
            }
        });

        // 创建面板，并将组件添加到面板上
        JPanel buttonPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        buttonPanel.add(studentStatusButton);
        buttonPanel.add(studentHonorsButton);
        buttonPanel.add(studentProjectButton);
        buttonPanel.add(studentExamResultsButton);
        buttonPanel.add(learningJourneyButton);
        buttonPanel.add(cvGenerationButton);
        buttonPanel.add(studyAbroadAssessmentButton);
        buttonPanel.add(exitButton);

        // 将标题和按钮面板添加到窗口中
        Container contentPane = getContentPane();
        contentPane.add(titleLabel, BorderLayout.NORTH);
        contentPane.add(buttonPanel, BorderLayout.CENTER);

        // 设置窗口大小、位置、可见性
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public static void main(String[] args) {
        String ID="2020213362";
        new LearningJourneyApp(ID);
    }
}