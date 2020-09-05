package com.khoa.dto;

import java.util.ArrayList;
import java.util.List;

import com.khoa.entity.LichSuGiaoDich;

public class DanhSachLichSuGiaoDichDTO {
	private int manguoigui;
	private String email;
	private String ngaybatdau;
	private String ngayketthuc;
	private List<LichSuGiaoDich> danhSachChuyenTien;
	private List<LichSuGiaoDich> danhSachNhanTien;
	private List<LichSuGiaoDich> danhSachTraNo;
	private List<LichSuGiaoDich> danhSachNguoiKhacTraNo;
	
	public int getManguoigui() {
		return manguoigui;
	}
	public void setManguoigui(int manguoigui) {
		this.manguoigui = manguoigui;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNgaybatdau() {
		return ngaybatdau;
	}
	public void setNgaybatdau(String ngaybatdau) {
		this.ngaybatdau = ngaybatdau;
	}
	public String getNgayketthuc() {
		return ngayketthuc;
	}
	public void setNgayketthuc(String ngayketthuc) {
		this.ngayketthuc = ngayketthuc;
	}
	public List<LichSuGiaoDich> getDanhSachChuyenTien() {
		return danhSachChuyenTien;
	}
	public void setDanhSachChuyenTien(List<LichSuGiaoDich> danhSachChuyenTien) {
		this.danhSachChuyenTien = danhSachChuyenTien;
	}
	public List<LichSuGiaoDich> getDanhSachNhanTien() {
		return danhSachNhanTien;
	}
	public void setDanhSachNhanTien(List<LichSuGiaoDich> danhSachNhanTien) {
		this.danhSachNhanTien = danhSachNhanTien;
	}
	public List<LichSuGiaoDich> getDanhSachTraNo() {
		return danhSachTraNo;
	}
	public void setDanhSachTraNo(List<LichSuGiaoDich> danhSachTraNo) {
		this.danhSachTraNo = danhSachTraNo;
	}
	public List<LichSuGiaoDich> getDanhSachNguoiKhacTraNo() {
		return danhSachNguoiKhacTraNo;
	}
	public void setDanhSachNguoiKhacTraNo(List<LichSuGiaoDich> danhSachNguoiKhacTraNo) {
		this.danhSachNguoiKhacTraNo = danhSachNguoiKhacTraNo;
	}
	
	
}
