package jp.bragnikita.myblogapi.models;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

import java.util.Date;

@Configuration
public class EntityListeners {

    @Bean
    public ModifiedDateSetter createDateSetter() {
        return new ModifiedDateSetter();
    }

    private static class ModifiedDateSetter extends AbstractMongoEventListener<CanSetModifiedAt> {

        @Override
        public void onBeforeConvert(BeforeConvertEvent<CanSetModifiedAt> event) {
            var post = event.getSource();
            post.setModifiedAt(new Date());
        }
    }
}
