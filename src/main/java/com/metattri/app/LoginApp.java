package com.metattri.app;

import com.metattri.dao.DB;
import com.metattri.entity.Student;

import javax.swing.*;
import java.awt.*;

/**
 * The LoginApp class represents the login interface of the system.
 * It provides a graphical user interface (GUI) for users to enter their student ID and password.
 * Upon successful login, the user is redirected to the WelcomeApp.
 */
public class LoginApp extends JFrame {
    private JTextField idTextField;
    private JPasswordField passwordField;

    /**
     * Initializes the LoginApp by setting up the look and feel, creating the title panel, and login panel.
     * Sets the size, location, and visibility of the LoginApp window.
     */
    public LoginApp() {
        initializeLookAndFeel();
        createTitlePanel();
        createLoginPanel();
        setSize(600, 300);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    /**
     * Initializes the look and feel of the application to Nimbus.
     */
    private void initializeLookAndFeel() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    /**
     * Creates the title panel with the application title and subtitle.
     */
    private void createTitlePanel() {
        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("    Learning Journey Application for International School    ");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel subTitleLabel = new JLabel("QMUL & BUPT");
        subTitleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        subTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(titleLabel, BorderLayout.NORTH);
        titlePanel.add(subTitleLabel, BorderLayout.CENTER);
        add(titlePanel, BorderLayout.NORTH);
    }

    /**
     * Creates the login panel with input fields for student ID and password, as well as a login button.
     */
    private void createLoginPanel() {
        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel idLabel = new JLabel("Student ID:");
        idLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        loginPanel.add(idLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        idTextField = new JTextField(10);
        idTextField.setFont(new Font("Arial", Font.PLAIN, 14));
        loginPanel.add(idTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        loginPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        passwordField = new JPasswordField(10);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        loginPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.PLAIN, 14));
        loginPanel.add(loginButton, gbc);

        loginButton.addActionListener(e -> {
            String studentID = idTextField.getText();
            String password = new String(passwordField.getPassword
                    ());
            boolean loginSuccess = false;
            Student s = DB.getStudent(studentID);
            if (s != null && s.getPassword().equals(password)) {
                loginSuccess = true;
            }
            if (loginSuccess) {
                new WelcomeApp(studentID);
                dispose();
            } else {
                JOptionPane.showMessageDialog(LoginApp.this, "Invalid StudentID or Password!", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(loginPanel, BorderLayout.CENTER);
    }
}
