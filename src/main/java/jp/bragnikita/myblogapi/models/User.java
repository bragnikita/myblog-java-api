package jp.bragnikita.myblogapi.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
    @Id
    public Long id;
    public String username;
    public String displayName;
    public String groupId;
    public ModelObjectRefs.ImageRef avatar;
    public String accessToken;
    public String confirmationCode;
}
