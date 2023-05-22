/**
 * this class is used to add the skills
 */

package cn.edu.bupt;

import javax.swing.*;
import java.util.List;

public class SkillForm extends JFrame {

    private final JLabel titleLabel;
    private final JTextField skillNameField;
    private final JTextField skillLevelField;
    private final JButton submitButton;
    private final JButton backButton;

    public SkillForm(final String studentID) {
        super("Adding your skill here");

        // create titles
        titleLabel = new JLabel("Adding your skill here");
        titleLabel.setFont(new java.awt.Font("Helvetica", 1, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        // create the input box
        skillNameField = new JTextField(20);
        skillNameField.setHorizontalAlignment(JTextField.CENTER);
        skillNameField.setPreferredSize(new java.awt.Dimension(200, 30));

        skillLevelField = new JTextField(20);
        skillLevelField.setHorizontalAlignment(JTextField.CENTER);
        skillLevelField.setPreferredSize(new java.awt.Dimension(200, 30));

        // create submit and back button
        submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new java.awt.Dimension(200, 30));
        backButton = new JButton("Back");
        backButton.setPreferredSize(new java.awt.Dimension(200, 30));

        // create the panel and add the components
        JPanel panel = new JPanel(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.insets = new java.awt.Insets(10, 10, 10, 10);

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


        /**
         * Extract all the information and add them into json
         */
        submitButton.addActionListener(e -> {
            String skillName = skillNameField.getText();
            String skillLevel = skillLevelField.getText();
            if (skillName.isEmpty() || skillLevel.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all the fields.");
            } else {
                Student s = DB.getStudent(studentID);
                List<Skill> skills;
                if (s != null) {
                    skills = s.getSkills();
                    skills.add(new Skill(skillName, skillLevel));
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


        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}