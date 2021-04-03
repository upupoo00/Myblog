package models;

import java.util.Date;

public class ArticleInfo {
    private int id;
    private String title;
    private String content;
    private Date createtime;
    private Date updatetime;
    private int uid;
    private int rcount;
    private int state;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public int getUid() {
        return uid;
    }

    public int getRcount() {
        return rcount;
    }

    public int getState() {
        return state;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setRcount(int rcount) {
        this.rcount = rcount;
    }

    public void setState(int state) {
        this.state = state;
    }
}
