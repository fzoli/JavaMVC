package fzoli.mvc.view;

import fzoli.mvc.Sender;
import fzoli.mvc.control.MyController;
import fzoli.mvc.model.MyWindowModel;

public interface MyWindow extends Sender {
    
    public MyController getController();
    
    public MyWindowModel getModel();
    
    public void setModel(MyWindowModel model);
    
}