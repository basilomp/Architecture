package assignment_1.messages;

public class MessagerImpl implements Messager{
    @Override
    public void print(String message) {
        System.out.println(message);
    }

    public static MessagerImpl create() {
        return new MessagerImpl();
    }
}
