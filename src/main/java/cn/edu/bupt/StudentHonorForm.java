/**
 * This class is used to add the student honors
 */
package cn.edu.bupt;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StudentHonorForm extends JFrame {

    private final JLabel titleLabel;
    private final JTextField projectTimeField;
    private final JTextArea projectContentArea;
    private final JButton submitButton;
    private final JButton backButton;

    public StudentHonorForm(final String studentID) {
        super("Adding your honor here");

        //set the titles
        titleLabel = new JLabel("Adding your honor experience here");
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

        submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(200, 30));

        // Create the back Button
        backButton = new JButton("Back");
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
            List<Honor> s_honor;
            if (s != null) {
                s_honor = s.getHonors();
                s_honor.add(new Honor(honorTime, honorContent));
                s.setHonors(s_honor);
                DB.writeToJson(DB.updateStudent(s));
                projectTimeField.setText("");
                projectContentArea.setText("");
                JOptionPane.showMessageDialog(null, "Honor submitted successfully!");
            }
        });
        backButton.addActionListener(e -> {
            dispose();
            new StudentHonorApp(studentID);
        });

        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}