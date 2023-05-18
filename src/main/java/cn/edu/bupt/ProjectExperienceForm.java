package cn.edu.bupt;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProjectExperienceForm extends JFrame {

    private final JLabel titleLabel;
    private final JTextField projectNameField;
    private final JTextField projectTimeField;
    private final JTextArea projectContentArea;
    private final JButton submitButton;
    private final JButton backButton;

    public ProjectExperienceForm(final String studentID) {
        super("Adding your project experience here");

        // 创建标题标签，设置字体和尺寸，居中对齐
        titleLabel = new JLabel("Adding your project experience here");
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        // 创建项目名称输入框，设置大小和位置
        projectNameField = new JTextField(20);
        projectNameField.setHorizontalAlignment(JTextField.CENTER);
        projectNameField.setPreferredSize(new Dimension(200, 30));

        // 创建项目时间输入框，设置大小和位置
        projectTimeField = new JTextField(20);
        projectTimeField.setHorizontalAlignment(JTextField.CENTER);
        projectTimeField.setPreferredSize(new Dimension(200, 30));

        // 创建项目内容文本框，设置大小和位置
        projectContentArea = new JTextArea(5, 20);
        projectContentArea.setLineWrap(true);
        projectContentArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(projectContentArea);

        // 创建提交按钮，设置大小和位置
        submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(200, 30));

        // 创建返回按钮，设置大小和位置
        backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(200, 30));

        // 创建面板，将组件添加到面板上
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
        panel.add(new JLabel("Project Name:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(projectNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Project Time:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(projectTimeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Project Content:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(scrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        panel.add(submitButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(backButton, gbc);

        // 清空所有输入框内容
        submitButton.addActionListener(e -> {
            // 获取输入框中的信息
            String projectName = projectNameField.getText();
            String projectTime = projectTimeField.getText();
            String projectContent = projectContentArea.getText();

            // 将项目信息写入StudentProject.csv中
            Student s = DB.getStudent(studentID);
            List<Project> projs;
            if (s != null) {
                projs = s.getProjs();
                if(projectName.equals("") || projectTime.equals("") || projectContent.equals("")){
                    JOptionPane.showMessageDialog(null, "Please fill in all fields!");
                    return;
                }
                projs.add(new Project(projectName, projectTime, projectContent));
                s.setProjs(projs);
                DB.writeToJson(DB.updateStudent(s));
                projectNameField.setText("");
                projectTimeField.setText("");
                projectContentArea.setText("");
                JOptionPane.showMessageDialog(null, "Project submitted successfully!");
            }
        });
        backButton.addActionListener(e -> {
            dispose();
            new StudentProjectApp(studentID);
        });

        // 将面板添加到窗口上
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}