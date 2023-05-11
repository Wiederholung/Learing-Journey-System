package cn.edu.bupt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentHonorApp extends JFrame {
    private String studentID;
    private ArrayList<String[]> honors;

    public StudentHonorApp(String studentID) {
        this.studentID = studentID;
        this.honors = new ArrayList<>();
//        readProjects();
        Student s = DB.getStudent(studentID);
        if (s != null) {
            List<Honor> honor =s.getHonors();
            // 将projs转为ArrayList<String[]>
            for (Honor h : honor) {
                String[] s_honor = new String[2];
                s_honor[0] = h.getHonor_name();
                s_honor[1] = h.getHonor_time();

                honors.add(s_honor);
            }
        }

        setTitle("Student Honors");
        setSize(600, 400);
        setLocationRelativeTo(null);

        // 创建主面板
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // 创建返回按钮和添加按钮
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LearningJourneyApp(studentID);
            }
        });
        JButton addButton = new JButton("Add New");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new StudentHonorForm(studentID);
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(backButton);
        buttonPanel.add(addButton);
        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        // 创建项目按钮面板
        JPanel projectPanel = new JPanel();
        projectPanel.setLayout(new BoxLayout(projectPanel, BoxLayout.Y_AXIS));

        // 创建每个项目的按钮
        for (String[] project : honors) {

            JButton projectButton = new JButton(project[0] + " " + project[1]);
            projectButton.setPreferredSize(new Dimension(550, 50));
            projectButton.setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
            projectButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            // 创建项目按钮的监听器
            projectButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 创建新的项目详情面板
                    JPanel projectDetailPanel = new JPanel();
                    projectDetailPanel.setLayout(new BoxLayout(projectDetailPanel, BoxLayout.Y_AXIS));

                    // 添加项目详情信息
                    projectDetailPanel.add(new JLabel("Honor Name: " + project[0]));
                    projectDetailPanel.add(new JLabel("Honor Time: " + project[1]));
//                    projectDetailPanel.add(new JLabel("Project Description: " + project[2]));

                    // 添加返回按钮
                    JButton backButton = new JButton("Back");
                    backButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            mainPanel.setVisible(true);
                            projectDetailPanel.setVisible(false);
                        }
                    });
                    projectDetailPanel.add(backButton);

                    JButton deleteButton = new JButton("Delete");
                    deleteButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this honor?", "Delete honor", JOptionPane.YES_NO_OPTION);
                            if (result == JOptionPane.YES_OPTION) {
                                System.out.println("success");
                                DB.deleteHonor(project[0],project[1]);
                                writeProjects();
                                new StudentHonorApp(studentID);
                                dispose();
                            }
                        }
                    });
                    projectDetailPanel.add(deleteButton);
                    // 显示项目详情面板
                    mainPanel.setVisible(false);
                    projectDetailPanel.setVisible(true);
                    getContentPane().add(projectDetailPanel, BorderLayout.CENTER);
                }
            });

            projectPanel.add(projectButton);
        }


        // 添加项目面板到主面板
        mainPanel.add(projectPanel, BorderLayout.CENTER);

        getContentPane().add(mainPanel);
        setVisible(true);
    }

    private void readProjects() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/StudentProject.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                honors.add(parts);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    TODO
    private void writeProjects() {
        try {
            FileWriter writer = new FileWriter("src/main/resources/StudentProject.csv");
            BufferedWriter out = new BufferedWriter(writer);

            for (String[] project : honors) {
                out.write(String.join(",", project));
                out.newLine();
            }

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String ID = "2020213362";
        new StudentHonorApp(ID);
    }
}
