package obj;

import model.RoleModel;

public class Role {

	private int id;
	private String desc;
	
	public Role() {
		super();
	}
	
	public Role(int id, String desc) {
		this.id = id;
		this.desc = desc;
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
        
         public RoleModel toModel(){
            return new RoleModel(
                this.id,
               this.desc
            );
        }
}
