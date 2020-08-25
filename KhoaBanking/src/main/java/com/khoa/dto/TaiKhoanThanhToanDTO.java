package com.khoa.dto;

public class TaiKhoanThanhToanDTO {

	private long mataikhoanthanhtoan;
	private int mataikhoandangnhap;
	private String sodu;
	private int trangthai;
	private String hoten;
	
	public TaiKhoanThanhToanDTO() {
		
	}
	
	public TaiKhoanThanhToanDTO(long mataikhoanthanhtoan, int mataikhoandangnhap, String sodu, int trangthai) {
		super();
		this.mataikhoanthanhtoan = mataikhoanthanhtoan;
		this.mataikhoandangnhap = mataikhoandangnhap;
		this.sodu = sodu;
		this.trangthai = trangthai;
	}

	public TaiKhoanThanhToanDTO(long mataikhoanthanhtoan, int mataikhoandangnhap, String sodu, int trangthai, String hoten) {
		super();
		this.mataikhoanthanhtoan = mataikhoanthanhtoan;
		this.mataikhoandangnhap = mataikhoandangnhap;
		this.sodu = sodu;
		this.trangthai = trangthai;
		this.hoten = hoten;
	}

	public long getMataikhoanthanhtoan() {
		return mataikhoanthanhtoan;
	}
	public void setMataikhoanthanhtoan(long mataikhoanthanhtoan) {
		this.mataikhoanthanhtoan = mataikhoanthanhtoan;
	}
	public int getMataikhoandangnhap() {
		return mataikhoandangnhap;
	}
	public void setMataikhoandangnhap(int mataikhoandangnhap) {
		this.mataikhoandangnhap = mataikhoandangnhap;
	}
	public String getSodu() {
		return sodu;
	}
	public void setSodu(String sodu) {
		this.sodu = sodu;
	}
	public int getTrangthai() {
		return trangthai;
	}
	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}

	public String getHoten() {
		return hoten;
	}

	public void setHoten(String hoten) {
		this.hoten = hoten;
	}
	
	
}
