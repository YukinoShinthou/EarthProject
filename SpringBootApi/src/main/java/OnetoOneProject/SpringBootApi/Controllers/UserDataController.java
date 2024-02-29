package OnetoOneProject.SpringBootApi.Controllers;

import OnetoOneProject.SpringBootApi.Models.Person;
import OnetoOneProject.SpringBootApi.Models.UserData;
import OnetoOneProject.SpringBootApi.Services.AuthenticationService;
import OnetoOneProject.SpringBootApi.Services.PeopleServices;
import OnetoOneProject.SpringBootApi.Services.UserDataServices;
import OnetoOneProject.SpringBootApi.Util.PersonDetailsException;
import OnetoOneProject.SpringBootApi.Util.RegistrationExceptionHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("userdata")
public class UserDataController {
    private final UserDataServices userDataServices;

    @Autowired
    public UserDataController(AuthenticationService authenticationService, UserDataServices userDataServices, PeopleServices peopleServices) {
        this.userDataServices = userDataServices;
    }

}
