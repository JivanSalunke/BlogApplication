package learnspring.blogapplication.articles;

import jakarta.persistence.*;
import learnspring.blogapplication.commons.BaseEntity;
import learnspring.blogapplication.users.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "articles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Article extends BaseEntity {

    String title;
    String slug;
    String subtitle;
    String body;

    @ManyToOne
    User author;
}
