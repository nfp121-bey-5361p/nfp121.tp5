package question2;
import java.util.Stack;


/**
 * Classe gradien de la liste memento
 *
 */
public class Caretaker {
    private Stack<JPanelListe2.Memento> mementoStk = new Stack<>();
   
    public JPanelListe2.Memento getMemento() {
        if (mementoStk.isEmpty())
            return null;
        return mementoStk.pop();
    }
    
    public void setMemento(JPanelListe2.Memento memento) {
        mementoStk.push(memento);
    }
}
