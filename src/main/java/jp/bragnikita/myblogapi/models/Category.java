package jp.bragnikita.myblogapi.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
public class Category {
    @Id
    public String id;
    public String displayName;
    public Integer postCount;

    public Category(String _id) {
        this.id = _id;
    }
}
