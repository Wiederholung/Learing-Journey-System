/**
 * this class is used to show all the information of project and make some operation on it
 */
package cn.edu.bupt;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StudentProjectApp extends JFrame {

    public StudentProjectApp(String studentID) {
        ArrayList<String[]> projects = new ArrayList<>();
        Student s = DB.getStudent(studentID);
        if (s != null) {
            List<Project> projs = s.getProjs();
            for (Project p : projs) {
                String[] project = new String[3];
                project[0] = p.getProj_name();
                project[1] = p.getProj_time();
                project[2] = p.getDescribe();
                projects.add(project);
            }
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Student Projects");
        setSize(600, 400);
        setLocationRelativeTo(null);


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Create add and back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            dispose();
            new LearningJourneyApp(studentID);
        });
        JButton addButton = new JButton("Add New");
        addButton.addActionListener(e -> {
            dispose();
            new ProjectExperienceForm(studentID);
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(backButton);
        buttonPanel.add(addButton);
        mainPanel.add(buttonPanel, BorderLayout.NORTH);


        JPanel projectPanel = new JPanel();
        projectPanel.setLayout(new BoxLayout(projectPanel, BoxLayout.Y_AXIS));

        // Create buttons to every project
        for (String[] project : projects) {

            JButton projectButton = new JButton(project[1] + " " + project[2]);
            projectButton.setPreferredSize(new Dimension(550, 50));
            projectButton.setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
            projectButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            projectButton.addActionListener(e -> {
                // Create new panel for project
                JPanel projectDetailPanel = new JPanel();
                projectDetailPanel.setLayout(new BoxLayout(projectDetailPanel, BoxLayout.Y_AXIS));
                projectDetailPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

                //add information to project
                JLabel projectNameLabel = new JLabel("Project Name: " + project[0]);
                projectNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                projectDetailPanel.add(projectNameLabel);

                JLabel projectTimeLabel = new JLabel("Project Time: " + project[1]);
                projectTimeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                projectDetailPanel.add(projectTimeLabel);

                JLabel projectDescriptionLabel = new JLabel("Project Description: " + project[2]);
                projectDescriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                projectDetailPanel.add(projectDescriptionLabel);

                // add back button
                JButton backButton1 = new JButton("Back");
                backButton1.addActionListener(e1 -> {
                    mainPanel.setVisible(true);
                    projectDetailPanel.setVisible(false);
                });
                backButton1.setAlignmentX(Component.CENTER_ALIGNMENT);
                projectDetailPanel.add(backButton1);

                // add delete button
                JButton deleteButton = new JButton("Delete");
                deleteButton.addActionListener(e12 -> {
                    int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this project?", "Delete Project", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        System.out.println("success");
                       DB.writeToJson( DB.deleteProject(project[0], project[1]));
                        new StudentProjectApp(studentID);
                        dispose();
                    }
                });
                deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                projectDetailPanel.add(deleteButton);


                mainPanel.setVisible(false);
                projectDetailPanel.setVisible(true);
                getContentPane().add(projectDetailPanel, BorderLayout.CENTER);
            });

            projectPanel.add(projectButton);
        }



        mainPanel.add(projectPanel, BorderLayout.CENTER);

        getContentPane().add(mainPanel);
        setVisible(true);
    }


}
