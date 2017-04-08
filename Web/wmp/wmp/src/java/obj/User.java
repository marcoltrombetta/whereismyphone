package obj;

import model.RoleModel;
import model.UserModel;

public class User {

	private int id;
	private String email;
	private String password;
	private int idRole;
	private Role role;
        private String token;
        
	public User() {
		super();
	}
	
	public User(int id, String email, String password,String token, int idRole,
			 Role role) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.idRole = idRole;
		this.token=token;
                this.role=role;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getIdRole() {
		return idRole;
	}
	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}


        public void setRole(Role role) {
            this.role = role;
        }

        public Role getRole() {
            return role;
        }

	public boolean isAdmin(){
            if(this.id>0 && this.idRole==1){
                    return true;
            }
            return false;
	}
        
        
        public boolean isUserValid(){
            if(this.getId()==0){
                return false;
            }
            return true;
        }
        
        public UserModel toModel(){
            return new UserModel(
                this.id,
                this.email,
                this.idRole,
                this.token,               
                new RoleModel(
                    this.role.getId(),
                    this.role.getDesc()
                )
            );
        }
        
        public String getToken(){
            String a=Globals.md5(this.email+this.id+this.password);
            return a;
        }
         public void setToken(String token){
            this.token=token;
        }
}
