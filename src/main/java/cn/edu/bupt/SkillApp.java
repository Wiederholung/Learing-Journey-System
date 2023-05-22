/**
 * This class is the interface to show all of student's skills
 */
package cn.edu.bupt;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SkillApp extends JFrame {
    private final ArrayList<String[]> skills;

    public SkillApp(String studentID) {


        this.skills = new ArrayList<>();
        Student s = DB.getStudent(studentID);
        if (s != null) {
            List<Skill> skill = s.getSkills();
            for (Skill ski : skill) {
                String[] s_skill = new String[2];
                s_skill[0] = ski.getSkill_name();
                s_skill[1] = ski.getSkill_level();

                skills.add(s_skill);
            }
        }


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        //add and back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            dispose();
            new LearningJourneyApp(studentID);
        });
        JButton addButton = new JButton("Add New");
        addButton.addActionListener(e -> {
            dispose();
            new SkillForm(studentID);
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(backButton);
        buttonPanel.add(addButton);
        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        JPanel skillPanel = new JPanel();
        skillPanel.setLayout(new BoxLayout(skillPanel, BoxLayout.Y_AXIS));



        //Create the button of every project
        for (String[] skill : skills) {

            JButton projectButton = new JButton(skill[0] + " " + skill[1]);
            projectButton.setPreferredSize(new Dimension(550, 50));
            projectButton.setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
            projectButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            projectButton.addActionListener(e -> {
                JPanel projectDetailPanel = new JPanel();
                projectDetailPanel.setLayout(new BoxLayout(projectDetailPanel, BoxLayout.Y_AXIS));

                JLabel honorNameLabel = new JLabel("Honor Name: " + skill[0]);
                JLabel honorTimeLabel = new JLabel("Honor Time: " + skill[1]);
                JPanel detailPanel = new JPanel();
                detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));
                detailPanel.add(honorNameLabel);
                detailPanel.add(honorTimeLabel);


                //add backButton and delete Button
                JButton backButton1 = new JButton("Back");
                backButton1.setAlignmentX(Component.CENTER_ALIGNMENT);
                backButton1.addActionListener(e1 -> {
                   new SkillApp(studentID);
                    dispose();
                });

                JButton deleteButton = new JButton("Delete");
                deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                deleteButton.addActionListener(e12 -> {
                    int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this skill?", "Delete skill", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        System.out.println("success");
                        DB.writeToJson(DB.deleteSkill(skill[0], skill[1]));
                        new SkillApp(studentID);
                        dispose();
                    }
                });

                JPanel buttonPanel2 = new JPanel();
                buttonPanel2.setLayout(new FlowLayout(FlowLayout.CENTER));
                buttonPanel2.add(backButton1);
                buttonPanel2.add(deleteButton);

                detailPanel.add(buttonPanel2);
                mainPanel.setVisible(false);
                detailPanel.setVisible(true);
                getContentPane().add(detailPanel, BorderLayout.CENTER);

            });

            skillPanel.add(projectButton);
        }
        mainPanel.add(skillPanel, BorderLayout.CENTER);
        getContentPane().add(mainPanel);

        setTitle("Student Skills");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
