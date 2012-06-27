package fzoli.mvc_test;

import fzoli.mvc.control.MyControllerUtils;
import fzoli.mvc.control.MyDialogController;
import fzoli.mvc.model.MyWindowModel;
import fzoli.mvc.view.MyDialog;
import fzoli.mvc_test.id.Keys;
import fzoli.mvc_test.id.Messages;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class HelloDialog extends MyDialog {

    private JTextField tfTxt;
    private JLabel lbMsg;
    
    public HelloDialog(MyDialogController controller) {
        super(controller);
    }

    @Override
    protected void initDialog() {
        setTitle("Helló dialógus");
        setLayout(new GridLayout(2, 1));
        setSize(220, 100);
        lbMsg = new JLabel();
        lbMsg.setHorizontalAlignment(SwingConstants.CENTER);
        add(lbMsg);
        tfTxt = new JTextField();
        add(tfTxt);
        MyControllerUtils.addExitListener(this);
        super.initDialog();
    }
    
    @Override
    public MyWindowModel getModel() {
        MyWindowModel model = new MyWindowModel(this);
        model.put(Keys.TEXT_KEY, tfTxt.getText());
        model.put(Keys.MESSAGE_KEY, lbMsg.getText());
        return model;
    }

    @Override
    public void setModel(MyWindowModel model) {
        tfTxt.setText(model.getString(Keys.TEXT_KEY));
        lbMsg.setText(model.getString(Keys.MESSAGE_KEY));
    }

    @Override
    public void dispose() {
        sendMessage(Messages.DIALOG_DISPOSED);
        super.dispose();
    }
    
}