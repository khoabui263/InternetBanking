package com.khoa.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "goinho")
public class GoiNho {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int mataikhoancannho;
	private long mataikhoangoinho;
	private String chuoimanguoigoinho;
	private String hotennguoigoinho;
	private String bietdanhgoinho;
	private int manganhang;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mataikhoancannho", insertable = false, updatable = false)
	private TaiKhoanDangNhap taikhoancannho;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mataikhoangoinho", insertable = false, updatable = false)
	private TaiKhoanThanhToan taikhoangoinho;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manganhang", insertable = false, updatable = false)
	private NganHang nganhanggoinho;

	public GoiNho() {

	}

	public GoiNho(int mataikhoancannho) {
		super();
		this.mataikhoancannho = mataikhoancannho;
	}

	public GoiNho(int id, int mataikhoancannho, long mataikhoangoinho, String chuoimanguoigoinho, String hotennguoigoinho,
			String bietdanhgoinho, int manganhang) {
		super();
		this.id = id;
		this.mataikhoancannho = mataikhoancannho;
		this.mataikhoangoinho = mataikhoangoinho;
		this.chuoimanguoigoinho = chuoimanguoigoinho;
		this.hotennguoigoinho = hotennguoigoinho;
		this.bietdanhgoinho = bietdanhgoinho;
		this.manganhang = manganhang;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMataikhoancannho() {
		return mataikhoancannho;
	}

	public void setMataikhoancannho(int mataikhoancannho) {
		this.mataikhoancannho = mataikhoancannho;
	}

	public long getMataikhoangoinho() {
		return mataikhoangoinho;
	}

	public void setMataikhoangoinho(long mataikhoangoinho) {
		this.mataikhoangoinho = mataikhoangoinho;
	}

	public String getChuoimanguoigoinho() {
		return chuoimanguoigoinho;
	}

	public void setChuoimanguoigoinho(String chuoimanguoigoinho) {
		this.chuoimanguoigoinho = chuoimanguoigoinho;
	}

	public String getHotennguoigoinho() {
		return hotennguoigoinho;
	}

	public void setHotennguoigoinho(String hotennguoigoinho) {
		this.hotennguoigoinho = hotennguoigoinho;
	}

	public String getBietdanhgoinho() {
		return bietdanhgoinho;
	}

	public void setBietdanhgoinho(String bietdanhgoinho) {
		this.bietdanhgoinho = bietdanhgoinho;
	}

	public int getManganhang() {
		return manganhang;
	}

	public void setManganhang(int manganhang) {
		this.manganhang = manganhang;
	}

	public TaiKhoanDangNhap getTaikhoancannho() {
		return taikhoancannho;
	}

	public void setTaikhoancannho(TaiKhoanDangNhap taikhoancannho) {
		this.taikhoancannho = taikhoancannho;
	}

	public TaiKhoanThanhToan getTaikhoangoinho() {
		return taikhoangoinho;
	}

	public void setTaikhoangoinho(TaiKhoanThanhToan taikhoangoinho) {
		this.taikhoangoinho = taikhoangoinho;
	}

	public NganHang getNganhanggoinho() {
		return nganhanggoinho;
	}

	public void setNganhanggoinho(NganHang nganhanggoinho) {
		this.nganhanggoinho = nganhanggoinho;
	}

}
