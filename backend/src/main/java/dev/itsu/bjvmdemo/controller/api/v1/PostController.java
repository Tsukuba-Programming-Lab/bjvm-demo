package dev.itsu.bjvmdemo.controller.api.v1;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class PostController {

    @GetMapping("/posts")
    public List<String> posts() {
        return List.of();
    }

    @PostMapping("/post")
    public String post(@RequestParam String userName, @RequestParam String text) {
        return text;
    }

}
