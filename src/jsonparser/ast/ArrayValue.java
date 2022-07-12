package jsonparser.ast;

import java.util.ArrayList;
import java.util.List;

public class ArrayValue implements Value{

    private List<Value> values;

    public ArrayValue(){
        values = new ArrayList<>();
    }

    public void add(Value value){
        values.add(value);
    }

    public Value get(int index){
        return values.get(index);
    }

    public List<Value> values() {
        return values;
    }

    @Override
    public String toString(){
        return values.toString();
    }

    @Override
    public Object asObject() {
        return values;
    }
}
