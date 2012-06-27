package fzoli.mvc;

public interface Sender {
    
    void receiveMessage(Sender sender, int messageCode, Object args);
    
}