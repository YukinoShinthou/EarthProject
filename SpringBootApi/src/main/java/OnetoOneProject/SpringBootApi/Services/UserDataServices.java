package OnetoOneProject.SpringBootApi.Services;

import OnetoOneProject.SpringBootApi.Models.UserData;
import OnetoOneProject.SpringBootApi.Repositories.UserDataRepositories;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDataServices {

    private final UserDataRepositories userDataRepositories;


    @Autowired
    public UserDataServices(UserDataRepositories userDataRepositories) {
        this.userDataRepositories = userDataRepositories;

    }

    @Transactional
    public UserData saveUserData(UserData newUser){
        newUser.setRole("Consumer");
        newUser.setTimestamp();
        userDataRepositories.save(newUser);
        return newUser;
    }
    @Transactional
    public UserData foundUser(int id){
        Optional<UserData> user = userDataRepositories.findById(id);
        return user.orElse(null);
    }


}
