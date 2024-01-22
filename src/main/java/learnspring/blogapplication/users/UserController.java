package learnspring.blogapplication.users;

import learnspring.blogapplication.ErrorMsg;
import learnspring.blogapplication.Exception.UserNotFoundException;
import learnspring.blogapplication.TokenService.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final TokenService tokenService;

    public UserController(@Autowired UserService userService, @Autowired TokenService tokenService) {
        this.userService = userService;
        this.tokenService=tokenService;
    }

    @GetMapping("")
    ResponseEntity<List<User>> getAllUsers(){
        List<User> users=userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/verify")
    String verifyToken(@RequestHeader String token){
        return tokenService.verifyToken(token);
    }

    @GetMapping("/{uName}")
    User getUserByUserName(@PathVariable("uName") String uName) throws UserNotFoundException {
        return  userService.getUserByUserName(uName);
    }

    @PostMapping("/signup")
    ResponseEntity<User> createUser(@RequestBody CreateUserDTO newUser) throws Exception{
        User createdUser=userService.createUser(newUser.getUserName(), newUser.getPassword(), newUser.getEmail());
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/login")
    ResponseEntity<User> login(@RequestBody CreateUserDTO user) throws Exception{
        String token=tokenService.createToken(user.getUserName());
        User u= userService.login(user.getUserName(),user.getPassword());
        u.authToken=token;
        return ResponseEntity.ok(u);
    }

    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<String> handleIncorrectTaskDetailsException(UserNotFoundException e){
        ErrorMsg err= new ErrorMsg(e.getMessage());
        return  ResponseEntity.badRequest().body(err.toString());
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<String> handleGeneralExceptions(Exception e){
        ErrorMsg err= new ErrorMsg(e.getMessage());
        return ResponseEntity.badRequest().body(err.toString());
    }

}
