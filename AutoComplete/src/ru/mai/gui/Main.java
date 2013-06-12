package ru.mai.gui;

import javax.swing.*;

public class Main {

    private JCheckBox checkBox1;
    private JButton button1;
    private JPanel Test;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main");
        frame.setContentPane(new Main().Test);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
