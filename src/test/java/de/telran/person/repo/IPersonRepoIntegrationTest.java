package de.telran.person.repo;

import de.telran.person.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Collection;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IPersonRepoIntegrationTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    IPersonRepo personRepo;

    @Test
    public void testFindAllByName_onePerson_existing() {
        //creating a person for this test
        Person person = new Person("Vasily");
        person.setAge(20);
        person.setSecondName("Vasin");
        // adding this person to our test DB
        entityManager.persist(person);
        entityManager.flush();
        // making sure Persistence Context is clear
        entityManager.clear();

        Collection<Person> personsFromDB = personRepo.findAllByName("Vasily");
        assertEquals(1, personsFromDB.size());

        Iterator<Person> iterator = personsFromDB.iterator();
        Person personFromDB = iterator.next();

        assertEquals(person.getName(), personFromDB.getName());
        assertEquals(person.getAge(), personFromDB.getAge());
        assertEquals(person.getSecondName(), personFromDB.getSecondName());
    }

    @Test
    public void testFindAllByName_onePerson_nonExisting(){
        Person person = new Person("Vasily");
        person.setAge(20);
        person.setSecondName("Vasin");

        entityManager.persist(person);
        entityManager.flush();
        entityManager.clear();

        Collection<Person> personsFromDB = personRepo.findAllByName("Petr");
        assertEquals(0, personsFromDB.size());
    }
}