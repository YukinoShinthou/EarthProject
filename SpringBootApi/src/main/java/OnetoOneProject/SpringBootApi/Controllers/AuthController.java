package OnetoOneProject.SpringBootApi.Controllers;

import OnetoOneProject.SpringBootApi.Models.Person;
import OnetoOneProject.SpringBootApi.Models.UserData;
import OnetoOneProject.SpringBootApi.Services.AuthenticationService;
import OnetoOneProject.SpringBootApi.Services.PeopleServices;
import OnetoOneProject.SpringBootApi.Services.UserDataServices;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController implements WebMvcConfigurer {

    private final AuthenticationService authenticationService ;
    private final PeopleServices peopleServices;
    private final UserDataServices userDataServices;

    @Autowired
    public AuthController(AuthenticationService authenticationService, PeopleServices peopleServices,UserDataServices userDataServices) {
        this.authenticationService = authenticationService;
        this.peopleServices = peopleServices;
        this.userDataServices = userDataServices;
    }

    //log in
    @PostMapping( value = "/Login")
    public Map<String, Object> login(@RequestBody @Valid UserData userData){
        //check username if exists then password then log in
        if(authenticationService.checkUsername(userData.getUsername())){
            if(authenticationService.checkPassword(userData.getUsername(), userData.getPassword())){
                Map<String, Object> jsonDataLoginInfo = new HashMap<>();
                jsonDataLoginInfo.put("Status", "Access");
                jsonDataLoginInfo.put("Person", peopleServices.findPersonByUserId(authenticationService.getUser(userData.getUsername(), userData.getPassword()).getId()));
                return jsonDataLoginInfo;
            }else {
                Map<String, Object> jsonDataLoginInfo = new HashMap<>();
                jsonDataLoginInfo.put("Status", "Reject");
                jsonDataLoginInfo.put("Error", "Incorrect Password");
                return jsonDataLoginInfo;
            }
        }
        else {
            Map<String, Object> jsonDataLoginInfo = new HashMap<>();
            jsonDataLoginInfo.put("Status", "Reject");
            jsonDataLoginInfo.put("Error", "Incorrect Username");
            return jsonDataLoginInfo;
        }

    }

    //registration
    @PostMapping( "/Registration")
    public Map<String,Object> registration(@RequestBody @Valid UserData newUser){
        //check username if doesn't exist then password and save the user and return the id of the new created user
        if(authenticationService.checkUsername(newUser.getUsername())){
            Map<String, Object> jsonDataLoginInfo = new HashMap<>();
            jsonDataLoginInfo.put("Status", "Reject");
            jsonDataLoginInfo.put("Error", "Username already exists");
            return jsonDataLoginInfo;
        }else {
            Map<String, Object> jsonDataLoginInfo = new HashMap<>();
            jsonDataLoginInfo.put("Status", "Access");
            return jsonDataLoginInfo;
        }
    }

    @PostMapping( "/Registration/User")
    public Map<String,Object> registrationUser(@RequestBody @Valid UserData newUser){
        //create new user
            Map<String, Object> jsonDataLoginInfo = new HashMap<>();
            jsonDataLoginInfo.put("Status", "Created");
            jsonDataLoginInfo.put("User", userDataServices.saveUserData(newUser));
            return jsonDataLoginInfo;

    }

    @PostMapping("/Registration/person")
    public Map<String, Object> registrationPerson(@RequestBody @Valid Person person, BindingResult bindingResult){
        //get the id of new user and crate it's new person and save and return this person

        if(bindingResult.hasErrors()) {
            StringBuilder ErrorMessage = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            Map<String, Object> jsonObjectInfo = new HashMap<>();
            jsonObjectInfo.put("Status","Reject");
            for (FieldError error : errors) {
                jsonObjectInfo.put(error.getField(),error.getDefaultMessage());
                ErrorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";");
            }

            jsonObjectInfo.put("Error",ErrorMessage);
            return jsonObjectInfo;
        }

        Map<String, Object> jsonObjectInfo = new HashMap<>();
        jsonObjectInfo.put("Status","Created");
        jsonObjectInfo.put("Person", peopleServices.createPerson(person));
        return jsonObjectInfo;
    }


























//    //registration
//    @PostMapping( "/Registration")
//    public Map<String,Object> registration(@RequestBody @Valid UserData newUser){
//        //check username if doesn't exist then password and save the user and return the id of the new created user
//        if(authenticationService.checkUsername(newUser.getUsername())){
//            Map<String, Object> jsonDataLoginInfo = new HashMap<>();
//            jsonDataLoginInfo.put("Status", "Reject");
//            jsonDataLoginInfo.put("Error", "Username already exists");
//            return jsonDataLoginInfo;
//        }else {
//            Map<String, Object> jsonDataLoginInfo = new HashMap<>();
//            jsonDataLoginInfo.put("Status", "Created");
//            jsonDataLoginInfo.put("UserId", userDataServices.saveUserData(newUser).getId());
//            return jsonDataLoginInfo;
//        }
//    }
//
//    @PostMapping("/Registration/person")
//    public Map<String, Object> registration(@RequestBody @Valid Person person, BindingResult bindingResult){
//        //get the id of new user and crate it's new person and save and return this person
//
//        if(bindingResult.hasErrors()) {
//            StringBuilder ErrorMessage = new StringBuilder();
//
//            List<FieldError> errors = bindingResult.getFieldErrors();
//            Map<String, Object> jsonObjectInfo = new HashMap<>();
//            jsonObjectInfo.put("Status","Reject");
//            for (FieldError error : errors) {
//                jsonObjectInfo.put(error.getField(),error.getDefaultMessage());
//                ErrorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";");
//            }
//
////            jsonObjectInfo.put("Error",ErrorMessage);
//            return jsonObjectInfo;
//        }
//        Map<String, Object> jsonObjectInfo = new HashMap<>();
//        peopleServices.savePerson(person);
//        jsonObjectInfo.put("Status","Created");
//        jsonObjectInfo.put("Person", person);
//        return jsonObjectInfo;
//    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*");
    }
}
