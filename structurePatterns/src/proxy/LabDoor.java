package proxy;

public class LabDoor implements Door{

    @Override
    public void open() {
        System.out.println("Open lab door");
    }

    @Override
    public void open(String password) {

    }

    @Override
    public void close() {
        System.out.println("Closing the lab door");
    }
}
