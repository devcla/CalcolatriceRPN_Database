package CalcolatriceRpnDatabase;

import javax.swing.*;
import java.awt.*;

public class RegisterForm {
    private JPanel Register;
    private JLabel title;
    private JLabel lblUsername;
    private JTextField txtUsername;
    private JTextField txtPassword;
    private JLabel lblPassword;
    private JButton btnRegistrati;
    private DB database;

    public RegisterForm() {
        JFrame frame = new JFrame("RegisterForm");
        frame.setContentPane(Register);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setMinimumSize(new Dimension(650, 500));
        frame.setVisible(true);

        btnRegistrati.addActionListener(e -> {
            String username = new String(txtUsername.getText());
            String password = new String(txtPassword.getText());
            if(!username.isEmpty() || !password.isEmpty()) {
                database = new DB();
                if(database.register(username, password) == 1) {
                    new Calcolatrice(username);
                    JFrame frame2 = (JFrame) SwingUtilities.getWindowAncestor(Register);
                    frame2.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Username già esistente", "Errore", JOptionPane.ERROR_MESSAGE);
                    txtUsername.setText("");
                    txtPassword.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Inserisci username e password", "Errore", JOptionPane.ERROR_MESSAGE);
                txtUsername.setText("");
                txtPassword.setText("");
            }
        });
    }
}
