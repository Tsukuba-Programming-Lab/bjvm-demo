package dev.itsu.bjvmdemo.controller.api.v1;

import dev.itsu.bjvmdemo.model.Post;
import dev.itsu.bjvmdemo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class PostController {

    private final PostRepository repository;

    public PostController(PostRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/posts")
    public List<Post> posts() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }

    @PutMapping("/post")
    public Post post(@ModelAttribute Post post) {
        repository.save(post);
        return post;
    }

}
