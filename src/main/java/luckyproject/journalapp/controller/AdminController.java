package luckyproject.journalapp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import luckyproject.journalapp.service.UserService;
import luckyproject.journalapp.entity.User;
import org.springframework.http.HttpStatus;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping("/admin")
public class AdminController {
  @Autowired
  private UserService userService;

  @Autowired
    private PasswordEncoder passwordEncoder;

  @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {
       List<User> all = userService.getAll();  
       if(all!=null && !all.isEmpty()){
        return new ResponseEntity<>(all, HttpStatus.OK); 
       } 
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}

@PostMapping("/create-admin")
public ResponseEntity<?> createAdmin(@RequestBody User user){
    userService.saveNewAdmin(user);
    return ResponseEntity.ok("Admin created successfully");
}

}
