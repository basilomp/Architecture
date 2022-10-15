import builder.Person;

public class Main {
    public static void main(String[] args) {

        Person person = Person.createBuilder()
                .withId(1L)
                .withFirstName("Ivan")
                .withLastName("Ivanov")
                .withGender("M")
                .build();
    }
}