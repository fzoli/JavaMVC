package fzoli.mvc.control;

import fzoli.mvc.view.MyDialog;
import fzoli.mvc.view.MyWindow;

public abstract class MyDialogController extends MyController {

    public MyDialogController() {
        this(null);
    }
    
    public MyDialogController(MyController owner) {
        super(owner);
    }

    public final MyDialog getDialog() {
        return (MyDialog) getWindow();
    }

    protected void initDialog() {
        ;
    }

    @Override
    protected final void initWindow() {
        initDialog();
        super.initWindow();
    }
    
    @Override
    protected final MyWindow createWindow() {
        return createDialog();
    }
    
    protected abstract MyDialog createDialog();
    
}