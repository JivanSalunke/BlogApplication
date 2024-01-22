package learnspring.blogapplication.users;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class IUserRepositoryTest {

    @Autowired
    IUserRepository iUserRepository;

    @Test
    public void createUser(){
        User u=new User("Jivan","123456","Givan@gmail.com","kjhsd","djhk","sdjk");
        iUserRepository.save(u);
    }
}
