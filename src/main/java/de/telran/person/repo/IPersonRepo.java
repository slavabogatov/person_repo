package de.telran.person.repo;

import de.telran.person.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

/**
 * An instance of the class that stores Person objects. <Person> needs to have @Entity in its class
 */
public interface IPersonRepo extends CrudRepository<Person, Integer> { //CrudRepo implements the CRUD idea, Spring Data creates beans to use
    Collection<Person> findAll(); //Original findAll returns Iterable, but we override it as it is to broad for us

    // select * from person p where p.name like 'Vasya'
    Collection<Person> findAllByName(String name);

    // select * where age >= x
    Collection<Person> findAllByAgeGreaterThanEqualAndAgeLessThanEqual(int after, int before);

    Collection<Person> findAllByAgeBetween(int after, int before);

//    @Query("select p from Person p where p.age>=?1 and p.age <=?2")
//    Collection<Person> findAllByAgeBeforeTwoValues(int after, int before);

    @Query("select p from Person p where p.age>=:after and p.age <=:before")
    Collection<Person> findAllByAgeBetweenTwoValues(@Param("after") int after, @Param("before") int before);

    // select * where secondName %something
    Collection<Person> findAllBySecondNameIgnoreCaseContaining(String pattern);
}