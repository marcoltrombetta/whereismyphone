package model;

import obj.*;

public class PhoneInfoModel {
	private int id;
	private String imei;
	private String modelo;
        private UserModel user;
        
	public PhoneInfoModel() {
		super();
	}
	
	public PhoneInfoModel(int id, String imei, String modelo) {
		super();
		this.id = id;
		this.imei = imei;
		this.modelo = modelo;
	}
        
        public PhoneInfoModel(int id, String imei, String modelo, UserModel user) {
		super();
		this.id = id;
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
