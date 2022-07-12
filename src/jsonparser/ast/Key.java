package jsonparser.ast;

public class Key {

    private String name;

    private Value value;

    public Key(String name, Value value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("%s:%s", name,value.toString());
    }

}
