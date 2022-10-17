package decorator;

public class MilkCoffee implements Coffee{

    protected Coffee coffee;

    public MilkCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public int getCost() {
        return this.coffee.getCost() + 2;
    }

    @Override
    public String getDescription() {
        return this.coffee.getDescription() + ", milk";
    }

    @Override
    public void print() {
        System.out.printf("%s: %d\n", this.getDescription(), this.getCost());
    }
}
