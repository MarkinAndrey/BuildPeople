public class PersonBuilder {
    protected String name;
    protected String surname;
    protected String adress;
    protected int age = -1;

    public PersonBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PersonBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public PersonBuilder setAge(int age) throws BadAgeException {
        if (age >= 0) {
            this.age = age;
        } else {
            throw new BadAgeException();
        }
        return this;
    }

    public PersonBuilder setAddress(String address) {
        this.adress = address;
        return this;
    }

    public Person build() {
        if (this.name == null || this.surname == null) {
            throw new IllegalStateException();
        }
        return new Person(this);
    }

}