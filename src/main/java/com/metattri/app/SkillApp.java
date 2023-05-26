package com.metattri.app;

import com.metattri.dao.DB;
import com.metattri.entity.Student;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the interface to show all of a student's skills.
 * It allows the user to view their skills, add new skills, and delete existing skills.
 */
public class SkillApp extends JFrame {
    /**
     * Constructs a SkillApp object and initializes the interface.
     * Retrieves the student's skills from the database and displays them.
     *
     * @param studentID the ID of the student
     */
    public SkillApp(String studentID) {
        // Retrieve the student's skills from the database
        ArrayList<String[]> skills = new ArrayList<>();
        Student s = DB.getStudent(studentID);
        if (s != null) {
            List<Student.Skill> skillList = s.getSkills();
            for (Student.Skill skill : skillList) {
                String[] s_skill = new String[2];
                s_skill[0] = skill.getSkill_name();
                s_skill[1] = skill.getSkill_level();
                skills.add(s_skill);
            }
        }

        // Create the main panel and buttons
        JPanel mainPanel = new JPanel();
        JButton backButton = new JButton("Back");
        JButton addButton = new JButton("Add New");
        JPanel buttonPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        JPanel skillPanel = new JPanel();

        // Set up the button panel and add buttons
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(backButton);
        buttonPanel.add(addButton);
        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        skillPanel.setLayout(new BoxLayout(skillPanel, BoxLayout.Y_AXIS));

        // Set up actions for the buttons
        backButton.addActionListener(e -> {
            dispose();
            new WelcomeApp(studentID);
        });
        addButton.addActionListener(e -> {
            dispose();
            new SkillForm(studentID);
        });

        // Create buttons for each skill and add them to the skill panel
        for (String[] skill : skills) {
            JButton projectButton = new JButton(skill[0] + " " + skill[1]);
            JLabel informationLabel = new JLabel("<HTML><body style='text-align: center;'><h3>Skill Name:</h3>" + skill[0] +
                    "<br><h3>Skill Level:</h3>" + skill[1] +
                    "<br></body></html");
            projectButton.setPreferredSize(new Dimension(550, 50));
            projectButton.setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
            projectButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            projectButton.addActionListener(e -> {
                // Display detailed information for the selected skill
                JPanel projectDetailPanel = new JPanel();
                projectDetailPanel.setLayout(new BoxLayout(projectDetailPanel, BoxLayout.Y_AXIS));
                projectDetailPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

                // Add skill information to the panel
                informationLabel.setHorizontalAlignment(SwingConstants.CENTER);
                informationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                informationLabel.setMaximumSize(new Dimension(300, Integer.MAX_VALUE));
                projectDetailPanel.add(informationLabel);

                // Add back button and delete button
                JPanel infoButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                JButton backButton1 = new JButton("Back");
                backButton1.setAlignmentX(Component.CENTER_ALIGNMENT);
                backButton1.setPreferredSize(new Dimension(100, 50));
                JButton deleteButton = new JButton("Delete");
                deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                deleteButton.setPreferredSize(new Dimension(100, 50));

                // Set actions for back button and delete button
                backButton1.addActionListener(e1 -> {
                    new SkillApp(studentID);
                    dispose();
                });
                deleteButton.addActionListener(e12 -> {
                    int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this skill?", "Delete skill", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        DB.writeToJson(DB.deleteSkill(skill[0], skill[1]));
                        new SkillApp(studentID);
                        dispose();
                    }
                });

                // Add buttons to the panel
                infoButtonPanel.add(backButton1);
                infoButtonPanel.add(deleteButton);
                mainPanel.setVisible(false);
                projectDetailPanel.add(infoButtonPanel);
                projectDetailPanel.setVisible(true);
                getContentPane().add(projectDetailPanel, BorderLayout.CENTER);
            });

            skillPanel.add(projectButton);
        }

        // Add the skill panel to the main panel
        mainPanel.add(skillPanel, BorderLayout.CENTER);
        getContentPane().add(mainPanel);

        setTitle("Student Skills");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This class represents the skill form for adding a new skill.
     * It allows the user to enter the skill name and skill level.
     */
    public static class SkillForm extends JFrame {

        private final JTextField skillNameField;
        private final JTextField skillLevelField;

        /**
         * Constructs a SkillForm object and initializes the skill form interface.
         *
         * @param studentID the ID of the student
         */
        public SkillForm(final String studentID) {
            super("Adding your skill here");

            // Create the title label
            JLabel titleLabel = new JLabel("Adding your skill here");
            titleLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
            titleLabel.setHorizontalAlignment(JLabel.CENTER);

            // Create the input fields
            skillNameField = new JTextField(20);
            skillNameField.setHorizontalAlignment(JTextField.CENTER);
            skillNameField.setPreferredSize(new Dimension(200, 30));

            skillLevelField = new JTextField(20);
            skillLevelField.setHorizontalAlignment(JTextField.CENTER);
            skillLevelField.setPreferredSize(new Dimension(200, 30));

            // Create the submit and back buttons
            JButton submitButton = new JButton("Submit");
            submitButton.setPreferredSize(new Dimension(200, 30));
            JButton backButton = new JButton("Back");
            backButton.setPreferredSize(new Dimension(200, 30));

            // Create the panel and add the components
            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            panel.add(titleLabel, gbc);
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            panel.add(new JLabel("Skill Name"), gbc);
            gbc.gridx = 1;
            gbc.gridy = 1;
            panel.add(skillNameField, gbc);
            gbc.gridx = 0;
            gbc.gridy = 2;
            panel.add(new JLabel("Skill Level"), gbc);
            gbc.gridx = 1;
            gbc.gridy = 2;
            panel.add(skillLevelField, gbc);
            gbc.gridx = 0;
            gbc.gridy = 3;
            panel.add(submitButton, gbc);
            gbc.gridx = 1;
            gbc.gridy = 3;
            panel.add(backButton, gbc);

            // Set actions for the submit and back buttons
            submitButton.addActionListener(e -> {
                String skillName = skillNameField.getText();
                String skillLevel = skillLevelField.getText();
                if (skillName.isEmpty() || skillLevel.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all the fields.");
                } else {
                    Student s = DB.getStudent(studentID);
                    List<Student.Skill> skills;
                    if (s != null) {
                        skills = s.getSkills();
                        skills.add(new Student.Skill(skillName, skillLevel));
                        s.setSkills(skills);
                        DB.writeToJson(DB.updateStudent(s));
                        skillNameField.setText("");
                        skillLevelField.setText("");
                        JOptionPane.showMessageDialog(null, "Skill submitted successfully!");
                    }
                }
            });
            backButton.addActionListener(e -> {
                this.dispose();
                new SkillApp(studentID);
            });

            // Add the panel to the frame
            this.add(panel);
            this.pack();
            this.setLocationRelativeTo(null);
            this.setVisible(true);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }
}

