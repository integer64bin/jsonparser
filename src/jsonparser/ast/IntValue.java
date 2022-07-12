package jsonparser.ast;

public class IntValue implements Value {

    private long value;

    public IntValue(String value) {
        this.value = Long.parseLong(value);
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
