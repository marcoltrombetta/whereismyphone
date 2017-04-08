package obj;

/**
 *
 * @author marco
 */
public class AlarmAccion {
    private int id;
    private int idPlan;
    private String desc;
    private Plan plan;
    
    public AlarmAccion() {
    }

    public AlarmAccion(int id, int idPlan, String desc, Plan plan) {
        this.id = id;
        this.idPlan = idPlan;
        this.desc = desc;
        this.plan=plan;
    }

    public int getId() {
        return id;
    }

    public int getIdPlan() {
        return idPlan;
    }

    public String getDesc() {
        return desc;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }
    
}
