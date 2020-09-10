package com.khoa.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "taikhoanliennganhang")
public class TaiKhoanLienNganHang {

	@Id
	private long mataikhoan;
	private String hoten;
	private String sodu;
	private int manganhang;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manganhang", insertable = false, updatable = false)
	@JsonIgnore 
	private NganHang liennganhang;

	public long getMataikhoan() {
		return mataikhoan;
	}

	public void setMataikhoan(long mataikhoan) {
		this.mataikhoan = mataikhoan;
	}

	public String getHoten() {
		return hoten;
	}

	public void setHoten(String hoten) {
		this.hoten = hoten;
	}

	public String getSodu() {
		return sodu;
	}

	public void setSodu(String sodu) {
		this.sodu = sodu;
	}

	public int getManganhang() {
		return manganhang;
	}

	public void setManganhang(int manganhang) {
		this.manganhang = manganhang;
	}

	public NganHang getLiennganhang() {
		return liennganhang;
	}

	public void setLiennganhang(NganHang liennganhang) {
		this.liennganhang = liennganhang;
	}
}
