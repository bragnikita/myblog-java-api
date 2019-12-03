package jp.bragnikita.myblogapi.repos;

import jp.bragnikita.myblogapi.models.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface PostsRepo extends MongoRepository<Post, Long> {

}
