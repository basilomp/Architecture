package decorator;

public class WhipCoffee implements Coffee{

    protected Coffee coffee;

    public WhipCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public int getCost() {
        return this.coffee.getCost() + 5;
    }

    @Override
    public String getDescription() {
        return this.coffee.getDescription() + ", whip";
    }

    @Override
    public void print() {
        System.out.printf("%s: %d\n", this.getDescription(), this.getCost());
    }
}
