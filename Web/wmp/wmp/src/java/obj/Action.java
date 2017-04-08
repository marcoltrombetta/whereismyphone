package obj;

import model.ActionModel;

public class Action {

	private int id;
	private int idPhoneInfo;
	private boolean sound;
	private boolean logout;
	private boolean broadcast;
        private String sms;
        private PhoneName phoneName;
        
	public Action() {
		super();
	}

	public Action(int id, int idPhoneInfo, boolean sound, boolean logout,
			boolean broadcast,PhoneName phoneName) {
		super();
		this.id = id;
		this.idPhoneInfo = idPhoneInfo;
		this.sound = sound;
		this.logout = logout;
		this.broadcast = broadcast;
                this.phoneName=phoneName;
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

        public String getSms() {
            return sms;
        }

        public void setSms(String sms) {
            this.sms = sms;
        }

        public PhoneName getPhoneInfo() {
            return phoneName;
        }

        public void setPhoneInfo(PhoneName phoneName) {
            this.phoneName = phoneName;
        }
        
        public ActionModel toModel(){
            return new ActionModel(
                    this.id,
                    this.idPhoneInfo,
                    this.sound,
                    this.logout,
                    this.broadcast,
                    this.sms,
                    this.phoneName.toModel()
            );
        }
}

