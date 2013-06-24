package ru.mai.autocomplete;

import java.util.ArrayList;
import java.util.List;

public class StringSpliterImpl implements StringSpliter {
    @Override
    public List<String> split(String text, String delimiters) {
        List<String> result = new ArrayList<>();
        StringBuilder token = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            boolean last = ((i + 1) == text.length());

            if (last || delimiters.indexOf(c) != -1) {
                if (token.length() > 0) {
                    result.add(token.toString());
                    token = new StringBuilder();
                }
            } else {
                token.append(c);
            }
        }

        return result;
    }
}
