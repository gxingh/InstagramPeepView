package com.a22boxes.instagrampeepview;

public class RecyclerItem {
    int userImage;
    String userName;
    int image;

    public RecyclerItem(String username, int userImage, int image) {
        this.userImage = userImage;
        this.userName = username;
        this.image = image;
    }

    public int getUserImage() {
        return userImage;
    }

    public void setUserImage(int userImage) {
        this.userImage = userImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
