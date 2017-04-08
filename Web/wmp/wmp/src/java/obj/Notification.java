package obj;

import java.sql.Timestamp;
import model.NotificationModel;

/**
 *
 * @author marco
 */
public class Notification {
    private int id;
    private int idUsuario;
    private String sms;
    private String title;
    private boolean read;
    private Timestamp date;
    private User user;

    public Notification(int id, int idUsuario, String sms, String title, boolean read, Timestamp date, User user) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.sms = sms;
        this.read = read;
        this.date = date;
        this.title=title;
        this.user=user;
    }
 
    public Notification() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
          
     public NotificationModel toModel(){
            return new NotificationModel(
                this.id,
                this.sms,
                this.title,
                this.read,
                this.date,
                this.user.toModel()
            );
        }
}
