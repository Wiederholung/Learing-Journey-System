package cn.edu.bupt;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StudentScoreApp extends JFrame {
    private final ArrayList<String[]> courses;

    public StudentScoreApp(String studentID) {
        this.courses = new ArrayList<>();
//        readProjects();
        Student s = DB.getStudent(studentID);
        if (s != null) {
            for (Course c : s.getCourses()) {
                String[] course = new String[4];
                course[0] = c.getCourse_id();
                course[1] = c.getCredits();
                course[2] = String.valueOf(c.getGrade());
                double gp = 4 - 3 * (100 - (double) c.getGrade()) * (100 - (double) c.getGrade()) / 1600;
                course[3] = String.format("%.2f", gp);
                courses.add(course);
            }
        }

        // 创建主面板
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // 创建表格
        String[] columnNames = {"Course ID", "Credits", "Score", "GP"};
        JTable table = new JTable(courses.toArray(new Object[0][0]), columnNames);
        table.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        add(mainPanel);

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
        // 点击添加按钮，弹出对话框，打印统计信息
        addButton.addActionListener(e -> {
            String[] statInfo = new String[2];
            double wam = 0;
            double gpa = 0;
            double totalCredits = 0;
            for (String[] course : courses) {
                wam += Double.parseDouble(course[1]) * Double.parseDouble(course[2]);
                gpa += Double.parseDouble(course[1]) * Double.parseDouble(course[3]);
                totalCredits += Double.parseDouble(course[1]);
            }
            wam /= totalCredits;
            gpa /= totalCredits;
            statInfo[0] = String.format("%.2f", wam);
            statInfo[1] = String.format("%.2f", gpa);

            JOptionPane.showMessageDialog(null, "Weighted Average Mark: " + statInfo[0] + "\n" + "GPA: " + statInfo[1], "Statistic Information", JOptionPane.INFORMATION_MESSAGE);
        });

        setTitle("Student Projects");
        setSize(600, 800);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}

