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
	
	@OneToMany(mappedBy = "nganhanggui", fetch = FetchType.LAZY)
	private List<LichSuGiaoDich> danhsachtaikhoangui;
	
	@OneToMany(mappedBy = "nganhangnhan", fetch = FetchType.LAZY)
	private List<LichSuGiaoDich> danhsachtaikhoannhan;
	
	@OneToMany(mappedBy = "nganhanggoinho", fetch = FetchType.LAZY)
	private List<GoiNho> nganhanggoinho;

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
	
	
	
}
