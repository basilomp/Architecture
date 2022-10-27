package proxy;

public class SecuredDoor implements Door{
    protected Door door;

    public SecuredDoor(Door door) {
        this.door = door;
    }

    @Override
    public void open() {

    }

    public void open(String password) {
        if(this.authenticate(password)) {
            this.door.open();
        } else {
            System.out.println("Big no! It ain't possible!");
        }
    }

    @Override
    public void close() {
        this.door.close();
    }

    public boolean authenticate(String password) {
        return password == "$ecr@t";
    }
}
