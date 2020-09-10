package com.khoa.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "nganhang")
public class NganHang {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int manganhang;
	private String tennganhang;
	private String partnercode;
	private String privatekey;
	private String publickey;
	
	@OneToMany(mappedBy = "nganhanggui", fetch = FetchType.LAZY)
	private List<LichSuGiaoDich> danhsachtaikhoangui;
	
	@OneToMany(mappedBy = "nganhangnhan", fetch = FetchType.LAZY)
	private List<LichSuGiaoDich> danhsachtaikhoannhan;
	
	@OneToMany(mappedBy = "nganhanggoinho", fetch = FetchType.LAZY)
	private List<GoiNho> nganhanggoinho;
	
	@OneToMany(mappedBy = "liennganhang", fetch = FetchType.LAZY)
	private List<TaiKhoanLienNganHang> danhsachliennganhang;

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

	public List<LichSuGiaoDich> getDanhsachtaikhoangui() {
		return danhsachtaikhoangui;
	}

	public void setDanhsachtaikhoangui(List<LichSuGiaoDich> danhsachtaikhoangui) {
		this.danhsachtaikhoangui = danhsachtaikhoangui;
	}

	public List<LichSuGiaoDich> getDanhsachtaikhoannhan() {
		return danhsachtaikhoannhan;
	}

	public void setDanhsachtaikhoannhan(List<LichSuGiaoDich> danhsachtaikhoannhan) {
		this.danhsachtaikhoannhan = danhsachtaikhoannhan;
	}

	public List<GoiNho> getNganhanggoinho() {
		return nganhanggoinho;
	}

	public void setNganhanggoinho(List<GoiNho> nganhanggoinho) {
		this.nganhanggoinho = nganhanggoinho;
	}

	public List<TaiKhoanLienNganHang> getDanhsachliennganhang() {
		return danhsachliennganhang;
	}

	public void setDanhsachliennganhang(List<TaiKhoanLienNganHang> danhsachliennganhang) {
		this.danhsachliennganhang = danhsachliennganhang;
	}

	public String getPrivatekey() {
		return privatekey;
	}

	public void setPrivatekey(String privatekey) {
		this.privatekey = privatekey;
	}

	public String getPublickey() {
		return publickey;
	}

	public void setPublickey(String publickey) {
		this.publickey = publickey;
	}
}
