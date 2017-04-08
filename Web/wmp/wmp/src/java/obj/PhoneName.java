package obj;

import model.PhoneInfoModel;
import model.PhoneNameModel;

public class PhoneName {

	private int id;
	private int idPhoneInfo;
	private String desc;
	private PhoneInfo phoneInfo;
        
	public PhoneName() {
		super();
	}

        public PhoneName(int id, int idPhoneInfo, String desc, PhoneInfo phoneInfo) {
            this.id = id;
            this.idPhoneInfo = idPhoneInfo;
            this.desc = desc;
            this.phoneInfo = phoneInfo;
        }

        public PhoneName(int id, int idPhoneInfo, String desc) {
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

        public PhoneInfo getPhoneInfo() {
            return phoneInfo;
        }

        public void setPhoneInfo(PhoneInfo phoneInfo) {
            this.phoneInfo = phoneInfo;
        } 
        
        public PhoneNameModel toModel(){
            
            if(this.phoneInfo!=null){
                return new PhoneNameModel(
                    this.id,
                    this.idPhoneInfo,
                    this.desc,
                    new PhoneInfoModel(
                        this.phoneInfo.getId(),
                        this.phoneInfo.getImei(),
                        this.phoneInfo.getModelo()
                    )
                );
            }else{
                return new PhoneNameModel(
                    this.id,
                    this.idPhoneInfo,
                    this.desc,
                    null
                );
            }
            
        }
}
