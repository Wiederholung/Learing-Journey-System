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

        // 创建标题标签，设置字体和尺寸，居中对齐
        titleLabel = new JLabel("Adding your skill here");
        titleLabel.setFont(new java.awt.Font("Helvetica", 1, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        // 创建技能名称输入框，设置大小和位置
        skillNameField = new JTextField(20);
        skillNameField.setHorizontalAlignment(JTextField.CENTER);
        skillNameField.setPreferredSize(new java.awt.Dimension(200, 30));

        // 创建技能等级输入框，设置大小和位置
        skillLevelField = new JTextField(20);
        skillLevelField.setHorizontalAlignment(JTextField.CENTER);
        skillLevelField.setPreferredSize(new java.awt.Dimension(200, 30));

        // 创建提交按钮，设置大小和位置
        submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new java.awt.Dimension(200, 30));

        // 创建返回按钮，设置大小和位置
        backButton = new JButton("Back");
        backButton.setPreferredSize(new java.awt.Dimension(200, 30));

        // 创建面板，将组件添加到面板上
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
            new SikllApp(studentID);
        });

        // 设置窗口属性
        this.add(panel);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}