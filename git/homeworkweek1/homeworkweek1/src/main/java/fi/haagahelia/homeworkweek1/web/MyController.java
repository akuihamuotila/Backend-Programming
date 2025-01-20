package fi.haagahelia.homeworkweek1.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    // Endpoint: /index
    @GetMapping("/index")
    public String index() {
        return "This is the main page";
    }

    // Endpoint: /contact
    @GetMapping("/contact")
    public String contact() {
        return "This is the contact page";
    }

    // Endpoint: /hello, jossa kaksi parametriä (location ja name)
    @GetMapping("/hello")
    public String hello(@RequestParam String location, @RequestParam String name) {
        return "Welcome to the " + location + " " + name + "!";
    }
    
}