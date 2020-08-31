package com.khoa.entity;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "taikhoandangnhap")
public class TaiKhoanDangNhap {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mataikhoan;
	private String hoten;
	private String email;
	private String sodienthoai;
	private String matkhau;
	private String refeshtoken;
	private int maloainguoidung;
	
	@Column(columnDefinition="int default 0")
	private int tinhtrangno;
	
	@Column(columnDefinition="int default 1")
	private int trangthai;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maloainguoidung", insertable = false, updatable = false)
	@JsonIgnore
	private LoaiNguoiDung loaiNguoiDung;
	
	@OneToMany(mappedBy = "taiKhoanDangNhap", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<TaiKhoanThanhToan> taiKhoanThanhToans;
	
	@OneToMany(mappedBy = "taikhoancannho", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<GoiNho> taikhoangoinho;
	
	@OneToMany(mappedBy = "thongtinnguoinhacno", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<NhacNo> nguoinhacno;
	
	@OneToMany(mappedBy = "thongtinnguoibino", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<NhacNo> nguoibino;
	
	public TaiKhoanDangNhap() {
		
	}

	public TaiKhoanDangNhap(int mataikhoan, String hoten, String email, String sodienthoai, String matkhau,
			String refeshtoken, int maloainguoidung, int trangthai) {
		super();
		this.mataikhoan = mataikhoan;
		this.hoten = hoten;
		this.email = email;
		this.sodienthoai = sodienthoai;
		this.matkhau = matkhau;
		this.refeshtoken = refeshtoken;
		this.maloainguoidung = maloainguoidung;
		this.trangthai = trangthai;
	}

	public TaiKhoanDangNhap(int mataikhoan, String hoten, String email, String sodienthoai, String matkhau,
			String refeshtoken, int maloainguoidung, int tinhtrangno, int trangthai, LoaiNguoiDung loaiNguoiDung) {
		super();
		this.mataikhoan = mataikhoan;
		this.hoten = hoten;
		this.email = email;
		this.sodienthoai = sodienthoai;
		this.matkhau = matkhau;
		this.refeshtoken = refeshtoken;
		this.maloainguoidung = maloainguoidung;
		this.tinhtrangno = tinhtrangno;
		this.trangthai = trangthai;
		this.loaiNguoiDung = loaiNguoiDung;
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

	public int getTinhtrangno() {
		return tinhtrangno;
	}

	public void setTinhtrangno(int tinhtrangno) {
		this.tinhtrangno = tinhtrangno;
	}

	public int getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}

	public LoaiNguoiDung getLoaiNguoiDung() {
		return loaiNguoiDung;
	}

	public void setLoaiNguoiDung(LoaiNguoiDung loaiNguoiDung) {
		this.loaiNguoiDung = loaiNguoiDung;
	}

	public List<TaiKhoanThanhToan> getTaiKhoanThanhToans() {
		return taiKhoanThanhToans;
	}

	public void setTaiKhoanThanhToans(List<TaiKhoanThanhToan> taiKhoanThanhToans) {
		this.taiKhoanThanhToans = taiKhoanThanhToans;
	}

	public List<GoiNho> getTaikhoangoinho() {
		return taikhoangoinho;
	}

	public void setTaikhoangoinho(List<GoiNho> taikhoangoinho) {
		this.taikhoangoinho = taikhoangoinho;
	}

	public List<NhacNo> getNguoinhacno() {
		return nguoinhacno;
	}

	public void setNguoinhacno(List<NhacNo> nguoinhacno) {
		this.nguoinhacno = nguoinhacno;
	}

	public List<NhacNo> getNguoibino() {
		return nguoibino;
	}

	public void setNguoibino(List<NhacNo> nguoibino) {
		this.nguoibino = nguoibino;
	}
}
