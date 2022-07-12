package jsonparser.ast;

public class FloatValue implements Value {

    private double value;

    public FloatValue(String value) {
        this.value = Double.parseDouble(value);
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
