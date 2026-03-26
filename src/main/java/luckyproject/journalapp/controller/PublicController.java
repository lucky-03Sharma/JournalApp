package luckyproject.journalapp.controller;

import luckyproject.journalapp.entity.User;           
import luckyproject.journalapp.service.UserService;

import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService; 


    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/health-check")
    public String healthCheck() {
        return "OK";
    }

   @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); 
        user.setRoles(List.of("USER")); 
        userService.saveUser(user);
        return ResponseEntity.ok("User created");
}
}