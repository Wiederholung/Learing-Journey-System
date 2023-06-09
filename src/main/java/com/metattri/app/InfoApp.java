package com.metattri.app;

import com.metattri.dao.DB;
import com.metattri.entity.Student;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * The InfoApp class represents an interface that displays and allows modification of a student's basic information.
 * The interface includes a table with the student's information and buttons for returning and modifying the data.
 */
public class InfoApp extends JFrame {
    String[][] stuInfo;

    /**
     * Constructs a new instance of the InfoApp class.
     *
     * @param studentID the ID of the student
     */
    public InfoApp(String studentID) {
        // Set the title
        JLabel titleLabel = new JLabel("Your Status Information", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(new EmptyBorder(10, 0, 10, 0));

        // Create the forms
        stuInfo = loadStudentData(studentID);
        String[] COLUMN_NAMES = {"Info", "Value"};
        JTable table = new JTable(stuInfo, COLUMN_NAMES);
        table.setEnabled(true);
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

        // Create the returnButton and the modifyButton
        JButton returnButton = new JButton("Return");
        JButton modifyButton = new JButton("Modify");
        returnButton.addActionListener(e -> {
            dispose();
            new WelcomeApp(studentID);
        });
        modifyButton.addActionListener(e -> {
            for (int i = 0; i < stuInfo.length; i++) {
                stuInfo[i][1] = table.getValueAt(i, 1).toString();
            }
            writeStudentData(studentID, stuInfo);

            dispose();
            new InfoApp(studentID);
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(returnButton);
        buttonPanel.add(modifyButton);
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPanel.add(titleLabel, BorderLayout.NORTH);
        contentPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        setContentPane(contentPanel);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        setTitle("Your Status Information");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Loads the student data into a two-dimensional array.
     *
     * @param studentID the ID of the student
     * @return an array that saves the basic information of the student
     */
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

    /**
     * Writes the student data to the JSON file.
     *
     * @param studentID the ID of the student, used to locate the JSON object
     * @param data      the array of information to be written
     */
    private void writeStudentData(String studentID, String[][] data) {
        Student s = DB.getStudent(studentID);
        if (s != null && s.getSid().equals(studentID)) {
            for (String[] datum : data) {
                if (datum[1].equals("")) {
                    JOptionPane.showMessageDialog(null, "Value cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }
        if (s != null) {
            s.setName(data[0][1]);
            s.setGender(data[1][1]);
            s.setClassId(data[3][1]);
            s.setMajor(data[4][1]);
            s.setEnrollDate(data[5][1]);
            s.setGradDate(data[6][1]);
            s.setAffiliation(data[7][1]);
        }

        DB.writeToJson(DB.updateStudent(s));
        JOptionPane.showMessageDialog(null, "Modify Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}

