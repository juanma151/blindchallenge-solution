package mysolution;

import blch.framework.annotations.Solution;
import blch.framework.external.SolutionBase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Solution
public class MySolution2 extends SolutionBase {
    
    private Object[] pop(Object... args) {
        return Arrays.copyOfRange(args,1,args.length);
    }

    @Override
    public Object execute(Object... args) {        
        if (args.length == 0) {
            throw new RuntimeException("No arguments");
        }

        String theCmd = (String) args[0];
        Object[] rest = pop(args);
            
        return switch (theCmd) {
            case "COUNT" -> execEntity("COUNT", rest);
            case "PUT" -> execEntity("PUT", rest);
            default -> throw new RuntimeException("UNKOWN COMMAND");
        };
    }
    
    private Object execEntity(String Op, Object... args) {
        if (args.length==0) {
            throw new RuntimeException("Missing entity");
        }
        
        String entity = (String) args[0];
        Object[] rest = pop(args);
        Map<String,Object> map = this.getMap(entity);
        
        return switch(Op) {
            case "COUNT" -> execCount(entity,map,rest);
            case "PUT" -> execPut(entity,map,rest);
            default -> throw new RuntimeException("Unknown operation");
        };
    }
    
    private Object execCount (String entity, Map<String,Object> map, Object... args) {
        return map.size();
    }
    
    private Object execPut (String entity, Map<String,Object> map, Object... args) {
        if (args.length == 0) {
            throw new RuntimeException("Missing parameters");
        }
        
        Iterator<MyAtt> it  = this.getObj(entity).iterator();
        Map<String,Object> value = new HashMap<>();
       
        for (Object param : args) {
            if (it.hasNext()) {
                MyAtt att = it.next();
                value.put(att.name(),param);
            }
            else {
                throw new RuntimeException("There are more atts than needed");
            }
        }
        
        //map.put()
        
        return null;
    }
    
    
    private Object execPutPerson (Map<String,Object> map, Object... args) {
        //String[] properties
        return null;
    }
    
    //private Object execPutPerson
    
    /*
    private Object execPut (Object... args) {
        if (args.length == 0) {
            throw new RuntimeException("Missing entity");
        }
        else {
            return 0;
        }
    }
*/
    
    private Map<String,Map<String,Object>> myMap = new HashMap();
    private Map<String,MyObj> entities;
    
    private void addEntity(String entity, Object... args) {
        this.myMap.put(entity, new HashMap<>());
        
        MyObj obj = new MyObj(entity);
        
        for (int i=0; i<args.length; i+=2) {
            obj.push(new MyAtt((String)args[i],(Class<?>)args[i+1]));
        }
    }
    
    private void initMap() {
        this.myMap = new HashMap<>();
        
        this.addEntity("person","name",String.class);
        //this.addEntity("");
        //this.addEntity("");
        //this.addEntity("");
    }
    
    private Map<String,Object> getMap(String entity) {
        if (this.myMap.containsKey(entity)) {
            return this.myMap.get(entity);
        }
        else {
            throw new RuntimeException("Unknow entity");
        }
    }
    
    private MyObj getObj(String entity) {
        return this.entities.get(entity);
    }
}

record MyAtt (String name, Class<?> type) {}

class MyObj implements Iterable<MyAtt> {
    
    private String entity;
    private List<MyAtt> atts = new ArrayList<>();
    
    public MyObj(String entity) {
        this.entity = entity;
    }
    
    public void push(MyAtt att) {
        atts.add(att);
    }

    @Override
    public Iterator<MyAtt> iterator() {
        return this.atts.iterator();
    }
}