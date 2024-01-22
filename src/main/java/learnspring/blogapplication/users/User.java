package learnspring.blogapplication.users;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import learnspring.blogapplication.articles.Article;
import learnspring.blogapplication.commons.BaseEntity;
import lombok.*;

import java.util.List;

@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User extends BaseEntity {
    String userName;
    String email;
    String password;
    String authToken;
    String bio;
    String imageUrl;
}
