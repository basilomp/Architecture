package decorator;

public class VanillaCoffee implements Coffee{
    protected Coffee coffee;

    public VanillaCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public int getCost() {
        return this.coffee.getCost() + 3;
    }

    @Override
    public String getDescription() {
        return this.coffee.getDescription() + ", vanilla";
    }

    @Override
    public void print() {
        System.out.printf("%s: %d\n", this.getDescription(), this.getCost());
    }
}
