package com.metattri.app;

import com.metattri.dao.DB;
import com.metattri.entity.Student;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The main application window for displaying student honors.
 */
public class HonorApp extends JFrame {
    private final ArrayList<String[]> honors;

    /**
     * Constructs the HonorApp window.
     *
     * @param studentID The ID of the student.
     */
    public HonorApp(String studentID) {
        this.honors = new ArrayList<>();
        Student s = DB.getStudent(studentID);
        if (s != null) {
            List<Student.Honor> honor = s.getHonors();
            for (Student.Honor h : honor) {
                String[] s_honor = new String[2];
                s_honor[0] = h.getHonor_name();
                s_honor[1] = h.getHonor_time();
                honors.add(s_honor);
            }
        }
        //create basic panel and buttons
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        JButton backButton = new JButton("Back");
        JButton addButton = new JButton("Add New");
        JPanel projectPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(backButton);
        buttonPanel.add(addButton);
        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        projectPanel.setLayout(new BoxLayout(projectPanel, BoxLayout.Y_AXIS));

        backButton.addActionListener(e -> {
            dispose();
            new WelcomeApp(studentID);
        });

        addButton.addActionListener(e -> {
            dispose();
            new HonorForm(studentID);
        });


        //Create the button of every honors
        for (String[] project : honors) {

            JButton projectButton = new JButton(project[0] + " " + project[1]);
            projectButton.setPreferredSize(new Dimension(550, 50));
            projectButton.setMaximumSize(new Dimension(Short.MAX_VALUE, 50));
            projectButton.setAlignmentX(Component.CENTER_ALIGNMENT);


            projectButton.addActionListener(e -> {
                //Show the information
                JPanel projectDetailPanel = new JPanel();
                JLabel informationLabel = new JLabel("<HTML><body style='text-align: center;'><h3>Honor Name:</h3>" + project[0] +
                        "<br><h3>Honor Time:</h3>" + project[1] +
                        "<br></body></html");
                projectDetailPanel.setLayout(new BoxLayout(projectDetailPanel, BoxLayout.Y_AXIS));
                projectDetailPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
                informationLabel.setHorizontalAlignment(SwingConstants.CENTER);
                informationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                informationLabel.setMaximumSize(new Dimension(300, Integer.MAX_VALUE));
                projectDetailPanel.add(informationLabel);


                // Create back button and delete button
                JPanel infoButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                JButton returnButton = new JButton("Back");
                JButton deleteButton = new JButton("Delete");
                returnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                returnButton.setPreferredSize(new Dimension(100, 50));
                deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                deleteButton.setPreferredSize(new Dimension(100, 50));

                returnButton.addActionListener(e1 -> {
                    mainPanel.setVisible(true);
                    projectDetailPanel.setVisible(false);
                });
                deleteButton.addActionListener(e12 -> {
                    int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this honor?", "Delete honor", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        DB.writeToJson(DB.deleteHonor(project[0], project[1]));
                        new HonorApp(studentID);
                        dispose();
                    }
                });


                mainPanel.setVisible(false);
                projectDetailPanel.setVisible(true);
                projectDetailPanel.removeAll();
                projectDetailPanel.setLayout(new BoxLayout(projectDetailPanel, BoxLayout.Y_AXIS));
                projectDetailPanel.add(Box.createVerticalGlue());
                projectDetailPanel.add(Box.createVerticalGlue());
                projectDetailPanel.add(informationLabel);
                infoButtonPanel.add(returnButton);
                projectDetailPanel.add(Box.createVerticalGlue());
                infoButtonPanel.add(deleteButton);
                projectDetailPanel.add(Box.createVerticalGlue());
                projectDetailPanel.add(infoButtonPanel);
                getContentPane().add(projectDetailPanel, BorderLayout.CENTER);
            });

            projectPanel.add(projectButton);
        }

        mainPanel.add(projectPanel, BorderLayout.CENTER);
        getContentPane().add(mainPanel);
        setTitle("Student Honors");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * The form for adding a new honor.
     */
    public static class HonorForm extends JFrame {

        private final JTextField projectTimeField;
        private final JTextArea projectContentArea;

        /**
         * Constructs the HonorForm window.
         *
         * @param studentID The ID of the student.
         */
        public HonorForm(final String studentID) {
            super("Adding your honor here");

            //set the titles
            JLabel titleLabel = new JLabel("Adding your honor experience here");
            titleLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
            titleLabel.setHorizontalAlignment(JLabel.CENTER);

            // create the input box
            projectTimeField = new JTextField(20);
            projectTimeField.setHorizontalAlignment(JTextField.CENTER);
            projectTimeField.setPreferredSize(new Dimension(200, 30));

            projectContentArea = new JTextArea(5, 20);
            projectContentArea.setLineWrap(true);
            projectContentArea.setWrapStyleWord(true);
            JScrollPane scrollPane = new JScrollPane(projectContentArea);

            JButton submitButton = new JButton("Submit");
            submitButton.setPreferredSize(new Dimension(200, 30));

            // Create the back Button
            JButton backButton = new JButton("Back");
            backButton.setPreferredSize(new Dimension(200, 30));
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
            panel.add(new JLabel("Honor Time:"), gbc);
            gbc.gridx = 1;
            gbc.gridy = 1;
            panel.add(projectTimeField, gbc);
            gbc.gridx = 0;
            gbc.gridy = 2;
            panel.add(new JLabel("Honor Content:"), gbc);
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

            //Add the honors into JSON
            submitButton.addActionListener(e -> {
                String honorTime = projectTimeField.getText();
                String honorContent = projectContentArea.getText();
                if (honorTime.isEmpty() || honorContent.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                    return;
                }
                Student s = DB.getStudent(studentID);
                List<Student.Honor> s_honor;
                if (s != null) {
                    s_honor = s.getHonors();
                    s_honor.add(new Student.Honor(honorTime, honorContent));
                    s.setHonors(s_honor);
                    DB.writeToJson(DB.updateStudent(s));
                    projectTimeField.setText("");
                    projectContentArea.setText("");
                    JOptionPane.showMessageDialog(null, "Honor submitted successfully!");
                }
            });
            backButton.addActionListener(e -> {
                dispose();
                new HonorApp(studentID);
            });

            add(panel);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        }
    }
}
