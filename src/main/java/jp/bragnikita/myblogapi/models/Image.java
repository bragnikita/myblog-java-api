package jp.bragnikita.myblogapi.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.Date;

@Document(collection = "images")
public class Image {
    @Id
    public Long id;
    public String title;
    public String description;
    @Field(targetType = FieldType.STRING)
    public Storage storage;
    public String locator;
    public String thumbnailLocator;
    public String blobId;
    public Date createdAt;
    public Long albumId;

    public enum Storage {
        FILE, S3, BLOB
    }
}
