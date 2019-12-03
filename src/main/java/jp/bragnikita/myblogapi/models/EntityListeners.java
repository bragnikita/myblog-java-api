package jp.bragnikita.myblogapi.models;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;

import java.util.Date;

@Configuration
public class EntityListeners {

    @Bean
    public CreateDateSetter createDateSetter() {
        return new CreateDateSetter();
    }

    private static class CreateDateSetter extends AbstractMongoEventListener<Post> {

        @Override
        public void onBeforeConvert(BeforeConvertEvent<Post> event) {
            var post = event.getSource();
            if (post.getCreated() == null) {
                post.setCreated(new Date());
            }
            post.setModified(new Date());
        }
    }
}
