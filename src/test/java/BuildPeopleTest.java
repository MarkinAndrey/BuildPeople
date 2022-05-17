import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BuildPeopleTest {

    @org.junit.jupiter.api.Test
    void newChildBuilder_addressTrue() {
        Person person;
        person = new PersonBuilder().setName("name").setSurname("surname").setAge(20).setAddress("address").build();
        Person son = person.newChildBuilder()
                .setName("Антошка")
                .build();
        assert (person.getSurname().equals(son.surname)
                && person.getAddress().equals(son.getAddress())
                && son.age == 0);
    }

    @org.junit.jupiter.api.Test
    void newChildBuilder_addressFalse() {
        Person person;
        person = new PersonBuilder().setName("name").setSurname("surname").setAge(20).build();
        Person son = person.newChildBuilder()
                .setName("Антошка")
                .build();
        assert (person.getSurname().equals(son.surname) && son.age == 0);
    }

    @ParameterizedTest
    @CsvSource({"20",
            "-1"})
    void happyBirthday(int age) throws BadAgeException {
        Person person;
        if (age >= 0) {
            person = new PersonBuilder().setName("name").setSurname("surname").setAge(age).build();
        } else {
            person = new PersonBuilder().setName("name").setSurname("surname").build();
        }

        if (person.age != -1) {
            person.happyBirthday();
            assertEquals(age + 1, person.age);
        } else {
            assertThrows(BadAgeException.class, () -> new PersonBuilder().setName("name").setSurname("surname").build().happyBirthday());
        }
    }

    @ParameterizedTest
    @CsvSource({"'name', 'surname', 20, 'address'",
            "'name', 'surname', 0, 'address'",
            "'name', 'surname', -20, 'address'"})
    void setAge(String name,
                String surname,
                int age,
                String address) throws BadAgeException {
        switch (age) {
            case 20:
                assertEquals(20, new PersonBuilder().setName(name).setSurname(surname).setAddress(address).setAge(age).age);
                break;
            case -0:
                assertEquals(0, new PersonBuilder().setName(name).setSurname(surname).setAge(age).age);
                break;
            case -20:
                assertThrows(BadAgeException.class, () -> new PersonBuilder().setName(name).setSurname(surname).setAge(age));
            default:
                break;
        }
    }
}