package model;

import java.sql.Timestamp;
import obj.Plan;
import obj.User;

/**
 *
 * @author marco
 */
public class UserPlanModel {
    private int id;
    private int idPlan;
    private Timestamp fechaVenc;
    private PlanModel plan;
    private UserModel user;

    public UserPlanModel(int id,int idPlan, Timestamp fechaVenc, PlanModel plan, UserModel user) {
        this.id = id;
        this.idPlan=idPlan;
        this.fechaVenc = fechaVenc;
        this.plan=plan;
        this.user=user;
    }
    
    public UserPlanModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getFechaVenc() {
        return fechaVenc;
    }

    public void setFechaVenc(Timestamp fechaVenc) {
        this.fechaVenc = fechaVenc;
    }

    public PlanModel getPlan() {
        return plan;
    }

    public void setPlan(PlanModel plan) {
        this.plan = plan;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user.toModel();
    }

    public int getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
    }
    
    
         
}
