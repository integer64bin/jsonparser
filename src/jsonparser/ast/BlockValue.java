package jsonparser.ast;

import option.Options;

import java.util.HashMap;
import java.util.Map;

public class BlockValue implements Value {

    private Map<String,Value> values;

    public BlockValue(){
        values = new HashMap<>();
    }

    public void add(Key value){
        if(value==null) return;
        values.put(value.getName(),value.getValue());
    }

    public void add(String name, Value value){
        values.put(name,value);
    }

    public Value get(String key){
        if(values.containsKey(key))
            return values.get(key);
        return null;
    }

    public Options getOptions(){
        Options options = new Options();
        for (String key :
                values.keySet()) {
            options.add(key,values.get(key).asObject());
        }
        return options;
    }

    public boolean containsKey(String key){
        return values.containsKey(key);
    }

    @Override
    public String toString(){
        return values.toString();
    }

    @Override
    public Object asObject() {
        return getOptions();
    }
}
