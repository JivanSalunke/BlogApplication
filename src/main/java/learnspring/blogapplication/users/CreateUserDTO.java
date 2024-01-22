package learnspring.blogapplication.users;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class CreateUserDTO {
    String userName;
    String password;
    String email;
    String bio;
    String imageUrl;
}
