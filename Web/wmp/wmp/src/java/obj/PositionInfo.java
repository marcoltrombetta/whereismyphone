package obj;

import java.sql.Timestamp;
import model.PhoneInfoModel;
import model.PhoneNameModel;
import model.PositionInfoModel;

public class PositionInfo {
	private int id;
	private double latitude;
	private double longitude;
	private Timestamp date;
	private double accuracy;
	private int idPhoneInfo;
        private PhoneInfo phoneInfo;
	private PhoneName phoneName;
        
	public PositionInfo() {
		super();
	}

        public PositionInfo(int id, double latitude, double longitude, Timestamp date, double accuracy, int idPhoneInfo, PhoneInfo phoneInfo, PhoneName phoneName) {
            this.id = id;
            this.latitude = latitude;
            this.longitude = longitude;
            this.date = date;
            this.accuracy = accuracy;
            this.idPhoneInfo = idPhoneInfo;
            this.phoneInfo = phoneInfo;
            this.phoneName = phoneName;
        }
        	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public double getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}
	public int getIdPhoneInfo() {
		return idPhoneInfo;
	}
	public void setIdPhoneInfo(int idPhoneInfo) {
		this.idPhoneInfo = idPhoneInfo;
	}
        
        public PhoneInfo getPhoneInfo() {
		return phoneInfo;
	}
	public void setPhoneInfo(PhoneInfo phoneInfo) {
		this.phoneInfo = phoneInfo;
	}

        public PhoneName getPhoneName() {
            return phoneName;
        }

        public void setPhoneName(PhoneName phoneName) {
            this.phoneName = phoneName;
        }
 
        public PositionInfoModel toModel(){
           return new PositionInfoModel(
               this.id,
               this.latitude,
               this.longitude,
               this.date,
               this.accuracy,
               new PhoneInfoModel(
                   this.phoneInfo.getId(),
                   this.phoneInfo.getImei(),
                   this.phoneInfo.getModelo()              
               ),
               new PhoneNameModel(
                   this.phoneName.getId(),
                   this.phoneName.getIdPhoneInfo(),
                   this.phoneName.getDesc()
               )
           );
       }
}
