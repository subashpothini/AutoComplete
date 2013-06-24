package ru.mai.autocomplete;

public class Result<K> {
    private final String text;
    private final K object;

    public Result(String text, K object) {
        this.text = text;
        this.object = object;
    }

    public String getText() {
        return text;
    }

    public K getObject() {
        return object;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Result result = (Result) o;

        if (object != null ? !object.equals(result.object) : result.object != null) return false;
        return !(text != null ? !text.equals(result.text) : result.text != null);

    }

    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + (object != null ? object.hashCode() : 0);
        return result;
    }
}
