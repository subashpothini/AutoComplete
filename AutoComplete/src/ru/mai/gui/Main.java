package ru.mai.gui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Main {

    private JCheckBox checkBox1;
    private JButton button1;
    private JPanel Test;
    private JTextField input;
    private JTextArea output;

    public Main() {
        input.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                action();
            }

            public void removeUpdate(DocumentEvent e) {
                action();
            }

            public void insertUpdate(DocumentEvent e) {
                action();
            }

            public void action() {
                output.setText("");
                String text = input.getText();
                //output.setText(text);
                int n = 5;
                for (int i = 0; i < n; ++i) {
                    output.append(text + "\n");
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main");
        frame.setContentPane(new Main().Test);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
