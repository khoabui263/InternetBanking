package com.khoa.dto;

public class NganHangDTO {
	private int manganhang;
	private String tennganhang;
	private String partnercode;
	
	public NganHangDTO() {
		
	}

	public NganHangDTO(int manganhang, String tennganhang, String partnercode) {
		super();
		this.manganhang = manganhang;
		this.tennganhang = tennganhang;
		this.partnercode = partnercode;
	}

	public int getManganhang() {
		return manganhang;
	}

	public void setManganhang(int manganhang) {
		this.manganhang = manganhang;
	}

	public String getTennganhang() {
		return tennganhang;
	}

	public void setTennganhang(String tennganhang) {
		this.tennganhang = tennganhang;
	}

	public String getPartnercode() {
		return partnercode;
	}

	public void setPartnercode(String partnercode) {
		this.partnercode = partnercode;
	}
}
