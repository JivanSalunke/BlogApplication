package learnspring.blogapplication.comments;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @GetMapping("/{id}")
    ResponseEntity<List<Comment>> getAllComments(@PathVariable("id") Long id){
        return null;
    }

}
