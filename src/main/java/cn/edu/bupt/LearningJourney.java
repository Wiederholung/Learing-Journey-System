package cn.edu.bupt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class LearningJourney extends JFrame {
    private final int days;
    private final int courses;
    private final int numOfHonors;
    private final int numOfProjects;
    public String dateOfGradual;
    public String major;
    private String lessons;
    private double score;

    public LearningJourney(String studentID) {
        Student student = DB.getStudent(studentID);

        LocalDate enroll_date = LocalDate.parse(student.getEnrollDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        days = (int) ChronoUnit.DAYS.between(enroll_date, LocalDate.now());

        courses = student.getCourses().size();

        double maxScore = 0;
        for (Course course : student.getCourses()) {
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

        JFrame frame = new JFrame("Learning Journey");
        frame.setSize(500, 500);

        JPanel panel = new JPanel(new GridLayout(9, 1));

        JLabel label1 = new JLabel("<html><h1 style='text-align:center;'>" + student.getName() + "：</h1></html>");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label1);

        JLabel label2 = new JLabel("<html><h2 style='text-align:center;'>Enjoy your Learning journey in BUPT and QMUL!</h2></html>");
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label2);

        JLabel label3 = new JLabel("<html><h3 style='text-align:center;'>Now you have joined us for <font size='6'>" + days + "</font> days!</h3></html>");
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label3);

        JLabel label4 = new JLabel("<html><h3 style='text-align:center;'>You have learned <font size='6'>" + courses + "</font> subjects, receive unsurmontable knowledge!</h3></html>");
        label4.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label4);

        JLabel label5 = new JLabel("<html><h3 style='text-align:center;'>Overall, you performed best in the subject <font size='6'>" + lessons + "</font>, scored <font size='6'>" + score + "</font> ! How excellent you are!</h3></html>");
        label5.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label5);


        JLabel label6 = new JLabel("<html><h3 style='text-align:center;'>Until now, you have gained <font size='6'>" + numOfHonors + "</font> honors/scolarships!</h3></html>");
        label6.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label6);

        JLabel label7 = new JLabel("<html><h3 style='text-align:center;'>You have completed <font size='6'>" + numOfProjects + "</font> during these time. <br>--the difficulties you beat will become your glories!</h3></html>");
        label7.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label7);

        JLabel label8 = new JLabel("<html><h3 style='text-align:center;'>In <font size='6'>" + dateOfGradual + "</font>, you will receive a bachelor’s degree of ： <br>" + "<font size='12'>" + major + "</font>" + " ! <br>That is a significant step you take in your career and we believe you will be excellent in the future!</font></h3></html>");
        label8.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label8);
        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(200, 100));
        panel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LearningJourneyApp(studentID);
            }
        });
        frame.setSize(800, 1000);
        frame.getContentPane().add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        LearningJourney learningJourney=new LearningJourney("2020213362");
    }
}
