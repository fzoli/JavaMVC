package fzoli.mvc_test;

import fzoli.mvc.Sender;
import fzoli.mvc.control.MyController;
import fzoli.mvc.control.MyControllerUtils;
import fzoli.mvc.control.MyDialogController;
import fzoli.mvc_test.id.Keys;
import fzoli.mvc_test.id.Messages;
import javax.swing.JOptionPane;

public class HelloDialogController extends MyDialogController {
    
    public HelloDialogController(MyController owner) {
        super(owner);
    }

    @Override
    protected HelloDialog createDialog() {
        return new HelloDialog(this);
    }

    private void updateMainMessage() {
        sendMessageToOwner(Messages.SET_MESSAGE_TEXT, getTxt());
    }
    
    private void updatePreviousMessage(Object object) {
        updateModel(Keys.MESSAGE_KEY, "Előző üzenet: " + object);
    }

    @Override
    protected void dispose() {
        if (!getTxt().isEmpty()) {
            updateMainMessage();
            super.dispose();
        }
        else {
            showMessage("Az üzenet nem lehet üres!");
        }
    }
    
    private void showMessage(String msg) {
        JOptionPane.showMessageDialog(getDialog(), msg);
    }
    
    private String getTxt() {
        return getModel().getString(Keys.TEXT_KEY);
    }
    
    @Override
    public void receiveMessage(Sender sender, int messageCode, Object args) {
        switch(messageCode) {
            case Messages.SET_PREVIOUS_MSG_TEXT:
                updatePreviousMessage(args);
                break;
            case MyControllerUtils.MSG_EXIT:
                dispose();
                break;
            default:
                super.receiveMessage(sender, messageCode, args);
        }
    }
    
}