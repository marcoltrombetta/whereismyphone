package phone.gps.obj;

public class Plan {

	private int id;
	private int cantUsuariosAdmin;
	private int cantUsuariosOwner;

	public Plan() {
		super();
	}
	
	public Plan(int id, int cantUsuariosAdmin, int cantUsuariosOwner,
			int idEmpresa) {
		super();
		this.id = id;
		this.cantUsuariosAdmin = cantUsuariosAdmin;
		this.cantUsuariosOwner = cantUsuariosOwner;

	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCantUsuariosAdmin() {
		return cantUsuariosAdmin;
	}
	public void setCantUsuariosAdmin(int cantUsuariosAdmin) {
		this.cantUsuariosAdmin = cantUsuariosAdmin;
	}
	public int getCantUsuariosOwner() {
		return cantUsuariosOwner;
	}
	public void setCantUsuariosOwner(int cantUsuariosOwner) {
		this.cantUsuariosOwner = cantUsuariosOwner;
	}

}
