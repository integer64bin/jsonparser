package jsonparser.ast;

public class TextValue implements Value {

    private String text;

    public TextValue(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    @Override
    public Object asObject() {
        return text;
    }
}
