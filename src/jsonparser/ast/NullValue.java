package jsonparser.ast;

public class NullValue implements Value {

    @Override
    public String toString() {
        return "null";
    }

    @Override
    public Object asObject() {
        return null;
    }
}
