package obj;

public class Logged {
	private int id;
	private int idPhoneInfo;
	private boolean status;
	
	public Logged() {
		super();
	}
	
	public Logged(int id, int idPhoneInfo, boolean status) {
		super();
		this.id = id;
		this.idPhoneInfo = idPhoneInfo;
		this.status = status;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdPhoneInfo() {
		return idPhoneInfo;
	}
	public void setIdPhoneInfo(int idPhoneInfo) {
		this.idPhoneInfo = idPhoneInfo;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}
