package CalcolatriceRpnDatabase;

import javax.swing.*;
import java.awt.*;

public class LoginForm {
    private JPanel Login;
    private JLabel title;
    private JTextField txtUsername;
    private JTextField txtPassword;
    private JButton btnAccedi;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JButton btnRegistra;
    private DB database;

    public LoginForm() {
        btnAccedi.addActionListener(e -> {
            String username = new String(txtUsername.getText());
            String password = new String(txtPassword.getText());
            if(!username.isEmpty() || !password.isEmpty()) {
                database = new DB();
                if(database.login(username, password) == 1) {
                    new Calcolatrice(username);
                    JFrame frame2 = (JFrame) SwingUtilities.getWindowAncestor(Login);
                    frame2.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Username o password errati", "Errore", JOptionPane.ERROR_MESSAGE);
                    txtUsername.setText("");
                    txtPassword.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Inserisci username e password", "Errore", JOptionPane.ERROR_MESSAGE);
                txtUsername.setText("");
                txtPassword.setText("");
            }
        });
        btnRegistra.addActionListener(e -> {
            new RegisterForm();
            JFrame frame2 = (JFrame) SwingUtilities.getWindowAncestor(Login);
            frame2.dispose();
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("LoginForm");
        frame.setContentPane(new LoginForm().Login);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setMinimumSize(new Dimension(650, 500));
        frame.setVisible(true);
    }
}
