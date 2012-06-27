package fzoli.mvc.control;

import fzoli.mvc.view.MyFrame;
import fzoli.mvc.view.MyWindow;

public abstract class MyFrameController extends MyController {

    public MyFrameController() {
        this(null);
    }
    
    public MyFrameController(MyController owner) {
        super(owner);
    }

    public final MyFrame getFrame() {
        return (MyFrame) getWindow();
    }

    protected void initFrame() {
        ;
    }

    @Override
    protected final void initWindow() {
        initFrame();
        super.initWindow();
    }
    
    @Override
    protected final MyWindow createWindow() {
        return createFrame();
    }
    
    protected abstract MyFrame createFrame();
    
}