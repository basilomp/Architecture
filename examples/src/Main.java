import builder.Person;

import java.util.Iterator;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Person person = Person.createBuilder()
                .withId(1L)
                .withFirstName("Ivan")
                .withLastName("Ivanov")
                .withGender("M")
                .build();

//      Iterator example
        Iterable<Integer> random = new Iterable() {
            @Override
            public Iterator iterator() {
                return new Iterator() {

                    private Random random = new Random();
                    private int count = 0;

                    @Override
                    public boolean hasNext() {
                        return count < 10;
                    }

                    @Override
                    public Object next() {
                        int val = random.nextInt();
                        count++;
                        return val;
                    }
                };
            }
        };

        for (Integer val: random) {
            System.out.println(val);
        }
    }
}