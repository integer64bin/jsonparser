package option;

import java.util.HashMap;
import java.util.Map;

public class Options {

    private Map<String,Object> options;

    public Options(){
        options = new HashMap<>();
    }

    public void add(String name, Object value){
        options.put(name,value);
    }

    public Object get(String name){
        if(options.containsKey(name))
            return options.get(name);
        return null;
    }

    public Options sub(String name){
        if(get(name) != null && get(name) instanceof Options)
            return (Options) get(name);
        throw new RuntimeException("Value " + get(name).toString() + "is not option");
    }

    public boolean containsKey(String name){
        return options.containsKey(name);
    }

    public void remove(String name){
        if(options.containsKey(name))
            options.remove(name);
    }

    @Override
    public String toString() {
        return options.toString();
    }
}
