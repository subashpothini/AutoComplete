package ru.mai.gui;

import ru.mai.autocomplete.AutoComplete;
import ru.mai.autocomplete.AutoCompleteImpl;
import ru.mai.autocomplete.Result;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

public class Main {
    private JPanel Test;
    private JTextField input;
    private JTextArea output;
    private AutoComplete<String> autocomplete;
    private JFrame frame;
    private JMenuBar menuBar;

    public Main() {
        initFrames();
        initMenus();
        initAutoComplete();
        setListeners();
    }

    public static void main(String[] args) {
        new Main();
    }

    private void initFrames() {
        frame = new JFrame("Main");

        // Init frame
        frame.setContentPane(Test);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(new Dimension(800, 600));
    }

    private void initMenus() {
        menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem load = new JMenuItem("Load...");
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser();
                int returnValue = fc.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    loadResources(file);
                }
            }
        });
        menu.add(load);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
    }

    private void initAutoComplete() {
        autocomplete = new AutoCompleteImpl<>();
        //loadResources();
    }

    private void setListeners() {
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
                String text = input.getText();

                List<Result<String>> objects = autocomplete.getObjects(text);
                output.setText("");

                for (Result s : objects) {
                    String resultLine = s.getText() + " ( " + s.getObject() + " ) \n";
                    output.append(resultLine);
                }
            }
        });
    }

    private void clearAll() {
        autocomplete = new AutoCompleteImpl<>();
    }

    private void loadResources(File file) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String text;
            clearAll();

            while ((text = in.readLine()) != null) {
                String url = in.readLine();
                autocomplete.addObject(text, url);
            }
        } catch (Exception e) {
            // nothing
        }
    }
}
