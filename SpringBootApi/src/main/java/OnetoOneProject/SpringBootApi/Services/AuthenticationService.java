package OnetoOneProject.SpringBootApi.Services;

import OnetoOneProject.SpringBootApi.Models.UserData;
import OnetoOneProject.SpringBootApi.Repositories.PeopleRepositories;
import OnetoOneProject.SpringBootApi.Repositories.UserDataRepositories;
import OnetoOneProject.SpringBootApi.Util.UserNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class AuthenticationService {
    private final PeopleRepositories peopleRepositories;
    private final UserDataRepositories userDataRepositories;

    @Autowired
    public AuthenticationService(PeopleRepositories peopleRepositories, UserDataRepositories userDataRepositories) {
        this.peopleRepositories = peopleRepositories;
        this.userDataRepositories = userDataRepositories;
    }

    //check username
    @Transactional
    public Boolean checkUsername(String username){
        Optional<UserData> foundUsername = Optional.ofNullable(userDataRepositories.findByUsername(username));
        return foundUsername.isPresent();
    }
    //check if usernames' password is correct
    @Transactional
    public Boolean checkPassword(String username,String password){
        UserData user = userDataRepositories.findByUsername(username);
        return user.getPassword().equals(password);
    }
    //get user
    @Transactional
    public UserData getUser(String username,String password){
        return userDataRepositories.findByUsernameAndPassword(username,password);
    }

    //save user and return id of user
    @Transactional
    public Integer saveUser(UserData newUser){
        userDataRepositories.save(newUser);
        return newUser.getId();
    }

}
