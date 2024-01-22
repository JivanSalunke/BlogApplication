package learnspring.blogapplication.comments;

import jakarta.persistence.*;
import learnspring.blogapplication.articles.Article;
import learnspring.blogapplication.commons.BaseEntity;
import learnspring.blogapplication.users.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity {

    String title;
    String body;

    @ManyToOne
    Article articleComment;

    @ManyToOne
    User author;
}
