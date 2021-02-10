package geekbrains.authorization;

import geekbrains.database.SQLService;
import geekbrains.settings.Settings;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.sql.SQLException;

/**
 * @author Abubakar Musanipov
 */
public class Login extends JDialog {


    private final JTextField textFieldNickname;
    private final JPasswordField passwordField;
    private final JLabel labelNickname;
    private final JLabel labelPassword;
    private final JButton buttonLogin;
    private final JButton buttonCancel;
    private boolean succeeded;

    public Login(Frame parent) {
        super(parent, Settings.LOGIN_TITLE, true);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        labelNickname = new JLabel("Username: ");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        panel.add(labelNickname, gridBagConstraints);

        textFieldNickname = new JTextField(20);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        panel.add(textFieldNickname, gridBagConstraints);

        labelPassword = new JLabel("Password: ");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        panel.add(labelPassword, gridBagConstraints);

        passwordField = new JPasswordField(20);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        panel.add(passwordField, gridBagConstraints);
        panel.setBorder(new LineBorder(Color.GRAY));

        buttonLogin = new JButton("Login");

        buttonLogin.addActionListener(e -> {
            try {
                if (authenticate(getNickname(), getPassword())) {
                    JOptionPane.showMessageDialog(Login.this, "You have successfully logged in.", "Login", JOptionPane.INFORMATION_MESSAGE);
                    succeeded = true;
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(Login.this, "Invalid username or password", "Login", JOptionPane.ERROR_MESSAGE);
                    textFieldNickname.setText("");
                    passwordField.setText("");
                    succeeded = false;
                }
            } catch (SQLException sqlException) {
                JOptionPane.showMessageDialog(Login.this, sqlException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        buttonCancel = new JButton("Cancel");
        buttonCancel.addActionListener(e -> dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(buttonLogin);
        buttonPanel.add(buttonCancel);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    public boolean authenticate(String getNickname, String password) throws SQLException {
        return SQLService.getNicknameByLoginAndPassword(getNickname, password) != null;
    }

    public String getNickname() {
        return textFieldNickname.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public boolean isSucceeded() {
        return succeeded;
    }
}
