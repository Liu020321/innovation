package com.innovation.pojo;

import org.apache.ibatis.type.Alias;

@Alias("user")
public class User {
    private Integer id;
    private String userName;
    private String password;
    private Integer status;

    public User() {
        super();
    }

    public User(String userName) {
        super();
        this.userName = userName;
    }

    public User(String userName, String password, Integer status) {
        super();
        this.userName = userName;
        this.password = password;
        this.status = status;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                '}';
    }
}
