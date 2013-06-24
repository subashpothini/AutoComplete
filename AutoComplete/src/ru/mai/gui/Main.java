package ru.mai.gui;

import ru.mai.autocomplete.AutoComplete;
import ru.mai.autocomplete.AutoCompleteImpl;
import ru.mai.autocomplete.Result;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class Main {

    private JPanel Test;
    private JTextField input;
    private JTextArea output;
    private AutoComplete<String> autocomplete;

    public Main(String resourceFileName) {
        initAutoComplete(resourceFileName);
        setListeners();
    }

    public static void main(String[] args) {
        String resourceFileName = (args.length > 0 ? args[0] : "");

        JFrame frame = new JFrame("Main");
        frame.setContentPane(new Main(resourceFileName).Test);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void initAutoComplete(String resourceFileName) {
        autocomplete = new AutoCompleteImpl<>();
        loadResources(resourceFileName);
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
                    output.append(s.getText() + "\n");
                    output.append(s.getObject() + "\n");
                }
            }
        });
    }

    private void loadResources(String resourceFileName) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(resourceFileName));
            String text;

            while ((text = in.readLine()) != null) {
                String url = in.readLine();
                autocomplete.addObject(text, url);
            }
        } catch (Exception e) {
            // nothing
        }
    }
}
