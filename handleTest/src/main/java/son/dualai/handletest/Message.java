package son.dualai.handletest;

public class Message {
    Handler target;

    public Object obj;

    public int what;

    public Message(){

    }

    @Override
    public String toString() {
        return "Message{" +
                "obj=" + obj +
                '}';
    }
}
