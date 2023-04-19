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

        // 创建表格
        String[] columnNames = {"Course ID", "Credits", "Score", "GPA"};
        JTable table = new JTable(courses.toArray(new Object[0][0]), columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        setSize(600, 800);
        add(mainPanel);
        setVisible(true);
    }
    public static void main(String[] args) {
        String ID = "2020213362";
        new StudentScoreApp(ID);
    }
}

