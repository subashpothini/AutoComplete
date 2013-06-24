package ru.mai.autocomplete;

import java.util.ArrayList;
import java.util.List;

public class StringSpliterImpl implements StringSpliter {
    private final String delimiters;

    public StringSpliterImpl(String delimiters) {
        this.delimiters = delimiters;
    }

    @Override
    public List<String> split(String text) {
        List<String> result = new ArrayList<>();
        StringBuilder token = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (delimiters.indexOf(c) != -1) {
                if (token.length() > 0) {
                    result.add(token.toString());
                    token = new StringBuilder();
                }
            } else {
                token.append(c);
            }
        }

        if (token.length() > 0)
            result.add(token.toString());

        return result;
    }
}
