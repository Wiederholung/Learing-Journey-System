package cn.edu.bupt;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StudentScoreApp extends JFrame {
    private String studentID;
    private ArrayList<String[]> courses;

    public StudentScoreApp(String studentID) {
        this.studentID = studentID;
        this.courses = new ArrayList<>();
//        readProjects();
        Student s = DB.getStudent(studentID);
        if (s != null) {
            List<Course> cours = s.getCourses();
            // 将projs转为ArrayList<String[]>
            for (Course  c : cours) {
                String[] course = new String[4];
                course[0] = c.getCourse_id();
                course[1] =  c.getCredits();
                course[2] = String.valueOf(c.getGrade());
                course[3]=String.valueOf((c.getGrade()/20)-1);
                courses.add(course);
            }
        }

        setTitle("Student Projects");
        setSize(600, 400);
        setLocationRelativeTo(null);

        // 创建主面板
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // 创建返回按钮和添加按钮
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            dispose();
            new LearningJourneyApp(studentID);
        });
        JButton addButton = new JButton("Statistic Information");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(backButton);
        buttonPanel.add(addButton);
        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        // 创建表头面板
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(1, 4));
        JLabel courseIDLabel = new JLabel("Course ID");
        JLabel creditsLabel = new JLabel("Credits");
        JLabel scoreLabel = new JLabel("Score");
        JLabel gpaLabel = new JLabel("GPA");
        headerPanel.add(courseIDLabel);
        headerPanel.add(creditsLabel);
        headerPanel.add(scoreLabel);
        headerPanel.add(gpaLabel);
        mainPanel.add(headerPanel, BorderLayout.CENTER);

        // 创建项目按钮面板
        JPanel projectPanel = new JPanel();
        projectPanel.setLayout(new GridLayout(courses.size(), 1));

        // 创建每个项目的按钮
        for (String[] project : courses) {
            JButton projectButton = new JButton(project[0] + " " + project[1] + " " + project[2] + " " + project[3]);
            projectButton.setPreferredSize(new Dimension(550 / courses.size(), 50));
            projectButton.setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
            projectButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            projectButton.setEnabled(false);
            projectPanel.add(projectButton);
        }

        mainPanel.add(projectPanel, BorderLayout.SOUTH);
        setSize(600, 800);
        add(mainPanel);
        setVisible(true);
    }
    public static void main(String[] args) {
        String ID = "2020213362";
        new StudentScoreApp(ID);
    }
}



