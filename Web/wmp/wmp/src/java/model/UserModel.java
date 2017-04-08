package model;

import obj.Role;

/**
 *
 * @author marco
 */
public class UserModel {
        
        private int id;
	private String email;
	private int idRole;
        private String token;
	private RoleModel role;
        
	public UserModel() {
            super();
	}
	
	public UserModel(int id, String email, int idRole, String token, RoleModel role) {
		super();
		this.id = id;
		this.email = email;
		this.idRole = idRole;
		this.token = token;
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
	public int getIdRole() {
		return idRole;
	}
	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}
        
        public void setRole(RoleModel role) {
           this.role = role;
        }

        public RoleModel getRole() {
            return role;
        }
}
