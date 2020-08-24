package com.khoa.dto;

public class TaiKhoanDangNhapDTO {
	private int mataikhoan;
	private String hoten;
	private String email;
	private String sodienthoai;
	private String matkhau;
	private String refeshtoken;
	private int maloainguoidung;
	private String tenloainguoidung;
	
	public TaiKhoanDangNhapDTO() {
		
	}

	public TaiKhoanDangNhapDTO(int mataikhoan, String hoten, String email, String sodienthoai, String matkhau,
			String refeshtoken, int maloainguoidung) {
		super();
		this.mataikhoan = mataikhoan;
		this.hoten = hoten;
		this.email = email;
		this.sodienthoai = sodienthoai;
		this.matkhau = matkhau;
		this.refeshtoken = refeshtoken;
		this.maloainguoidung = maloainguoidung;
	}

	public TaiKhoanDangNhapDTO(String hoten, String email, String sodienthoai, String tenloainguoidung) {
		super();
		this.hoten = hoten;
		this.email = email;
		this.sodienthoai = sodienthoai;
		this.tenloainguoidung = tenloainguoidung;
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

	public String getMatkhau() {
		return matkhau;
	}

	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}

	public String getRefeshtoken() {
		return refeshtoken;
	}

	public void setRefeshtoken(String refeshtoken) {
		this.refeshtoken = refeshtoken;
	}

	public int getMaloainguoidung() {
		return maloainguoidung;
	}

	public void setMaloainguoidung(int maloainguoidung) {
		this.maloainguoidung = maloainguoidung;
	}

	public String getTenloainguoidung() {
		return tenloainguoidung;
	}

	public void setTenloainguoidung(String tenloainguoidung) {
		this.tenloainguoidung = tenloainguoidung;
	}
	
}
