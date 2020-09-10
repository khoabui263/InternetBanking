package com.khoa.dto;

import java.util.Date;

public class LienNganHangDTO {
	private String email;
	private String partnerCode;
	private long timeStampe;
	private int mataikhoandangnhap;
	private long mataikhoanthanhtoan;
	private String chuoiMaHoa;
	private String hoten;
	private String soTienChuyen;
	private String noiDungChuyen;
	private int status;
	private String message;
	private byte[] signature;
	
	public LienNganHangDTO() {
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPartnerCode() {
		return partnerCode;
	}

	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}

	public long getTimeStampe() {
		return timeStampe;
	}

	public void setTimeStampe(long timeStampe) {
		this.timeStampe = timeStampe;
	}

	public int getMataikhoandangnhap() {
		return mataikhoandangnhap;
	}

	public void setMataikhoandangnhap(int mataikhoandangnhap) {
		this.mataikhoandangnhap = mataikhoandangnhap;
	}

	public long getMataikhoanthanhtoan() {
		return mataikhoanthanhtoan;
	}

	public void setMataikhoanthanhtoan(long mataikhoanthanhtoan) {
		this.mataikhoanthanhtoan = mataikhoanthanhtoan;
	}

	public String getChuoiMaHoa() {
		return chuoiMaHoa;
	}

	public void setChuoiMaHoa(String chuoiMaHoa) {
		this.chuoiMaHoa = chuoiMaHoa;
	}

	public String getHoten() {
		return hoten;
	}

	public void setHoten(String hoten) {
		this.hoten = hoten;
	}

	public String getSoTienChuyen() {
		return soTienChuyen;
	}

	public void setSoTienChuyen(String soTienChuyen) {
		this.soTienChuyen = soTienChuyen;
	}

	public String getNoiDungChuyen() {
		return noiDungChuyen;
	}

	public void setNoiDungChuyen(String noiDungChuyen) {
		this.noiDungChuyen = noiDungChuyen;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public byte[] getSignature() {
		return signature;
	}

	public void setSignature(byte[] signature) {
		this.signature = signature;
	}

}
