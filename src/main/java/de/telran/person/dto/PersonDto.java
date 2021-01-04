package de.telran.person.dto;

import de.telran.person.model.Person;

public class PersonDto {

    public int id;
    public String name;
    public String secondName;
    public int age;

    public PersonDto() {
    }

    public PersonDto(int id, String name, String secondName, int age) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.age = age;
    }

    public PersonDto(Person person) {
        id = person.getId();
        name = person.getName();
        secondName = person.getSecondName();
        age = person.getAge();
    }
}
