package OnetoOneProject.SpringBootApi.Controllers;


import OnetoOneProject.SpringBootApi.Services.ItemServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemServices itemServices;

    @Autowired
    public ItemController(ItemServices itemServices) {
        this.itemServices = itemServices;
    }


}
