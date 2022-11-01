package facade;

public class ComputerApp {
    public static void main(String[] args) {
        ComputerFacade facade = new ComputerFacade(new Computer());
        facade.turnOn();
        facade.turnOff();
    }
}
