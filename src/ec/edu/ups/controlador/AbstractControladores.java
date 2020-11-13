package ec.edu.ups.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author tano
 */
public abstract class AbstractControladores<T> {
    private List<T> list;

    public AbstractControladores() {
        list = new ArrayList<>();
    }
    
    public boolean create(T object){
        return list.add(object);
    }
    
    public Optional<T> read(T object){
        return list.stream().filter(ob -> ob.equals(object)).findFirst();
    }
    
    public boolean update(T old, T nw){
        if(old != null){
            list.set(list.indexOf(old), nw);
            return true;
        }
        return false;
    }
    
    public boolean remove(T object){
        if(object != null){
            list.remove(object);
            return true;
        }
        return false;
    }

    public List<T> getList() {
        return list;
    }
    
    
}
