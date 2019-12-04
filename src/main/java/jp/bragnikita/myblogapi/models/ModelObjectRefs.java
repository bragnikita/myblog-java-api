package jp.bragnikita.myblogapi.models;

public class ModelObjectRefs {

    public static class ImageRef {
        public String locator;
        public Long id;
        public Image.Storage storage;
    }

    public static class AlbumRef {
        public String title;
        public Long id;
    }

}
