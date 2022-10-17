package decorator;

import java.util.ArrayList;
import java.util.List;

public class CoffeeApp {
    public static void main(String[] args) {
        List<Coffee> coffeeShop= new ArrayList<>();
        Coffee someCoffee = new SimpleCoffee();
        Coffee anotherCoffee = new MilkCoffee(someCoffee);
        Coffee yetAnotherCoffee = new WhipCoffee(someCoffee);
        Coffee justAnotherCoffee = new VanillaCoffee(someCoffee);

        coffeeShop.add(someCoffee);
        coffeeShop.add(anotherCoffee);
        coffeeShop.add(yetAnotherCoffee);
        coffeeShop.add(justAnotherCoffee);

        coffeeShop.forEach(Coffee::print);


    }
}
