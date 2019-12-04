package jp.bragnikita.myblogapi.models;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Album implements CanSetModifiedAt {
    @Id
    public String id;
    public String title;
    public String description;
    public String slug;
    public Date dateFrom;
    public Date dateTill;
    public String place;
    public ModelObjectRefs.ImageRef cover;
    public Date createdAt = new Date();
    public Date modifiedAt;


    @Override
    public void setModifiedAt(Date date) {
        this.modifiedAt = date;
    }

}
