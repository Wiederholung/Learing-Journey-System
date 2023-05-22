/**
 * this class is used to show all the information of honor and make some operation on it
 */
package cn.edu.bupt;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StudentHonorApp extends JFrame {
    private final ArrayList<String[]> honors;

    public StudentHonorApp(String studentID) {
        this.honors = new ArrayList<>();
        Student s = DB.getStudent(studentID);
        if (s != null) {
            List<Honor> honor = s.getHonors();
            for (Honor h : honor) {
                String[] s_honor = new String[2];
                s_honor[0] = h.getHonor_name();
                s_honor[1] = h.getHonor_time();

                honors.add(s_honor);
            }
        }
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Create the back and the add button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            dispose();
            new LearningJourneyApp(studentID);
        });
        JButton addButton = new JButton("Add New");
        addButton.addActionListener(e -> {
            dispose();
            new StudentHonorForm(studentID);
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(backButton);
        buttonPanel.add(addButton);
        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        JPanel projectPanel = new JPanel();
        projectPanel.setLayout(new BoxLayout(projectPanel, BoxLayout.Y_AXIS));

        //Create the button of every honors
        for (String[] project : honors) {

            JButton projectButton = new JButton(project[0] + " " + project[1]);
            projectButton.setPreferredSize(new Dimension(550, 50));
            projectButton.setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
            projectButton.setAlignmentX(Component.CENTER_ALIGNMENT);



            projectButton.addActionListener(e -> {
                // Create new panel of the content of honors
                JPanel projectDetailPanel = new JPanel();
                projectDetailPanel.setLayout(new BoxLayout(projectDetailPanel, BoxLayout.Y_AXIS));

                JPanel detailPanel = new JPanel();
                detailPanel.setLayout(new GridLayout(2, 1, 0, 10));
                detailPanel.add(new JLabel("Honor Name: " + project[0], SwingConstants.CENTER));
                detailPanel.add(new JLabel("Honor Time: " + project[1], SwingConstants.CENTER));



                // Create back button and delete button
                JButton backButton1 = new JButton("Back");
                backButton1.addActionListener(e1 -> {
                    mainPanel.setVisible(true);
                    projectDetailPanel.setVisible(false);
                });
                backButton1.setAlignmentX(Component.CENTER_ALIGNMENT);
                JButton deleteButton = new JButton("Delete");
                deleteButton.addActionListener(e12 -> {
                    int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this honor?", "Delete honor", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        System.out.println("success");
                        DB.writeToJson( DB.deleteHonor(project[0], project[1]));
                        new StudentHonorApp(studentID);
                        dispose();
                    }
                });
                deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);


                mainPanel.setVisible(false);
                projectDetailPanel.setVisible(true);


                projectDetailPanel.removeAll();
                projectDetailPanel.setLayout(new BoxLayout(projectDetailPanel, BoxLayout.Y_AXIS));
                projectDetailPanel.add(Box.createVerticalGlue());
                projectDetailPanel.add(detailPanel);
                projectDetailPanel.add(Box.createVerticalGlue());
                projectDetailPanel.add(backButton1);
                projectDetailPanel.add(Box.createVerticalGlue());
                projectDetailPanel.add(deleteButton);
                projectDetailPanel.add(Box.createVerticalGlue());

                getContentPane().add(projectDetailPanel, BorderLayout.CENTER);
            });

            projectPanel.add(projectButton);
        }

        mainPanel.add(projectPanel, BorderLayout.CENTER);
        getContentPane().add(mainPanel);

        setTitle("Student Honors");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
