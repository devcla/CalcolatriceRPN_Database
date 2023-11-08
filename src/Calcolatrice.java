import javax.swing.*;
import java.awt.*;

public class Calcolatrice {
    private JPanel Panel;
    private JPanel displayContainer;
    private JLabel display;
    private JButton btnSette;
    private JButton btnOtto;
    private JButton btnNove;
    private JButton btnDiviso;
    private JButton btnQuattro;
    private JButton btnCinque;
    private JButton btnSei;
    private JButton btnPer;
    private JButton btnMeno;
    private JButton btnTre;
    private JButton btnDue;
    private JButton btnUno;
    private JPanel buttonContainer;
    private JButton btnPiu;
    private JButton btnUguale;
    private JButton btnPunto;
    private JButton btnZero;
    private JButton btnAperta;
    private JButton btnChiusa;
    private JCheckBox checkBox1;
    private JButton btnC;
    private JButton btnAc;
    private JPanel spaceContainer;
    private JButton btnSpace;

    public Calcolatrice() {
        if(!checkBox1.isSelected()) {
            btnSpace.setVisible(false);
            spaceContainer.setVisible(false);
        }
        checkBox1.addActionListener(e -> {
            if (!checkBox1.isSelected()) {
                btnSpace.setVisible(false);
                spaceContainer.setVisible(false);
            } else if(checkBox1.isSelected()) {
                btnSpace.setVisible(true);
                spaceContainer.setVisible(true);
            }
        });
        btnZero.addActionListener(e -> display.setText(display.getText() + "0"));
        btnUno.addActionListener(e -> display.setText(display.getText() + "1"));
        btnDue.addActionListener(e -> display.setText(display.getText() + "2"));
        btnTre.addActionListener(e -> display.setText(display.getText() + "3"));
        btnQuattro.addActionListener(e -> display.setText(display.getText() + "4"));
        btnCinque.addActionListener(e -> display.setText(display.getText() + "5"));
        btnSei.addActionListener(e -> display.setText(display.getText() + "6"));
        btnSette.addActionListener(e -> display.setText(display.getText() + "7"));
        btnOtto.addActionListener(e -> display.setText(display.getText() + "8"));
        btnNove.addActionListener(e -> display.setText(display.getText() + "9"));
        btnPunto.addActionListener(e -> display.setText(display.getText() + "."));
        btnPiu.addActionListener(e -> display.setText(display.getText() + " + "));
        btnMeno.addActionListener(e -> display.setText(display.getText() + " - "));
        btnPer.addActionListener(e -> display.setText(display.getText() + " * "));
        btnDiviso.addActionListener(e -> display.setText(display.getText() + " / "));
        btnAperta.addActionListener(e -> display.setText(display.getText() + " ( "));
        btnChiusa.addActionListener(e -> display.setText(display.getText() + " ) "));
        btnAc.addActionListener(e -> display.setText(""));
        btnSpace.addActionListener(e -> display.setText(display.getText() + " "));
        btnC.addActionListener(e -> {
            String text = display.getText();
            if (!text.isEmpty()) {
                display.setText(text.substring(0, text.length() - 1));
            }
        });
        btnUguale.addActionListener(e -> {
            if(display.getText().isEmpty()) {
                return;
            }
            boolean isRPN = checkBox1.isSelected();
            String text = display.getText();
            try {
                display.setText(CalcolatriceRPN.calcola(isRPN, text));
            } catch (ArithmeticException a) {
                display.setText(a.getMessage());
            }
        });
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calcolatrice");
        frame.setContentPane(new Calcolatrice().Panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setMinimumSize(new Dimension(650, 500));
        frame.setVisible(true);
    }

}
