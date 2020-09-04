package com.khoa.dto;

public class ConfirmInfoDTO {
	private int mataikhoan;
	private String hoten;
	private String email;
	private String sodienthoai;
	private String sotien;
	private String matkhau;
	private int maloainguoidung;
	private int otp;
	
	public ConfirmInfoDTO() {
		
	}

	public ConfirmInfoDTO(int mataikhoan, String hoten, String email, String sodienthoai, String sotien, String matkhau,
			int maloainguoidung, int otp) {
		super();
		this.mataikhoan = mataikhoan;
		this.hoten = hoten;
		this.email = email;
		this.sodienthoai = sodienthoai;
		this.sotien = sotien;
		this.matkhau = matkhau;
		this.maloainguoidung = maloainguoidung;
		this.otp = otp;
	}

	public int getMataikhoan() {
		return mataikhoan;
	}

	public void setMataikhoan(int mataikhoan) {
		this.mataikhoan = mataikhoan;
	}

	public String getHoten() {
		return hoten;
	}

	public void setHoten(String hoten) {
		this.hoten = hoten;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSodienthoai() {
		return sodienthoai;
	}

	public void setSodienthoai(String sodienthoai) {
		this.sodienthoai = sodienthoai;
	}

	public String getSotien() {
		return sotien;
	}

	public void setSotien(String sotien) {
		this.sotien = sotien;
	}

	public String getMatkhau() {
		return matkhau;
	}

	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}

	public int getMaloainguoidung() {
		return maloainguoidung;
	}

	public void setMaloainguoidung(int maloainguoidung) {
		this.maloainguoidung = maloainguoidung;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}
}
