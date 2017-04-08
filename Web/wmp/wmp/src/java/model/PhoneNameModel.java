package model;

import obj.*;

public class PhoneNameModel {

	private int id;
	private int idPhoneInfo;
	private String desc;
	private PhoneInfoModel phoneInfo;
        
	public PhoneNameModel() {
		super();
	}

        public PhoneNameModel(int id, int idPhoneInfo, String desc, PhoneInfoModel phoneInfo) {
            this.id = id;
            this.idPhoneInfo = idPhoneInfo;
            this.desc = desc;
            this.phoneInfo = phoneInfo;
        }

        public PhoneNameModel(int id, int idPhoneInfo, String desc) {
            this.id = id;
            this.idPhoneInfo = idPhoneInfo;
            this.desc = desc;
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
            this.desc = desc;
	}

        public PhoneInfoModel getPhoneInfo() {
            return phoneInfo;
        }

        public void setPhoneInfo(PhoneInfoModel phoneInfo) {
            this.phoneInfo = phoneInfo;
        }
}
