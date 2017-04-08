package obj;

import java.sql.Timestamp;
import java.util.Calendar;
import model.UserPlanModel;

/**
 *
 * @author marco
 */
public class UserPlan {
    private int id;
    private int idUsuario;
    private int idPlan;
    private Timestamp fechaVenc;
    private Plan plan;
    private User user;

    public UserPlan(int id, int idUsuario, int idPlan, Timestamp fechaVenc, Plan plan) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idPlan = idPlan;
        this.fechaVenc = fechaVenc;
        this.plan=plan;
    }
    
     public UserPlan(int id, int idUsuario, int idPlan, Timestamp fechaVenc, Plan plan, User user) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idPlan = idPlan;
        this.fechaVenc = fechaVenc;
        this.plan=plan;
        this.user=user;
    }
    
    public UserPlan() {
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

    public int getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
    }

    public Timestamp getFechaVenc() {
        return fechaVenc;
    }

    public void setFechaVenc(Timestamp fechaVenc) {
        this.fechaVenc = fechaVenc;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
         
    public UserPlanModel toModel(){
        return new UserPlanModel(
                this.id,
                this.idPlan,
                this.fechaVenc,
                this.plan.toModel(),
                this.user.toModel()
        );
    }
    
    public boolean isPlanValid(){
        Calendar currenttime = Calendar.getInstance();
        Timestamp date = new Timestamp((currenttime.getTime()).getTime());
            
        if(this.idPlan==1){
            return true;
        }
        
        if(this.fechaVenc.after(date)){
            return true;
        }
        return false;        
    }
}
