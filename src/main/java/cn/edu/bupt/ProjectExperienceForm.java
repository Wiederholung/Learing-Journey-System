import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class ProjectExperienceForm extends JFrame{

    private JLabel titleLabel;
    private JTextField projectNameField, projectTimeField;
    private JTextArea projectContentArea;
    private JButton submitButton, backButton;
    private String studentID;

    public ProjectExperienceForm(final String studentID) {

        super("Adding your project experience here");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.studentID=studentID;
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


        // 将面板添加到窗口上
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // 清空所有输入框内容
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
// 获取输入框中的信息
                String projectName = projectNameField.getText();
                String projectTime = projectTimeField.getText();
                String projectContent = projectContentArea.getText();
// 从StudentInfo.csv中获取学生姓名和学号
                String studentName = "";
                String student_ID = " ";


                try {
                    File file = new File("./src/StudentInfo.csv");
                    Scanner scanner = new Scanner(file);
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        String[] fields = line.split(",");
                        if (fields[3].equals(studentID)) {
                            studentName = fields[1];
                            student_ID = fields[3];
                            break;
                        }
                    }
                    scanner.close();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }

                // 将项目信息写入StudentProject.csv中
                try {
                    FileWriter writer = new FileWriter("./src/StudentProject.csv", true);
                    writer.write(studentName + "," + student_ID + "," + projectName + "," + projectTime + "," + projectContent + "\n");
                    writer.close();
                    JOptionPane.showMessageDialog(null, "Project submitted successfully!");
                    // 清空所有输入框内容
                    projectNameField.setText("");
                    projectTimeField.setText("");
                    projectContentArea.setText("");
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Failed to submit project.");
                }
            }

        });
        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new StudentProjectApp(studentID);

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
        new ProjectExperienceForm(ID);
    }
}