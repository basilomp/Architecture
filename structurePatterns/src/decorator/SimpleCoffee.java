package decorator;

public class SimpleCoffee implements Coffee{


    @Override
    public int getCost() {
        return 10;
    }

    @Override
    public String getDescription() {
        return "Simple coffee";
    }

    @Override
    public void print() {
        System.out.printf("%s: %d\n", this.getDescription(), this.getCost());
    }
}
