package jsonparser.ast;

public class BoolValue implements Value {

    private boolean value;

    public BoolValue(String text) {
        this.value = Boolean.parseBoolean(text);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public Object asObject() {
        return value;
    }
}
