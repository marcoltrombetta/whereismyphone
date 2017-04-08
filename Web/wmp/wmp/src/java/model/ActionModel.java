package model;

import obj.*;

public class ActionModel {

	private int id;
	private int idPhoneInfo;
	private boolean sound;
	private boolean logout;
	private boolean broadcast;
        private String sms;
	private PhoneNameModel phoneName;
        
	public ActionModel() {
		super();
	}

	public ActionModel(int id, int idPhoneInfo, boolean sound, boolean logout,
			boolean broadcast, String sms, PhoneNameModel phoneName) {
		super();
		this.id = id;
		this.idPhoneInfo = idPhoneInfo;
		this.sound = sound;
		this.logout = logout;
		this.broadcast = broadcast;
                this.sms=sms;
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
        
        public PhoneNameModel getPhoneInfo() {
            return phoneName;
        }

        public void setPhoneInfo(PhoneNameModel phoneName) {
            this.phoneName = phoneName;
        }

        public boolean isActionActive(){
            if(this.broadcast==false && this.logout==false &&
                    this.sound==false){
                return false;
            }
            return true;
        }
}

