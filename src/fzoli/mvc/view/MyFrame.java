package fzoli.mvc.view;

import fzoli.mvc.Sender;
import fzoli.mvc.control.MyControllerUtils;
import fzoli.mvc.control.MyFrameController;
import javax.swing.JFrame;

public abstract class MyFrame extends JFrame implements MyWindow {

    private final MyFrameController CONTROLLER;
    
    public MyFrame(MyFrameController controller) {
        super();
        this.CONTROLLER = controller;
        initFrame();
    }
    
    protected void initFrame() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(getController().getOwnerWindow());
    }

    @Override
    public final MyFrameController getController() {
        return this.CONTROLLER;
    }
    
    protected final void sendMessage(int messageCode) {
        MyControllerUtils.sendMessage(this, messageCode);
    }
    
    protected final void sendMessage(int messageCode, Object args) {
        MyControllerUtils.sendMessage(this, messageCode, args);
    }
    
    public void receiveMessage(Sender sender, int messageCode, Object args) {
        ;
    }
    
}