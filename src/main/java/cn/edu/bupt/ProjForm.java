/**
 * This class is used to add the project experience
 */
package cn.edu.bupt;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProjForm extends JFrame {

    private final JLabel titleLabel;
    private final JTextField projectNameField;
    private final JTextField projectTimeField;
    private final JTextArea projectContentArea;
    private final JButton submitButton;
    private final JButton backButton;

    public ProjForm(final String studentID) {
        super("Adding your project experience here");

        // Set the title
        titleLabel = new JLabel("Adding your project experience here");
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        /**
         * set the field of name,time and content
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
        submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(200, 30));

        backButton = new JButton("Back");
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
            new ProjApp(studentID);
        });


        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}