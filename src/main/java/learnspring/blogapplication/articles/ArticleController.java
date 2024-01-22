package learnspring.blogapplication.articles;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @GetMapping("")
    ResponseEntity<List<Article>> getAllArticles(){
        return null;
    }
}
