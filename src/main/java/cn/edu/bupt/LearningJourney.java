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

        JLabel label1 = new JLabel("<html><h1>Dear " + student.getName() + "：</h1></html>");
        panel.add(label1);

        JLabel label2 = new JLabel("<html><h2>Enjoy your Learning journey in BUPT and QMUL!</h2></html>");
        panel.add(label2);

        JLabel label3 = new JLabel("<html><h3>Now you have joined us for " + days + " days!</h3></html>");
        panel.add(label3);

        JLabel label4 = new JLabel("<html><h3>You have learned " + courses + " subjects, receive unsurmontable knowledge!</h3></html>");
        panel.add(label4);

        JLabel label5 = new JLabel("<html><h3>Overall, you performed best in the subject " + lessons + ", scored " + score + " ! How excellent you are!</h3></html>");
        panel.add(label5);


        JLabel label6 = new JLabel("<html><h3>Until now, you have gained " + numOfHonors + " honors/scolarships!</h3></html>");
        panel.add(label6);

        JLabel label7 = new JLabel("<html><h3>You have completed " + numOfProjects + " during these time. <br>--the difficulties you beat will become your glories!</h3></html>");
        panel.add(label7);

        JLabel label8 = new JLabel("<html><h3>In " + dateOfGradual + ", you will receive a bachelor’s degree of ： <br>" + major + " ! <br>That is a significant step you take in your career and we believe you will be excellent in the future!</h3></html>");
        panel.add(label8);
        JButton backButton = new JButton("back!");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                LearningJourneyApp app = new LearningJourneyApp(studentID);
                app.show();
            }
        });
        backButton.setSize(200, 100);
        panel.add(backButton);


        frame.setSize(600, 900);
        frame.getContentPane().add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
