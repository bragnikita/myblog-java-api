package jp.bragnikita.myblogapi.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootTest
class SequencesTest {

    @Autowired
    Counters counter;

    @Autowired
    MongoTemplate mongo;

    @Test
    void testGetNext() {
        Assertions.assertEquals(1, counter.getNextPostId());
    }

    @Test
    void testCounterRiseUp() {
        long next = counter.getNextPostId();
        Assertions.assertEquals(next+1, counter.getNextPostId());
    }
}
