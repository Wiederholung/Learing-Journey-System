package cn.edu.bupt;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SikllApp extends JFrame {
    private final ArrayList<String[]> skills;

    public SikllApp(String studentID) {


        this.skills = new ArrayList<>();
//        readProjects();
        Student s = DB.getStudent(studentID);
        if (s != null) {
            List<Skill> skill = s.getSkills();
            // 将projs转为ArrayList<String[]>
            for (Skill ski : skill) {
                String[] s_skill = new String[2];
                s_skill[0] = ski.getSkill_name();
                s_skill[1] = ski.getSkill_level();

                skills.add(s_skill);
            }
        }

        // 创建主面板
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // 创建返回按钮和添加按钮
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

        // 创建项目按钮面板
        JPanel skillPanel = new JPanel();
        skillPanel.setLayout(new BoxLayout(skillPanel, BoxLayout.Y_AXIS));

        // 创建每个项目的按钮
        for (String[] skill : skills) {

            JButton projectButton = new JButton(skill[0] + " " + skill[1]);
            projectButton.setPreferredSize(new Dimension(550, 50));
            projectButton.setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
            projectButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            // 创建项目按钮的监听器
            projectButton.addActionListener(e -> {
                // 创建新的项目详情面板
                JPanel projectDetailPanel = new JPanel();
                projectDetailPanel.setLayout(new BoxLayout(projectDetailPanel, BoxLayout.Y_AXIS));

                // 添加项目详情信息
                projectDetailPanel.add(new JLabel("Honor Name: " + skill[0]));
                projectDetailPanel.add(new JLabel("Honor Time: " + skill[1]));
//                    projectDetailPanel.add(new JLabel("Project Description: " + project[2]));

                // 添加返回按钮
                JButton backButton1 = new JButton("Back");
                backButton1.addActionListener(e1 -> {
                    mainPanel.setVisible(true);
                    projectDetailPanel.setVisible(false);
                });
                projectDetailPanel.add(backButton1);

                JButton deleteButton = new JButton("Delete");
                deleteButton.addActionListener(e12 -> {
                    int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this honor?", "Delete honor", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        System.out.println("success");
                        DB.deleteHonor(skill[0], skill[1]);
                        new SikllApp(studentID);
                        dispose();
                    }
                });
                projectDetailPanel.add(deleteButton);
                // 显示项目详情面板
                mainPanel.setVisible(false);
                projectDetailPanel.setVisible(true);
                getContentPane().add(projectDetailPanel, BorderLayout.CENTER);
            });

            skillPanel.add(projectButton);
        }

        // 添加项目面板到主面板
        mainPanel.add(skillPanel, BorderLayout.CENTER);
        getContentPane().add(mainPanel);

        setTitle("Student Skills");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
