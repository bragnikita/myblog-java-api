package jp.bragnikita.myblogapi.services;

import jp.bragnikita.myblogapi.models.Counters;
import jp.bragnikita.myblogapi.models.Post;
import jp.bragnikita.myblogapi.models.Types;
import jp.bragnikita.myblogapi.repos.PostsRepo;
import jp.bragnikita.myblogapi.utils.AppExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PostsOperations {

    @Autowired
    private PostsRepo repo;

    @Autowired
    private Counters counters;

    public Post get(Long id) {
        return repo.findById(id).orElseThrow(AppExceptions.ObjectNotFoundException::new);
    }

    public Post create(CreatePostParams params) {
        Post p = new Post(counters.getNextPostId());
        p.setTitle(params.title);
        p.setTextContent(params.textContent);
        p.setVisibility(Post.PostVisibility.PRIVATE);
        p.setStatus(Post.PostStatus.DRAFT);
        p.setCover(new Types.ImageRef());
        p.getCover().url = params.coverRef;
        return repo.save(p);
    }
    public Post update(Long id, UpdatePostParams params) {
        Post p = repo.findById(id).orElseThrow(AppExceptions.ObjectNotFoundException::new);
        if (params.title != null) {
            p.setTitle(params.title);
        }
        if (params.coverRef != null && params.coverRef.isEmpty()) {
            p.setCover(null);
        }
        if (params.textContent != null) {
            p.setTextContent(params.textContent);
        }
        return repo.save(p);
    }


    public static class CreatePostParams {
        public String title;
        public String textContent;
        public String coverRef;
    }
    public static class UpdatePostParams extends CreatePostParams {

    }
}
