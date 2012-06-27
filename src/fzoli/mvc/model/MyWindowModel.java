package fzoli.mvc.model;

import fzoli.mvc.view.MyWindow;
import java.util.HashMap;

public final class MyWindowModel extends HashMap<Integer, Object> {

    private final MyWindow OWNER;
    
    public MyWindowModel(MyWindow owner) {
        super();
        this.OWNER = owner;
    }
    
    public void update() {
        OWNER.setModel(this);
    }
    
    public void updateKey(Integer key, Object object) {
        put(key, object);
        update();
    }
    
    public String getString(Integer key) {
        return (String) get(key);
    }

    public Boolean getBoolean(Integer key) {
        return (Boolean) get(key);
    }
    
}