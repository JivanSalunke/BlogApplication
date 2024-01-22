package learnspring.blogapplication.users;

import learnspring.blogapplication.Exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {
    private final IUserRepository userRepo;
    private PasswordEncoder encoder;

    public UserService(@Autowired IUserRepository userRepo, @Autowired PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.encoder=passwordEncoder;
    }

    boolean validateUserName(String userName) throws Exception{
        if(userName==null || userName.length()<1 || userName.length()>20){
            throw new Exception("User name must contains 1-20 characters");
        }
        if(userRepo.findByUserName(userName)!=null){
            throw new Exception("User with username: "+ userName +" already exists");
        }
        return true;
    }

    boolean validateEmail(String email) throws Exception{
        String patternString = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(email);
        boolean matches = matcher.matches();
        if(!matches){
            throw new Exception("Invalid email id");
        }
        return true;
    }

    boolean validatePassword(String pass) throws Exception{
        String patternString = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(pass);
        boolean matches = matcher.matches();
        if(pass.length()<8){
            throw new Exception("Password should not be less than 8 charactes");
        }
        if(!matches){
            throw  new Exception("Password should contains at least 1 uppercase, 1 lowercase and 1 numeric character");
        }
        return true;
    }

    public User createUser(String userName, String password, String email) throws Exception {
        validateUserName(userName);
        validatePassword(password);
        validateEmail(email);
        String encodedPass=encoder.encode(password);
        User user= User.builder().userName(userName).password(encodedPass).email(email).build();
        System.out.println("userObjs"+user.toString());
        User createdUser=userRepo.save(user);
        return createdUser;
    }
    public List<User> getAllUsers(){
        return (List<User>) userRepo.findAll();
    }

    public User getUserByUserName(String uName) throws UserNotFoundException {
        User user= userRepo.findByUserName(uName);
        if(user==null){
            throw new UserNotFoundException("User with username: "+ uName +" not Found.");
        }
        return  user;
    }

    public User login(String userName, String pass) throws Exception{
        User user=getUserByUserName(userName);
        boolean isValidPassword=encoder.matches(pass,user.getPassword());
        if(!isValidPassword){
            throw new Exception("Incorrect password");
        }
        return user;
    }

}
