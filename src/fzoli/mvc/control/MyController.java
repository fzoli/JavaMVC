package fzoli.mvc.control;

import fzoli.mvc.Sender;
import fzoli.mvc.model.MyWindowModel;
import fzoli.mvc.view.MyWindow;
import java.awt.Window;

public abstract class MyController implements Sender {
    
    private final MyController OWNER;
    private final MyWindow WINDOW;
    
    public MyController(MyController owner) {
        super();
        this.OWNER = owner;
        this.WINDOW = createWindow();
        initWindow();
    }
    
    protected void initWindow() {
        new Thread() { //modális dialógus blokkol mindent míg be nem zárják, ennek kivédésére külön száll

            @Override
            public void run() {
                super.run();
                getWindow().setVisible(true);
            }
            
        }.start();
    }
    
    protected abstract MyWindow createWindow();
    
    private boolean isRoot() {
        return this.OWNER == null;
    }
    
    protected final MyController getOwner() {
        return isRoot() ? this : this.OWNER;
    }
    
    public final Window getOwnerWindow() {
        return getOwner().getWindow();
    }
    
    protected final Window getWindow() {
        return (Window) WINDOW;
    }
    
    private MyWindow getMyWindow() {
        return (MyWindow) getWindow();
    }
    
    protected final MyWindowModel getModel() {
        return getMyWindow().getModel();
    }
    
    protected final void setModel(MyWindowModel model) {
        getMyWindow().setModel(model);
    }
    
    protected final void updateModel(Integer key, Object object) {
        getModel().updateKey(key, object);
    }
    
    protected void sendMessageToWindow(int messageCode, Object args) {
        getMyWindow().receiveMessage(this, messageCode, args);
    }
    
    protected final void sendMessageToWindow(int messageCode) {
        sendMessageToWindow(messageCode, null);
    }
    
    private void sendMessageToOwner(Sender sender, int messageCode, Object args) {
        if (!isRoot()) getOwner().receiveMessage(sender, messageCode, args);
    }
    
    protected final void sendMessageToOwner(int messageCode) {
        sendMessageToOwner(messageCode, null);
    }
    
    protected final void sendMessageToOwner(int messageCode, Object args) {
        sendMessageToOwner(this, messageCode, args);
    }
    
    protected final void sendMessage(Sender controller, int messageCode) {
        sendMessage(controller, messageCode, null);
    }
    
    protected final void sendMessage(Sender controller, int messageCode, Object args) {
        controller.receiveMessage(this, messageCode, args);
    }
    
    public void receiveMessage(Sender sender, int messageCode, Object args) {
        sendMessageToOwner(sender, messageCode, args); //továbbpasszolás
    }
    
    protected void dispose() {
        getWindow().dispose();
    }
    
}