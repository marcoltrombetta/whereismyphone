package phone.gps.obj;

public class PhoneInfo {
	private int id;
	private String imei;
	private String modelo;

	public PhoneInfo() {
		super();
	}
	
	public PhoneInfo(int id, String imei, String modelo) {
		super();
		this.id = id;
		this.imei = imei;
		this.modelo = modelo;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

}
