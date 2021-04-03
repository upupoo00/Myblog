package models;

import java.util.Date;

/**
 * userinfo 实体类
 */
public class UserInfo {
    private int id;
    private String username;
    private String password;
    private Date createtime;
    private  Date updatetime;
    private  int state;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public int getState() {
        return state;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public void setState(int state) {
        this.state = state;
    }
}
