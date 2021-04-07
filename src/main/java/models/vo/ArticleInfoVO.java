package models.vo;

import models.ArticleInfo;

public class ArticleInfoVO extends ArticleInfo {
    private String username;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
