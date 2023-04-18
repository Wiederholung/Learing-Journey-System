package cn.edu.bupt;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StudentInfoApp extends JFrame {
    private final String CSV_FILE = "./src/StudentInfo.csv";
    private final String[] COLUMN_NAMES = {"信息", "数值"};
    private String studentID;

    public StudentInfoApp(String studentID) {

        this.studentID = studentID;
        // 设置窗口标题和大小
        setTitle("Your Status Information");
        setSize(500, 400);

        // 创建顶部标题
        JLabel titleLabel = new JLabel("Your Status Information", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(new EmptyBorder(10, 0, 10, 0));

        // 创建表格
        String[][] data = loadStudentData();
        JTable table = new JTable(data, COLUMN_NAMES);
        table.setEnabled(false);

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

        // 添加返回按钮到底部
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(returnButton);


        // 将组件添加到内容面板中
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.add(titleLabel, BorderLayout.NORTH);
        contentPane.add(new JScrollPane(table), BorderLayout.CENTER);
        setContentPane(contentPane);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }




    private String[][] loadStudentData() {
        String[][] data = new String[9][2];

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 2 && fields[3].equals(studentID)) {
//                    data[0] = new String[] {"Password", fields[0].replaceAll("\\(.*\\)", "")};
                    data[1] = new String[] {"Name", fields[1]};
                    data[2] = new String[] {"Gender", fields[2]};
                    data[3] = new String[] {"StudentID", fields[3]};
                    data[4] = new String[] {"Class", fields[4]};
                    data[5] = new String[] {"Major", fields[5]};
                    data[6] = new String[] {"Date of Enrollment", fields[6]};
                    data[7] = new String[] {"Date of Graduation", fields[7]};
                    data[8] = new String[] {"School", fields[8]};
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    public static void main(String[] args) {
        // 在事件分派线程中启动GUI
        SwingUtilities.invokeLater(() -> {
             String ID="2020213362";
            StudentInfoApp app = new StudentInfoApp(ID);
            app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            app.setVisible(true);
        });
    }
}