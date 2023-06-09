package com.metattri.app;

import com.metattri.dao.DB;
import com.metattri.entity.Student;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * The LearningJourneyApp class extends JFrame and provides the functionality to display the learning journey information of a student.
 */
public class LearningJourneyApp extends JFrame {
    private int days;
    private int courses;
    private int numOfHonors;
    private int numOfProjects;
    public String dateOfGradual;
    public String major;
    private String lessons;
    private double score;

    /**
     * Constructs a LearningJourneyApp object for the given student ID.
     *
     * @param studentID the ID of the student
     */
    public LearningJourneyApp(String studentID) {
        // Calculate the variables to be shown in the interface
        Student student = DB.getStudent(studentID);
        LocalDate enroll_date;
        if (student != null) {
            enroll_date = LocalDate.parse(student.getEnrollDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            days = (int) ChronoUnit.DAYS.between(enroll_date, LocalDate.now());
            courses = student.getCourses().size();
            double maxScore = 0;
            for (Student.Course course : student.getCourses()) {
                if (course.getGrade() > maxScore) {
                    maxScore = course.getGrade();
                    lessons = course.getCourse_id();
                    score = course.getGrade();
                }
            }

            numOfHonors = student.getHonors().size();
            numOfProjects = student.getProjs().size();
            dateOfGradual = student.getGradDate();
            major = student.getMajor();
        }

        // Create and configure the GUI components
        JFrame frame = new JFrame("Learning Journey");
        frame.setSize(500, 500);
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 200, 20));
        JLabel label1 = null;
        if (student != null) {
            label1 = new JLabel("<html><h1 style='text-align:center;'>" + student.getName() + "：</h1></html>");
        }
        JLabel label2 = new JLabel("<html><h2 style='text-align:center;'>Enjoy your Learning journey in BUPT and QMUL!</h2></html>");
        JLabel label3 = new JLabel("<html><h3 style='text-align:center;'>Now you have joined us for <font size='6'>" + days + "</font> days!</h3></html>");
        JLabel label4 = new JLabel("<html><h3 style='text-align:center;'>You have learned <font size='6'>" + courses + "</font> subjects, receiving a wealth of knowledge!</h3></html>");
        JLabel label5 = new JLabel("<html><h3 style='text-align:center;'>Overall, you performed best in the subject <font size='6'>" + lessons + "</font>, scoring <font size='6'>" + score + "</font>! How excellent you are!</h3></html>");
        JLabel label6 = new JLabel("<html><h3 style='text-align:center;'>Until now, you have gained <font size='6'>" + numOfHonors + "</font> honors/scholarships!</h3></html>");
        JLabel label7 = new JLabel("<html><h3 style='text-align:center;'>You have completed <font size='6'>" + numOfProjects + "</font> projects during this time. <br>--the difficulties youbeat will become your glories!</h3></html>");
        JLabel label8 = new JLabel("<html><h3 style='text-align:center;'>In <font size='6'>" + dateOfGradual + "</font>, you will receive a bachelor’s degree of:<br>" + "<font size='12'>" + major + "</font>" + "!<br>That is a significant step you take in your career, and we believe you will be excellent in the future!</font></h3></html>");

        if (label1 != null) {
            label1.setHorizontalAlignment(SwingConstants.CENTER);
        }
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        label4.setHorizontalAlignment(SwingConstants.CENTER);
        label5.setHorizontalAlignment(SwingConstants.CENTER);
        label6.setHorizontalAlignment(SwingConstants.CENTER);
        label7.setHorizontalAlignment(SwingConstants.CENTER);
        label8.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.add(label5);
        panel.add(label6);
        panel.add(label7);
        panel.add(label8);

        // Set up the back button
        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(200, 50));
        panel.add(backButton);
        backButton.addActionListener(e -> {
            frame.dispose();
            new WelcomeApp(studentID);
        });

        frame.setSize(800, 800);
        frame.getContentPane().add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}