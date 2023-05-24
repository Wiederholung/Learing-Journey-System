/**
 * this class is used to show all the information of project and make some operation on it
 */
package cn.edu.bupt.app;

import cn.edu.bupt.dao.DB;
import cn.edu.bupt.dao.Student;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProjApp extends JFrame {

    public ProjApp(String studentID) {
        ArrayList<String[]> projects = new ArrayList<>();
        Student s = DB.getStudent(studentID);
        if (s != null) {
            List<Student.Project> projs = s.getProjs();
            for (Student.Project p : projs) {
                String[] project = new String[3];
                project[0] = p.getProj_name();
                project[1] = p.getProj_time();
                project[2] = p.getDescribe();
                projects.add(project);
            }
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Student Projects");
        setSize(600, 400);
        setLocationRelativeTo(null);


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Create add and back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            dispose();
            new WelcomeApp(studentID);
        });
        JButton addButton = new JButton("Add New");
        addButton.addActionListener(e -> {
            dispose();
            new ProjForm(studentID);
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(backButton);
        buttonPanel.add(addButton);
        mainPanel.add(buttonPanel, BorderLayout.NORTH);


        JPanel projectPanel = new JPanel();
        projectPanel.setLayout(new BoxLayout(projectPanel, BoxLayout.Y_AXIS));

        // Create buttons to every project
        for (String[] project : projects) {

            JButton projectButton = new JButton(project[1] + " " + project[2]);
            projectButton.setPreferredSize(new Dimension(550, 50));
            projectButton.setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
            projectButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            projectButton.addActionListener(e -> {
                // Create new panel for project
                JPanel projectDetailPanel = new JPanel();
                projectDetailPanel.setLayout(new BoxLayout(projectDetailPanel, BoxLayout.Y_AXIS));
                projectDetailPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

                //add information to project
                JLabel informationLabel = new JLabel("<HTML><body style='text-align: center;'" +
                        "<h3>Project Name:</h3>" +project[0]+
                        "<br><h3>Project Time:</h3>"+project[1] +
                        "<br><h3>Project Description</h3>"+project[2] +
                        "<br></body></html");
                informationLabel.setHorizontalAlignment(SwingConstants.CENTER);
                informationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                informationLabel.setMaximumSize(new Dimension(300,Integer.MAX_VALUE));
                projectDetailPanel.add(informationLabel);


                // add back button
                JPanel infoButtonPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
                JButton backButton1 = new JButton("Back");
                backButton1.addActionListener(e1 -> {
                    mainPanel.setVisible(true);
                    projectDetailPanel.setVisible(false);
                });
                backButton1.setAlignmentX(Component.CENTER_ALIGNMENT);
                backButton1.setPreferredSize(new Dimension(100,50));
                infoButtonPanel.add(backButton1);

                // add delete button

                JButton deleteButton = new JButton("Delete");
                deleteButton.addActionListener(e12 -> {
                    int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this project?", "Delete Project", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        System.out.println("success");
                       DB.writeToJson( DB.deleteProject(project[0], project[1]));
                        new ProjApp(studentID);
                        dispose();
                    }
                });
                deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                deleteButton.setPreferredSize(new Dimension(100,50));
                infoButtonPanel.add(deleteButton);
                projectDetailPanel.add(infoButtonPanel);


                mainPanel.setVisible(false);
                projectDetailPanel.setVisible(true);
                getContentPane().add(projectDetailPanel, BorderLayout.CENTER);
            });

            projectPanel.add(projectButton);
        }
        mainPanel.add(projectPanel, BorderLayout.CENTER);
        getContentPane().add(mainPanel);
        setVisible(true);
    }


    public static class ProjForm extends JFrame {

        private final JTextField projectNameField;
        private final JTextField projectTimeField;
        private final JTextArea projectContentArea;

        public ProjForm(final String studentID) {
            super("Adding your project experience here");

            // Set the title
            JLabel titleLabel = new JLabel("Adding your project experience here");
            titleLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
            titleLabel.setHorizontalAlignment(JLabel.CENTER);
            /*
              set the field of name,time and content
             */
            projectNameField = new JTextField(20);
            projectNameField.setHorizontalAlignment(JTextField.CENTER);
            projectNameField.setPreferredSize(new Dimension(200, 30));

            projectTimeField = new JTextField(20);
            projectTimeField.setHorizontalAlignment(JTextField.CENTER);
            projectTimeField.setPreferredSize(new Dimension(200, 30));

            projectContentArea = new JTextArea(5, 20);
            projectContentArea.setLineWrap(true);
            projectContentArea.setWrapStyleWord(true);
            JScrollPane scrollPane = new JScrollPane(projectContentArea);

            // create submit and back button
            JButton submitButton = new JButton("Submit");
            submitButton.setPreferredSize(new Dimension(200, 30));

            JButton backButton = new JButton("Back");
            backButton.setPreferredSize(new Dimension(200, 30));

            // create the panel and add into the frame
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

            submitButton.addActionListener(e -> {
                String projectName = projectNameField.getText();
                String projectTime = projectTimeField.getText();
                String projectContent = projectContentArea.getText();

                // write the information into the JSON file
                Student s = DB.getStudent(studentID);
                List<Student.Project> projs;
                if (s != null) {
                    projs = s.getProjs();
                    if(projectName.equals("") || projectTime.equals("") || projectContent.equals("")){
                        JOptionPane.showMessageDialog(null, "Please fill in all fields!");
                        return;
                    }
                    projs.add(new Student.Project(projectName, projectTime, projectContent));
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
                new ProjApp(studentID);
            });


            add(panel);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }

}
