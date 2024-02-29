package OnetoOneProject.SpringBootApi.Repositories;

import OnetoOneProject.SpringBootApi.Models.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepositories extends JpaRepository<UserData,Integer> {
    public UserData findByUsername(String username);

    public UserData findByPassword(String password);

    public UserData findByUsernameAndPassword(String username, String password);

}
