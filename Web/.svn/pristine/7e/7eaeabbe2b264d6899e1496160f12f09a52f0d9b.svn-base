package model;

import obj.*;
import java.sql.Timestamp;

/**
 *
 * @author marco
 */
public class NotificationModel {
    private int id;
    private String sms;
    private String title;
    private boolean read;
    private Timestamp date;
    private UserModel user;
    
    public NotificationModel(int id, String sms,String title, boolean read, Timestamp date,UserModel user) {
        this.id = id;
        this.sms = sms;
        this.title=title;
        this.read = read;
        this.date = date;
        this.user=user;
    }
   
    public NotificationModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
   
}
