package com.diplomski.obavestavanje.nastavnika.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class TemplateController {

    @GetMapping("login")
    public String getLoginView(){
        return "login";
    }
    @GetMapping("getCourses")
    public String getCourses(){
        return "courses";
    }
    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from API");
    }
    @GetMapping("/say=good=bye")
    public ResponseEntity<String> sayGoodBye(){
        return ResponseEntity.ok("Good bye and see you later");
    }
}
