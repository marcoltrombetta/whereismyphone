package model;

public class PlanModel {

    private int id;
    private String desc;

    public PlanModel(int id,  String desc) {
        this.id = id;
        this.desc = desc;
    }

    public PlanModel() {
    }
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
        
}
