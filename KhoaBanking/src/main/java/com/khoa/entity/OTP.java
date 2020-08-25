package com.khoa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "otp")
public class OTP {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int maotp;
	private String email;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date thoigianluu;
	
	public OTP() {
		
	}
	
	public OTP(int maotp, String email, Date thoigianluu) {
		super();
		this.maotp = maotp;
		this.email = email;
		this.thoigianluu = thoigianluu;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMaotp() {
		return maotp;
	}

	public void setMaotp(int maotp) {
		this.maotp = maotp;
	}

	public Date getThoigianluu() {
		return thoigianluu;
	}

	public void setThoigianluu(Date thoigianluu) {
		this.thoigianluu = thoigianluu;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
