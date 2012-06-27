package fzoli.mvc_test;

import fzoli.mvc.Sender;
import fzoli.mvc.control.MyControllerUtils;
import fzoli.mvc.control.MyFrameController;
import fzoli.mvc_test.id.Keys;
import fzoli.mvc_test.id.Messages;
import javax.swing.UIManager;

public class MainController extends MyFrameController {

    @Override
    protected MainFrame createFrame() {
        setSystemLookAndFeel();
        return new MainFrame(this);
    }

    private void setSystemLookAndFeel() {
        try {
            UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName()
            );
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    protected void initFrame() {
        updateModel(Keys.MESSAGE_KEY, "Nincs üzenet.");
        super.initFrame();
    }

    private void createHelloDialog() {
        HelloDialogController c = new HelloDialogController(this);
        sendMessage(c, Messages.SET_PREVIOUS_MSG_TEXT, getModel().getString(Keys.MESSAGE_KEY));
    }
    
    private void updateMessage(Object object) {
        updateModel(Keys.MESSAGE_KEY, object);
    }
    
    private void setButtonDisabled() {
        sendMessageToWindow(Messages.SET_BUTTON_DISABLED);
    }
    
    @Override
    public void receiveMessage(Sender sender, int messageCode, Object args) {
        switch(messageCode) {
            case Messages.DIALOG_DISPOSED:
                setButtonDisabled();
                break;
            case Messages.SET_MESSAGE_TEXT:
                updateMessage(args);
                break;
            case Messages.BUTTON_CLICK_EVENT:
                createHelloDialog();
                break;
            case MyControllerUtils.MSG_EXIT:
                dispose();
                break;
            default:
                super.receiveMessage(sender, messageCode, args);
        }
    }
    
    public static void main(String[] args) {
        new MainController();
    }
    
}