package dev.itsu.bjvmdemo.controller.api.v1;

import dev.itsu.bjvmdemo.model.Post;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("api/v1")
@CrossOrigin
public class PostController {

    private long id = 0;
    private final HashMap<Long, Post> posts = new HashMap<>();

    @GetMapping("/posts")
    public List<Post> posts() {
        return posts.values().stream().toList();
    }

    @PutMapping("/post")
    public Post post(@ModelAttribute Post post) {
        post.setSId(String.valueOf(id));
        post.setDate(new Date().toString());

        posts.put(id, post);
        id++;

        return post;
    }

}
