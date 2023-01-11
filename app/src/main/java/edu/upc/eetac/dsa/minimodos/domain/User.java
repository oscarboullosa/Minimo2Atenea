package edu.upc.eetac.dsa.minimodos.domain;

public class User {

    String avatar_url;
    String login;

    public User(String avatar_url, String login) {
        this.avatar_url = avatar_url;
        this.login = login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
