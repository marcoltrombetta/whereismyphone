package obj;

import model.MenuModel;

/**
 *
 * @author marco
 */
public class Menu {
    private int id;
    private int idPlan;
    private int idRole;
    private String name;
    private String url;
    private String style;
    private int displayOrder;
    private int idParent;
    private Plan plan;
    private Role role;
    private Menu parent;
    
    public Menu(int id, int idPlan, int idRole, String name, String url, String style, int displayOrder) {
        this.id = id;
        this.idPlan = idPlan;
        this.idRole = idRole;
        this.name = name;
        this.url = url;
        this.style = style;
        this.displayOrder = displayOrder;
    }
    
     public Menu(int id, int idPlan, int idRole, String name, String url, String style, int displayOrder, Plan plan, Role role,Menu parent) {
        this.id = id;
        this.idPlan = idPlan;
        this.idRole = idRole;
        this.name = name;
        this.url = url;
        this.style = style;
        this.displayOrder = displayOrder;
        this.plan=plan;
        this.role=role;
        this.parent=parent;
    }

    public Menu() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getIdParent() {
        return idParent;
    }

    public void setIdParent(int idParent) {
        this.idParent = idParent;
    }
                
    public MenuModel toModel(){
        if(this.parent==null){
            return new MenuModel(
                this.name,
                this.url,
                this.style,
                null
            );
        }else{
            return new MenuModel(
                this.name,
                this.url,
                this.style,
                this.parent.toModel()
            );
        }
        
    }   
}
