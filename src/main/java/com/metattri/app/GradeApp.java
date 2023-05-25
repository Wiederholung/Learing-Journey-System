package com.metattri.app;

import com.metattri.dao.DB;
import com.metattri.dao.Student;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * A GUI application for displaying student grades and calculating statistics.
 */
public class GradeApp extends JFrame {
    private final ArrayList<String[]> courses;

    /**
     * Constructs a GradeApp object for the given student ID.
     *
     * @param studentID the ID of the student
     */
    public GradeApp(String studentID) {
        this.courses = new ArrayList<>();

        Student s = DB.getStudent(studentID);
        if (s != null) {
            for (Student.Course c : s.getCourses()) {
                String[] course = new String[4];
                course[0] = c.getCourse_id();
                course[1] = c.getCredits();
                course[2] = String.valueOf(c.getGrade());
                double gp = 4 - 3 * (100 - (double) c.getGrade()) * (100 - (double) c.getGrade()) / 1600;
                course[3] = String.format("%.2f", gp);
                courses.add(course);
            }
        }

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Create the forms
        String[] columnNames = {"Course ID", "Credits", "Score", "GP"};
        JTable table = new JTable(courses.toArray(new Object[0][0]), columnNames);
        table.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        add(mainPanel);

        // Create back and add Button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            dispose();
            new WelcomeApp(studentID);
        });

        JButton addButton = new JButton("Statistic Information");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(backButton);
        buttonPanel.add(addButton);
        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        // Click the add button to pop up the dialog box and print the statistics.
        addButton.addActionListener(e -> {
            double[] statInfo = getStatistics();
            JOptionPane.showMessageDialog(null, "Weighted Average Mark: " + statInfo[0] + "\n" + "GPA: " + statInfo[1], "Statistic Information", JOptionPane.INFORMATION_MESSAGE);
        });

        setTitle("Student Projects");
        setSize(600, 650);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Calculates the weighted average mark and GPA based on the student's courses.
     *
     * @return an array containing the weighted average mark at index 0 and GPA at index 1
     */
    public double[] getStatistics() {
        double[] statInfo = new double[2];
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
        statInfo[0] = wam;
        statInfo[1] = gpa;
        return statInfo;
    }
}
