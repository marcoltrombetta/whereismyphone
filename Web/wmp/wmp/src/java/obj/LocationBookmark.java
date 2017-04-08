package obj;

/**
 *
 * @author marco
 */
public class LocationBookmark {
    private int id;
    private int idPhoneInfo;
    private double latitude;
    private double longitude;
    private String desc;
    private PhoneInfo phoneInfo;
    
    public LocationBookmark() {
    }

    public LocationBookmark(int id, int idPhoneInfo, double latitude, double longitude, String desc) {
        this.id = id;
        this.idPhoneInfo = idPhoneInfo;
        this.latitude = latitude;
        this.longitude = longitude;
        this.desc = desc;
    }
    
      public LocationBookmark(int id, int idPhoneInfo, double latitude, double longitude, String desc, PhoneInfo phoneInfo) {
        this.id = id;
        this.idPhoneInfo = idPhoneInfo;
        this.latitude = latitude;
        this.longitude = longitude;
        this.desc = desc;
        this.phoneInfo=phoneInfo;
    }

    public int getId() {
        return id;
    }

    public int getIdPhoneInfo() {
        return idPhoneInfo;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getDesc() {
        return desc;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdPhoneInfo(int idPhoneInfo) {
        this.idPhoneInfo = idPhoneInfo;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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
        
}
