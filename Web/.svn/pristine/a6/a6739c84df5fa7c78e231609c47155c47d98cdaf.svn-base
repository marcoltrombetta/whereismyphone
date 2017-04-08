/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.Timestamp;
import obj.PhoneInfo;

/**
 *
 * @author marco
 */
public class PositionInfoModel {
        private int id;
	private double latitude;
	private double longitude;
	private Timestamp date;
	private double accuracy;
	private PhoneInfoModel phoneInfo;
        private PhoneNameModel phoneName;
        
        public PositionInfoModel(){}

        public PositionInfoModel(int id, double latitude, double longitude, Timestamp date, double accuracy, PhoneInfoModel phoneInfo, PhoneNameModel phoneName) {
            this.id = id;
            this.latitude = latitude;
            this.longitude = longitude;
            this.date = date;
            this.accuracy = accuracy;
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
	public PhoneInfoModel getPhoneInfo() {
		return phoneInfo;
	}
	public void setPhoneInfo(PhoneInfoModel phoneInfo) {
		this.phoneInfo = phoneInfo;
	}

        public PhoneNameModel getPhoneName() {
            return phoneName;
        }

        public void setPhoneName(PhoneNameModel phoneName) {
            this.phoneName = phoneName;
        }
}
