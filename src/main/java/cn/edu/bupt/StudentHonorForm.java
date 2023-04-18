package cn.edu.bupt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;
import java.util.Scanner;

public class StudentHonorForm extends JFrame{

    private JLabel titleLabel;
    private JTextField projectTimeField;
    private JTextArea projectContentArea;
    private JButton submitButton, backButton;
    private String studentID;

    public StudentHonorForm(final String studentID) {

        super("Adding your project experience here");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.studentID=studentID;
        // 创建标题标签，设置字体和尺寸，居中对齐
        titleLabel = new JLabel("Adding your project experience here");
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

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
        panel.add(new JLabel("Project Time:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(projectTimeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Project Content:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(scrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panel.add(submitButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(backButton, gbc);


        // 将面板添加到窗口上
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);


        // 清空所有输入框内容
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 获取输入框中的信息
                String honorTime = projectTimeField.getText();
                String honorContent = projectContentArea.getText();
                // 从StudentInfo.csv中获取学生姓名和学号
                String studentName = "";
                String student_ID = " ";

                // 将项目信息写入StudentProject.csv中
                Student s = DB.getStudent(studentID);
                List<Honor> s_honor = null;
                if (s != null) {
                    s_honor = s.getHonors();
                    s_honor.add(new Honor(honorTime,honorContent));
                    s.setHonors(s_honor);
                    DB.updateStudent(s);


                    projectTimeField.setText("");
                    projectContentArea.setText("");
                    JOptionPane.showMessageDialog(null, "Project submitted successfully!");
                }
            }

        });
        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new StudentHonorApp(studentID);

            }
        });

        // 将面板添加到窗口上
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);



    }
    public static void main(String[] args) {
        String ID="2020213362";
        new StudentHonorForm(ID);
    }
}