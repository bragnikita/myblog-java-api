package jp.bragnikita.myblogapi.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigInteger;
import java.util.Date;

@Document(collection = "posts")
public class Post {

    @Id
    private Long id;

    private String title;

    private String textContent;

    private Types.ImageRef cover;

    @Field(targetType = FieldType.STRING)
    private PostVisibility visibility;
    @Field(targetType = FieldType.STRING)
    private PostStatus status;

    private Date created;

    private Date published;

    private Date modified;

    public enum PostStatus {
        DRAFT,
        PUBLISHED
    }

    public enum PostVisibility {
        PRIVATE,
        PUBLIC,
        RESTRICTED,
    }



    public Post(@Value("#root._id") Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public Types.ImageRef getCover() {
        return cover;
    }

    public void setCover(Types.ImageRef cover) {
        this.cover = cover;
    }

    public PostVisibility getVisibility() {
        return visibility;
    }

    public void setVisibility(PostVisibility visibility) {
        this.visibility = visibility;
    }

    public PostStatus getStatus() {
        return status;
    }

    public void setStatus(PostStatus status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

}
