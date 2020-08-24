package com.khoa.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "loainguoidung")
public class LoaiNguoiDung {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int maloainguoidung;
	private String tenloainguoidung;
	private String mota;
	
	@Column(columnDefinition="int default 1")
	private int trangthai;
	
	@OneToMany(mappedBy = "loaiNguoiDung", fetch = FetchType.LAZY)
	private List<TaiKhoanDangNhap> users;
	
	public LoaiNguoiDung() {
		
	}

	public LoaiNguoiDung(int maloainguoidung, String tenloainguoidung, String mota, int trangthai,
			List<TaiKhoanDangNhap> users) {
		super();
		this.maloainguoidung = maloainguoidung;
		this.tenloainguoidung = tenloainguoidung;
		this.mota = mota;
		this.trangthai = trangthai;
		this.users = users;
	}

	public long getMaloainguoidung() {
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

	public String getMota() {
		return mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	public int getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}

	public List<TaiKhoanDangNhap> getUsers() {
		return users;
	}

	public void setUsers(List<TaiKhoanDangNhap> users) {
		this.users = users;
	}

}
