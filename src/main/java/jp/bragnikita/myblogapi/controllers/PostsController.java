package jp.bragnikita.myblogapi.controllers;

import jp.bragnikita.myblogapi.models.Post;
import jp.bragnikita.myblogapi.repos.PostsRepo;
import jp.bragnikita.myblogapi.services.PostsOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostsController {

    private final PostsOperations posts;

    public PostsController(@Autowired PostsOperations posts) {
        this.posts = posts;
    }

    @GetMapping("/")
    public List<Post> list() {
        return List.of();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
//        throw new RuntimeException();
        var post = posts.get(id);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(post);
    }

    @PostMapping("/")
    public Post create(@RequestBody PostsOperations.CreatePostParams request) {
        return this.posts.create(request);
    }
}
