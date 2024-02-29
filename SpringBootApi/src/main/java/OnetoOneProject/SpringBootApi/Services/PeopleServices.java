package OnetoOneProject.SpringBootApi.Services;

import OnetoOneProject.SpringBootApi.Models.Person;
import OnetoOneProject.SpringBootApi.Repositories.PeopleRepositories;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PeopleServices {

    private final PeopleRepositories peopleRepositories;
    private final UserDataServices userDataServices;

    @Autowired
    public PeopleServices(PeopleRepositories peopleRepositories, UserDataServices userDataServices) {
        this.peopleRepositories = peopleRepositories;
        this.userDataServices = userDataServices;
    }
    @Transactional
    public void savePerson(Person person){
        person.setUserData(userDataServices.foundUser(person.getUserData().getId()));
    }
    @Transactional
    public Person createPerson(Person person){
        return peopleRepositories.save(person);
    }

    @Transactional
    public Person findPerson(int id){
        Optional<Person> foundPerson = Optional.ofNullable(peopleRepositories.findById(id));
        return foundPerson.orElse(null);
    }
    //get person by user id
    @Transactional
    public Person findPersonByUserId(int id){
        return peopleRepositories.findByUserId(id);
    }
}
