package phone.gps.obj;

public class UserPermission {

	private int id;
	private int idUsuario;
	private boolean canDelete;
	private boolean canEdit;
	private boolean canAdd;
	
	public UserPermission() {
		super();
	}
	
	public UserPermission(int id, int idUsuario, boolean canDelete,
			boolean canEdit, boolean canAdd) {
		super();
		this.id = id;
		this.idUsuario = idUsuario;
		this.canDelete = canDelete;
		this.canEdit = canEdit;
		this.canAdd = canAdd;
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
	public boolean isCanDelete() {
		return canDelete;
	}
	public void setCanDelete(boolean canDelete) {
		this.canDelete = canDelete;
	}
	public boolean isCanEdit() {
		return canEdit;
	}
	public void setCanEdit(boolean canEdit) {
		this.canEdit = canEdit;
	}
	public boolean isCanAdd() {
		return canAdd;
	}
	public void setCanAdd(boolean canAdd) {
		this.canAdd = canAdd;
	}

}
