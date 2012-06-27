package fzoli.mvc_test;

import fzoli.mvc.Sender;
import fzoli.mvc.control.MyControllerUtils;
import fzoli.mvc.control.MyFrameController;
import fzoli.mvc.model.MyWindowModel;
import fzoli.mvc.view.MyFrame;
import fzoli.mvc_test.id.Keys;
import fzoli.mvc_test.id.Messages;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MainFrame extends MyFrame {

    private JLabel lbMsg;
    private JButton btOk;
    
    public MainFrame(MyFrameController controller) {
        super(controller);
    }

    @Override
    protected void initFrame() {
        setTitle("Főablak");
        setLayout(new GridLayout(2, 1));
        setSize(400, 120);
        lbMsg = new JLabel();
        lbMsg.setHorizontalAlignment(SwingConstants.CENTER);
        add(lbMsg);
        btOk = new JButton("Üzen");
        add(btOk);
        btOk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                onButtonClick();
            }
            
        });
        MyControllerUtils.addExitListener(this);
        super.initFrame();
    }
    
    private void onButtonClick() {
        sendMessage(Messages.BUTTON_CLICK_EVENT);
    }
    
    @Override
    public MyWindowModel getModel() {
        MyWindowModel model = new MyWindowModel(this);
        model.put(Keys.MESSAGE_KEY, lbMsg.getText());
        return model;
    }

    @Override
    public void setModel(MyWindowModel model) {
        lbMsg.setText(model.getString(Keys.MESSAGE_KEY));
    }

    private void setButtonDisabled() {
        btOk.setEnabled(false);
    }
    
    @Override
    public void receiveMessage(Sender sender, int messageCode, Object args) {
        switch (messageCode) {
            case Messages.SET_BUTTON_DISABLED:
                setButtonDisabled();
                break;
            default:
                super.receiveMessage(sender, messageCode, args);
        }
    }
    
}