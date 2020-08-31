package com.khoa.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "nhacno")
public class NhacNo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private long mataikhoannhacno;
	private int manguoinhacno;
	private long mataikhoanduocnhacno;
	private int manguoibino;
	private String sotienno;
	private String noidungnhacno;
	private int maloainhacno;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mataikhoannhacno", insertable = false, updatable = false)
	private TaiKhoanThanhToan taikhoannhacno;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manguoinhacno", insertable = false, updatable = false)
	private TaiKhoanDangNhap thongtinnguoinhacno;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mataikhoanduocnhacno", insertable = false, updatable = false)
	private TaiKhoanThanhToan taikhoanduocnhacno;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manguoibino", insertable = false, updatable = false)
	private TaiKhoanDangNhap thongtinnguoibino;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maloainhacno", insertable = false, updatable = false)
	private LoaiNhacNo nhacno;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getMataikhoannhacno() {
		return mataikhoannhacno;
	}

	public void setMataikhoannhacno(long mataikhoannhacno) {
		this.mataikhoannhacno = mataikhoannhacno;
	}

	public int getManguoinhacno() {
		return manguoinhacno;
	}

	public void setManguoinhacno(int manguoinhacno) {
		this.manguoinhacno = manguoinhacno;
	}

	public long getMataikhoanduocnhacno() {
		return mataikhoanduocnhacno;
	}

	public void setMataikhoanduocnhacno(long mataikhoanduocnhacno) {
		this.mataikhoanduocnhacno = mataikhoanduocnhacno;
	}

	public int getManguoibino() {
		return manguoibino;
	}

	public void setManguoibino(int manguoibino) {
		this.manguoibino = manguoibino;
	}

	public String getSotienno() {
		return sotienno;
	}

	public void setSotienno(String sotienno) {
		this.sotienno = sotienno;
	}

	public String getNoidungnhacno() {
		return noidungnhacno;
	}

	public void setNoidungnhacno(String noidungnhacno) {
		this.noidungnhacno = noidungnhacno;
	}

	public int getMaloainhacno() {
		return maloainhacno;
	}

	public void setMaloainhacno(int maloainhacno) {
		this.maloainhacno = maloainhacno;
	}

	public TaiKhoanThanhToan getTaikhoannhacno() {
		return taikhoannhacno;
	}

	public void setTaikhoannhacno(TaiKhoanThanhToan taikhoannhacno) {
		this.taikhoannhacno = taikhoannhacno;
	}

	public TaiKhoanThanhToan getTaikhoanduocnhacno() {
		return taikhoanduocnhacno;
	}

	public void setTaikhoanduocnhacno(TaiKhoanThanhToan taikhoanduocnhacno) {
		this.taikhoanduocnhacno = taikhoanduocnhacno;
	}

	public TaiKhoanDangNhap getThongtinnguoinhacno() {
		return thongtinnguoinhacno;
	}

	public void setThongtinnguoinhacno(TaiKhoanDangNhap thongtinnguoinhacno) {
		this.thongtinnguoinhacno = thongtinnguoinhacno;
	}

	public TaiKhoanDangNhap getThongtinnguoibino() {
		return thongtinnguoibino;
	}

	public void setThongtinnguoibino(TaiKhoanDangNhap thongtinnguoibino) {
		this.thongtinnguoibino = thongtinnguoibino;
	}

	public LoaiNhacNo getNhacno() {
		return nhacno;
	}

	public void setNhacno(LoaiNhacNo nhacno) {
		this.nhacno = nhacno;
	}
}
