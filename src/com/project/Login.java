package com.project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;


public class Login extends JFrame{
    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton btnNewButton;
    private JLabel label;
    private JPanel contentPane;
public Login(){
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(280, 120, 1014, 597);
    setResizable(false);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    JLabel lblNewLabel = new JLabel("Login");
    lblNewLabel.setForeground(Color.BLACK);
    lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 26));
    lblNewLabel.setBounds(423, 13, 128, 40);
    contentPane.add(lblNewLabel);

    textField = new JTextField();
    textField.setFont(new Font("Times New Roman", Font.PLAIN, 16));
    textField.setBounds(481, 170, 228, 40);
    contentPane.add(textField);
    textField.setColumns(10);

    passwordField = new JPasswordField();
    passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
    passwordField.setBounds(481, 286, 228, 40);
    contentPane.add(passwordField);

    JLabel lblUsername = new JLabel("Username");
    lblUsername.setBackground(Color.BLACK);
    lblUsername.setForeground(Color.BLACK);
    lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 14));
    lblUsername.setBounds(250, 166, 128, 40);
    contentPane.add(lblUsername);

    JLabel lblPassword = new JLabel("Password");
    lblPassword.setForeground(Color.BLACK);
    lblPassword.setBackground(Color.CYAN);
    lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 14));
    lblPassword.setBounds(250, 286, 128, 40);
    contentPane.add(lblPassword);

    btnNewButton = new JButton("Login");
    btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 13));
    btnNewButton.setBounds(545, 392, 178, 40);
    btnNewButton.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            String userName = textField.getText();
            String password = passwordField.getText();
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project",
                        "teddy1", "PASSWORD@strong#5590");

                PreparedStatement connectionString = connection
                        .prepareStatement("Select name, password from admin where name=? and password=?");
                connectionString.setString(1, userName);
                connectionString.setString(2, password);
                ResultSet resutltstring = connectionString.executeQuery();
                if (resutltstring.next()) {
                    dispose();
                    HomePage homePage = new HomePage();
                    homePage.setTitle("Welcome home admin");
                    homePage.setVisible(true);
                    JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged in");
                } else {
                    JOptionPane.showMessageDialog(btnNewButton, "incorrect user name and password");
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    });

    contentPane.add(btnNewButton);

    label = new JLabel("");
    label.setBounds(0, 0, 1008, 562);
    contentPane.add(label);
}
}
