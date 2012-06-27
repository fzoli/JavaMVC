package fzoli.mvc.control;

import fzoli.mvc.view.MyWindow;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public final class MyControllerUtils {
    
    public static final int MSG_EXIT = 0;
    
    public static void addExitListener(final MyWindow window) {
        ((Window) window).addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                sendMessage(window, MSG_EXIT);
            }
            
        });
    }
    
    public static void sendMessage(MyWindow sender, int messageCode) {
        sendMessage(sender, messageCode, null);
    }
    
    public static void sendMessage(MyWindow sender, int messageCode, Object args) {
        sender.getController().receiveMessage(sender, messageCode, args);
    }
    
}