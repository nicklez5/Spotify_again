package com.spotify.demo.entity;
import jakarta.persistence.*;
@Entity
public class CurrentUserSession {
    @Id
    private String email;
    private Integer userId;
    private String uuId;

    public CurrentUserSession(){

    }
    public CurrentUserSession(String email, Integer userId, String uuId){
        super();
        this.email = email;
        this.userId = userId;
        this.uuId = uuId;

    }

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUuid() {
        return uuId;
    }
    public void setUuid(String uuid) {
        this.uuId = uuid;
    }
}
