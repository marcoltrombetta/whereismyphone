package obj;

import model.PhoneInfoModel;

public class PhoneInfo {
	private int id;
	private int idUsuario;
	private String imei;
	private String modelo;
	private User user;
        
	public PhoneInfo() {
		super();
	}
	
	public PhoneInfo(int id, int idUsuario, String imei, String modelo) {
		super();
		this.id = id;
		this.idUsuario = idUsuario;
		this.imei = imei;
		this.modelo = modelo;
	}
        
        public PhoneInfo(int id, int idUsuario, String imei, String modelo, User user) {
		super();
		this.id = id;
		this.idUsuario = idUsuario;
		this.imei = imei;
		this.modelo = modelo;
                this.user=user;
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
        
        public PhoneInfoModel toModel(){
            if(user==null){
                return new PhoneInfoModel(
                    this.id,
                    this.imei,
                    this.modelo
                );
            }else{
                return new PhoneInfoModel(
                    this.id,
                    this.imei,
                    this.modelo,
                    this.user.toModel()
                );
            }
        }
}
