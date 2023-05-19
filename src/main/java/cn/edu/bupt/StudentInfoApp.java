package cn.edu.bupt;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StudentInfoApp extends JFrame {
    String[][] stuInfo;

    public StudentInfoApp(String studentID) {
        // 创建顶部标题
        JLabel titleLabel = new JLabel("Your Status Information", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(new EmptyBorder(10, 0, 10, 0));

        // 创建表格
        stuInfo = loadStudentData(studentID);
        String[] COLUMN_NAMES = {"Info", "Value"};
        JTable table = new JTable(stuInfo, COLUMN_NAMES);
        table.setEnabled(true);

        // 创建信息面板
        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        for (String[] strings : stuInfo) {
            JLabel label = new JLabel(strings[0] + ":");
            infoPanel.add(label, gbc);
        }
        gbc.gridx = 1;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(5, 5, 5, 5);
        for (String[] strings : stuInfo) {
            JLabel label = new JLabel(strings[1]);
            infoPanel.add(label, gbc);
        }


        // 创建返回按钮
        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(e -> {
            // 关闭当前窗口
            dispose();

            // 创建并显示 LearningJourneyApp 窗口
            new LearningJourneyApp(studentID);
        });

        // 创建修改按钮
        JButton modifyButton = new JButton("Modify");
        modifyButton.addActionListener(e -> {
            // 保存当前表格数据
            for (int i = 0; i < stuInfo.length; i++) {
                stuInfo[i][1] = table.getValueAt(i, 1).toString();
            }
            // 更新数据库
            writeStudentData(studentID, stuInfo);

            // 关闭当前窗口
            dispose();
            // 创建并显示 StudentInfoApp 窗口
            new StudentInfoApp(studentID);
        });

        // 添加返回和修改按钮到底部
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(returnButton);
        buttonPanel.add(modifyButton);

        // 将组件添加到内容面板中
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPanel.add(titleLabel, BorderLayout.NORTH);
        contentPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        setContentPane(contentPanel);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        // 设置窗口标题和大小
        setTitle("Your Status Information");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    private void writeStudentData(String studentID, String[][] data) {
        Student s = DB.getStudent(studentID);
        if (s != null && s.getSid().equals(studentID)) {
            for (int i = 0; i < data.length; i++) {
                if (data[i][1].equals("")) {
                    JOptionPane.showMessageDialog(null, "Value cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }
        s.setName(data[0][1]);
        s.setGender(data[1][1]);
//            s.setSid(stuInfo[2][1]);
        s.setClassId(data[3][1]);
        s.setMajor(data[4][1]);
        s.setEnrollDate(data[5][1]);
        s.setGradDate(data[6][1]);
        s.setAffiliation(data[7][1]);
        DB.writeToJson(DB.updateStudent(s));
        // 提示修改成功
        JOptionPane.showMessageDialog(null, "Modify Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}