import java.util.OptionalInt;

public class Person {
    protected final String name;
    protected final String surname;
    protected String address;
    protected int age;

    public Person(PersonBuilder personBuilder) {
        this.name = personBuilder.name;
        this.surname = personBuilder.surname;
        this.address = personBuilder.adress;
        this.age = personBuilder.age;
    }

    public PersonBuilder newChildBuilder() {
        return new PersonBuilder().setSurname(this.surname).setAddress(this.address).setAge(0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name + " " + surname);
        if (hasAge()) {
            sb.append(" ").append(age).append(" лет");
        }
        if (hasAddress()) {
            sb.append(" ").append(address);
        }
        return sb.toString();
    }

    public boolean hasAge() {
        return this.age >= 0;
    }

    public boolean hasAddress() {
        return address != null && !address.isEmpty();
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public OptionalInt getAge() {
        if (hasAge()) {
            return OptionalInt.of(age);
        } else {
            return OptionalInt.empty();
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void happyBirthday() throws BadAgeException {
        if (/*OptionalInt.of(age).isPresent()*/ age != -1) {
            this.age++;
        } else {
            throw new BadAgeException();
        }
    }
}