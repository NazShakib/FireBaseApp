package DatabaseConnection;

import com.google.gson.annotations.SerializedName;

public class userData {

    @SerializedName("id")
    private String name;
    @SerializedName("title")
    private String email;
    @SerializedName("url")
    private String password;
    @SerializedName("thumbnailUrl")
    private String thumbnailUrl;

    public userData(String name, String email, String password, String thumbnailUrl) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}

