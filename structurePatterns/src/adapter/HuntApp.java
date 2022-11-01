package adapter;

public class HuntApp {
    public static void main(String[] args) {
        WildDog dog = new WildDog();
        WildDogAdapter adapter = new WildDogAdapter(dog);

        Hunter hunter = new Hunter();
        hunter.hunt(adapter);
    }
}
