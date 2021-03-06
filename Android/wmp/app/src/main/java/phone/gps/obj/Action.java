package phone.gps.obj;

public class Action {

	private int id;
	private int idPhoneInfo;
	private boolean sound;
	private boolean logout;
	private boolean broadcast;
		
	public Action() {
		super();
	}

	public Action(int id, int idPhoneInfo, boolean sound, boolean logout,
			boolean broadcast) {
		super();
		this.id = id;
		this.idPhoneInfo = idPhoneInfo;
		this.sound = sound;
		this.logout = logout;
		this.broadcast = broadcast;
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
	public boolean isSound() {
		return sound;
	}
	public void setSound(boolean sound) {
		this.sound = sound;
	}
	public boolean isLogout() {
		return logout;
	}
	public void setLogout(boolean logout) {
		this.logout = logout;
	}
	public boolean isBroadcast() {
		return broadcast;
	}
	public void setBroadcast(boolean broadcast) {
		this.broadcast = broadcast;
	}

}
