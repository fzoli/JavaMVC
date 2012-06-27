package fzoli.mvc.view;

import fzoli.mvc.Sender;
import fzoli.mvc.control.MyControllerUtils;
import fzoli.mvc.control.MyDialogController;
import javax.swing.JDialog;

public abstract class MyDialog extends JDialog implements MyWindow {

    private final MyDialogController CONTROLLER;
    
    public MyDialog(MyDialogController controller) {
        super(controller.getOwnerWindow());
        this.CONTROLLER = controller;
        initDialog();
    }
    
    protected void initDialog() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(getOwner());
        setModal(true);
    }
    
    @Override
    public final MyDialogController getController() {
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