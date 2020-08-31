package com.khoa.dto;

import java.util.List;

public class DanhSachNhacNoDTO {
	private List<NhacNoDTO> danhSachTaoNo;
	private List<NhacNoDTO> danhSachChuaThanhToanNo;
	private List<NhacNoDTO> danhSachTuChoiThanhToan;
	
	public List<NhacNoDTO> getDanhSachTaoNo() {
		return danhSachTaoNo;
	}
	public void setDanhSachTaoNo(List<NhacNoDTO> danhSachTaoNo) {
		this.danhSachTaoNo = danhSachTaoNo;
	}
	public List<NhacNoDTO> getDanhSachChuaThanhToanNo() {
		return danhSachChuaThanhToanNo;
	}
	public void setDanhSachChuaThanhToanNo(List<NhacNoDTO> danhSachChuaThanhToanNo) {
		this.danhSachChuaThanhToanNo = danhSachChuaThanhToanNo;
	}
	public List<NhacNoDTO> getDanhSachTuChoiThanhToan() {
		return danhSachTuChoiThanhToan;
	}
	public void setDanhSachTuChoiThanhToan(List<NhacNoDTO> danhSachTuChoiThanhToan) {
		this.danhSachTuChoiThanhToan = danhSachTuChoiThanhToan;
	}
}
