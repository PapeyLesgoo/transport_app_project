import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


class LoginFrame extends JFrame implements ActionListener {

    Container container = getContentPane();
    JLabel heading = new JLabel("FAST POINT MANAGEMENT");
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    ImageIcon img = new ImageIcon("FastLogo.jpg");
    JLabel fast_logo = new JLabel(img, JLabel.CENTER);
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton resetButton = new JButton("RESET");
    JCheckBox showPassword = new JCheckBox("Show Password");


    //Creating constructor of LoginFrame() class
    LoginFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        this.setTitle("Login Form");
        this.setVisible(true);
        this.setBounds(350, 50, 370, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);


    }

    public void setLayoutManager() {
        //Setting layout manager of Container to null
        container.setLayout(null);
    }


    public void setLocationAndSize() {
        //Setting location and Size of each components using setBounds() method.
        heading.setBounds(110, 0, 250, 200);
        fast_logo.setBounds(0, 0, 100, 100);
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        resetButton.setBounds(200, 300, 100, 30);


    }

    public void addComponentsToContainer() {
        //Adding each components to the Container

        container.setBackground(new Color(0, 160, 200));
        heading.setFont(new Font("Serif", Font.BOLD, 17));
        container.add(fast_logo);
        container.add(heading);
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
    }

    public void addActionEvent() {
        //adding Action listener to components
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
    }

    //Overriding actionPerformed() method
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginButton) {
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = passwordField.getText();
            if (userText.equals("Fast") && pwdText.equals("12345")) {
                //Loads up Menu frame if login is successful and disposes of login frame
                try {
                    MenuFrame frame = new MenuFrame();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                dispose();
                JOptionPane.showMessageDialog(this, "Login Successful");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }

        }

        if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        }

        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }


        }

    }

}
