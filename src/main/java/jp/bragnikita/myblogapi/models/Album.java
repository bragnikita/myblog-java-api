package jp.bragnikita.myblogapi.models;

import org.springframework.data.annotation.Id;

public class Album {
    @Id
    public String id;

    public String title;

    public Types.ImageRef[] images;
}
