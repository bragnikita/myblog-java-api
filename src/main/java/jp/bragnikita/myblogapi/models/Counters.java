package jp.bragnikita.myblogapi.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Component
public class Counters {

    private final MongoTemplate mongoTemplate;

    private Query byName(String name) {
        return query(where("_id").is(name));
    }

    public Counters(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public long getNextPostId() {
        return this.getNextLong("posts");
    }

    public long getNextImageId() {
        return this.getNextLong("images");
    }

    public long getNextAlbumId() {
        return this.getNextLong("albums");
    }

    public long getNextLong(String seqName) {
        MongoSequence nextState = mongoTemplate
                .findAndModify(this.byName(seqName),
                        new Update().inc("nextLong", 1L), MongoSequence.class);
        if (nextState == null) {
            throw new RuntimeException("seq not found");
        }
        return nextState.nextValLong;
    }

    @PostConstruct
    public void init() {
        checkAndCreate("posts");
        checkAndCreate("albums");
        checkAndCreate("images");
    }

    private void checkAndCreate(String seqName) {
        MongoSequence seq = mongoTemplate.findOne(this.byName(seqName), MongoSequence.class);
        if (seq == null) {
            seq = new MongoSequence(seqName);
            seq.nextValLong = 1L;
            mongoTemplate.save(seq);
        }
    }

    @Document(collection = "seq")
    public static class MongoSequence {

        public MongoSequence(@Value("#root._id") String id) {
            this.name = id;
        }

        @Id
        public String name;
        @Field("nextLong")
        public Long nextValLong;
    }
}
