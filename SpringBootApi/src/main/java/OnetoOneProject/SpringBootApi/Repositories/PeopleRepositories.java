package OnetoOneProject.SpringBootApi.Repositories;

import OnetoOneProject.SpringBootApi.Models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepositories extends JpaRepository<Person,Integer> {
    public Person findById(int id);
    public Person findByUserId(int id);
}
