package OnetoOneProject.SpringBootApi.Controllers;


import OnetoOneProject.SpringBootApi.Models.Person;
import OnetoOneProject.SpringBootApi.Services.PeopleServices;
import OnetoOneProject.SpringBootApi.Util.PersonDetailsException;
import jakarta.validation.Valid;
import org.hibernate.annotations.CurrentTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {
    private final PeopleServices peopleServices;

    @Autowired
    public PersonController(PeopleServices peopleServices) {
        this.peopleServices = peopleServices;
    }

    //person information
    @GetMapping("/personInfo")
    public Person findPersonById(@RequestBody@Valid Person person){
        return peopleServices.findPerson(person.getId());
    }
    @PostMapping
    public Person updatePerson(@RequestBody@Valid Person person,
                               BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            StringBuilder ErrorMessage = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors) {
                ErrorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";\n");
            }
            throw new PersonDetailsException(ErrorMessage);
        }
        peopleServices.savePerson(person);
        return peopleServices.findPerson(person.getId());
    }

    //show cart items

}
