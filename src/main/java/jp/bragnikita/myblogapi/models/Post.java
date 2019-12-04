package jp.bragnikita.myblogapi.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.Date;

@Document(collection = "posts")
public class Post implements CanSetModifiedAt {

    @Id
    private Long id;
    private String title;
    private String textContent;
    private ModelObjectRefs.ImageRef cover;
    @Field(targetType = FieldType.STRING)
    private PostVisibility visibility;
    @Field(targetType = FieldType.STRING)
    private PostStatus status;
    private String postType;
    private String slug;
    private String textContentType;
    private boolean canBeListed = false;
    private String author;
    private String[] categories = new String[]{};
    private AttributeType[] attributes = new AttributeType[]{};
    private Date createdAt = new Date();
    private Date publishedAt;
    private Date modifiedAt;


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

    /**
     * Getters and Setters
     **/
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

    public ModelObjectRefs.ImageRef getCover() {
        return cover;
    }

    public void setCover(ModelObjectRefs.ImageRef cover) {
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTextContentType() {
        return textContentType;
    }

    public void setTextContentType(String textContentType) {
        this.textContentType = textContentType;
    }

    public boolean isCanBeListed() {
        return canBeListed;
    }

    public void setCanBeListed(boolean canBeListed) {
        this.canBeListed = canBeListed;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public AttributeType[] getAttributes() {
        return attributes;
    }

    public void setAttributes(AttributeType[] attributes) {
        this.attributes = attributes;
    }

}
