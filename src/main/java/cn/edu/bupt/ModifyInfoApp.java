package cn.edu.bupt;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ModifyInfoApp extends JFrame {
    public ModifyInfoApp(String studentID) {
        // 创建顶部标题
        JLabel titleLabel = new JLabel("Modify Your Status Information", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(new EmptyBorder(10, 0, 10, 0));

        // 创建可以修改的表格
        String[][] data = loadStudentData(studentID);
        String[] COLUMN_NAMES = {"Info", "Value"};
        JTable table = new JTable(data, COLUMN_NAMES);
        table.setEnabled(true);

        // 创建返回按钮
        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(e -> {
            // 关闭当前窗口
            dispose();
            // 创建并显示 LearningJourneyApp 窗口
            LearningJourneyApp app = new LearningJourneyApp(studentID);
            app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            app.setVisible(true);
        });

        // 创建提交按钮
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
//            将修改后的信息写入db.json
//            DB.modifyStudent(studentID, data);
            JOptionPane.showMessageDialog(null, "Modify Successfully!");

            // 关闭当前窗口
            dispose();
            // 创建并显示 LearningJourneyApp 窗口
            LearningJourneyApp app = new LearningJourneyApp(studentID);
            app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            app.setVisible(true);
        });

        // 添加返回和提交按钮到底部
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(returnButton);
        buttonPanel.add(submitButton);

        // 将组件添加到内容面板中
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.add(titleLabel, BorderLayout.NORTH);
        contentPane.add(new JScrollPane(table), BorderLayout.CENTER);
        setContentPane(contentPane);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        // 设置窗口标题和大小
        setTitle("Your Status Information");
        setSize(500, 400);
        setLocationRelativeTo(null);
        pack();
    }

    private String[][] loadStudentData(String studentID) {
        String[][] data = new String[8][2];
        Student s = DB.getStudent(studentID);
        if (s != null) {
            data[0] = new String[]{"Name", s.getName()};
            data[1] = new String[]{"Gender", s.getGender()};
            data[2] = new String[]{"StudentID", s.getSid()};
            data[3] = new String[]{"Class", s.getClassId()};
            data[4] = new String[]{"Major", s.getMajor()};
            data[5] = new String[]{"Date of Enrollment", s.getEnrollDate()};
            data[6] = new String[]{"Date of Graduation", s.getGradDate()};
            data[7] = new String[]{"School", s.getAffiliation()};
        }
        return data;
    }
}
