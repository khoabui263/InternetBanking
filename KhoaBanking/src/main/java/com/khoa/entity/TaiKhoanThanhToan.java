package com.khoa.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "taikhoanthanhtoan")
public class TaiKhoanThanhToan {

	@Id
	private long mataikhoanthanhtoan;
	private int mataikhoandangnhap;
	private String sodu;
	
	@Column(columnDefinition="int default 1")
	private int trangthai;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mataikhoandangnhap", insertable = false, updatable = false)
//	@JsonIgnore
	private TaiKhoanDangNhap taiKhoanDangNhap;

	@OneToMany(mappedBy = "taikhoannguoigui", fetch = FetchType.LAZY)
//	@JsonIgnore
	private List<LichSuGiaoDich> giaodichdagui;
	
	@OneToMany(mappedBy = "taikhoannguoinhan", fetch = FetchType.LAZY)
//	@JsonIgnore
	private List<LichSuGiaoDich> giaodichdanhan;
	
	@OneToMany(mappedBy = "taikhoangoinho", fetch = FetchType.LAZY)
//	@JsonIgnore
	private List<GoiNho> goinho;
	
	public TaiKhoanThanhToan() {
		
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

	public TaiKhoanDangNhap getTaiKhoanDangNhap() {
		return taiKhoanDangNhap;
	}

	public void setTaiKhoanDangNhap(TaiKhoanDangNhap taiKhoanDangNhap) {
		this.taiKhoanDangNhap = taiKhoanDangNhap;
	}

	public List<LichSuGiaoDich> getGiaodichdagui() {
		return giaodichdagui;
	}

	public void setGiaodichdagui(List<LichSuGiaoDich> giaodichdagui) {
		this.giaodichdagui = giaodichdagui;
	}

	public List<LichSuGiaoDich> getGiaodichdanhan() {
		return giaodichdanhan;
	}

	public void setGiaodichdanhan(List<LichSuGiaoDich> giaodichdanhan) {
		this.giaodichdanhan = giaodichdanhan;
	}
	
	
	
	
}
