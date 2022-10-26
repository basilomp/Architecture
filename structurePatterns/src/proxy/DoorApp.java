package proxy;

public class DoorApp {
    public static void main(String[] args) {
        Door door = new SecuredDoor(new LabDoor());
        door.open("invalid");

        door.open("$ecr@t");
        door.close();
    }
}
