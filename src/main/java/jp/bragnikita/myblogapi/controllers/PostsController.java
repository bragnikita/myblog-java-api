package jp.bragnikita.myblogapi.controllers;

import jp.bragnikita.myblogapi.models.Post;
import jp.bragnikita.myblogapi.repos.PostsRepo;
import jp.bragnikita.myblogapi.services.PostsOperations;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostsController {

    private final PostsOperations posts;

    public PostsController(PostsOperations posts) {
        this.posts = posts;
    }

    @GetMapping("/")
    public List<Post> list() {
        return List.of();
    }

    @GetMapping("/{id}")
    public Post get(@PathVariable Long id) {
        return posts.get(id);
    }

    @PostMapping("/")
    public Post create(@RequestBody PostsOperations.CreatePostParams request) {
        return this.posts.create(request);
    }
}
