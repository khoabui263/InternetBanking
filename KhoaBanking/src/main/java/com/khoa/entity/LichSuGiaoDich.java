package com.khoa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "lichsugiaodich")
public class LichSuGiaoDich {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private long mataikhoannguoigui;
	private String tennguoigui;
	private long mataikhoannguoinhan;
	private String tennguoinhan;
	private String sotiengiaodich;
	private String noidungchuyenkhoan;
	private int manganhanggui;
	private int manganhangnhan;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date ngaygiaodich;
	
	private String signature;
	private int maloaigiaodich;
	
	@Column(columnDefinition="int default 1")
	private int trangthai;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mataikhoannguoigui", insertable = false, updatable = false)
	private TaiKhoanThanhToan taikhoannguoigui;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mataikhoannguoinhan", insertable = false, updatable = false)
	private TaiKhoanThanhToan taikhoannguoinhan;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manganhanggui", insertable = false, updatable = false)
	private NganHang nganhanggui;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manganhangnhan", insertable = false, updatable = false)
	private NganHang nganhangnhan;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maloaigiaodich", insertable = false, updatable = false)
	private LoaiGiaoDich loaigiaodich;

	public LichSuGiaoDich() {
		
	}

	public LichSuGiaoDich(long mataikhoannguoigui, String tennguoigui, long mataikhoannguoinhan,
			String tennguoinhan, String sotiengiaodich, String noidungchuyenkhoan, int manganhanggui,
			int manganhangnhan, Date ngaygiaodich, String signature, int maloaigiaodich, int trangthai) {
		super();
		this.mataikhoannguoigui = mataikhoannguoigui;
		this.tennguoigui = tennguoigui;
		this.mataikhoannguoinhan = mataikhoannguoinhan;
		this.tennguoinhan = tennguoinhan;
		this.sotiengiaodich = sotiengiaodich;
		this.noidungchuyenkhoan = noidungchuyenkhoan;
		this.manganhanggui = manganhanggui;
		this.manganhangnhan = manganhangnhan;
		this.ngaygiaodich = ngaygiaodich;
		this.signature = signature;
		this.maloaigiaodich = maloaigiaodich;
		this.trangthai = trangthai;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getMataikhoannguoigui() {
		return mataikhoannguoigui;
	}

	public void setMataikhoannguoigui(long mataikhoannguoigui) {
		this.mataikhoannguoigui = mataikhoannguoigui;
	}

	public String getTennguoigui() {
		return tennguoigui;
	}

	public void setTennguoigui(String tennguoigui) {
		this.tennguoigui = tennguoigui;
	}

	public long getMataikhoannguoinhan() {
		return mataikhoannguoinhan;
	}

	public void setMataikhoannguoinhan(long mataikhoannguoinhan) {
		this.mataikhoannguoinhan = mataikhoannguoinhan;
	}

	public String getTennguoinhan() {
		return tennguoinhan;
	}

	public void setTennguoinhan(String tennguoinhan) {
		this.tennguoinhan = tennguoinhan;
	}

	public String getSotiengiaodich() {
		return sotiengiaodich;
	}

	public void setSotiengiaodich(String sotiengiaodich) {
		this.sotiengiaodich = sotiengiaodich;
	}

	public String getNoidungchuyenkhoan() {
		return noidungchuyenkhoan;
	}

	public void setNoidungchuyenkhoan(String noidungchuyenkhoan) {
		this.noidungchuyenkhoan = noidungchuyenkhoan;
	}

	public int getManganhanggui() {
		return manganhanggui;
	}

	public void setManganhanggui(int manganhanggui) {
		this.manganhanggui = manganhanggui;
	}

	public int getManganhangnhan() {
		return manganhangnhan;
	}

	public void setManganhangnhan(int manganhangnhan) {
		this.manganhangnhan = manganhangnhan;
	}

	public Date getNgaygiaodich() {
		return ngaygiaodich;
	}

	public void setNgaygiaodich(Date ngaygiaodich) {
		this.ngaygiaodich = ngaygiaodich;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public int getMaloaigiaodich() {
		return maloaigiaodich;
	}

	public void setMaloaigiaodich(int maloaigiaodich) {
		this.maloaigiaodich = maloaigiaodich;
	}

	public int getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}

	public TaiKhoanThanhToan getTaikhoannguoigui() {
		return taikhoannguoigui;
	}

	public void setTaikhoannguoigui(TaiKhoanThanhToan taikhoannguoigui) {
		this.taikhoannguoigui = taikhoannguoigui;
	}

	public TaiKhoanThanhToan getTaikhoannguoinhan() {
		return taikhoannguoinhan;
	}

	public void setTaikhoannguoinhan(TaiKhoanThanhToan taikhoannguoinhan) {
		this.taikhoannguoinhan = taikhoannguoinhan;
	}

	public NganHang getNganhanggui() {
		return nganhanggui;
	}

	public void setNganhanggui(NganHang nganhanggui) {
		this.nganhanggui = nganhanggui;
	}

	public NganHang getNganhangnhan() {
		return nganhangnhan;
	}

	public void setNganhangnhan(NganHang nganhangnhan) {
		this.nganhangnhan = nganhangnhan;
	}

	public LoaiGiaoDich getLoaigiaodich() {
		return loaigiaodich;
	}

	public void setLoaigiaodich(LoaiGiaoDich loaigiaodich) {
		this.loaigiaodich = loaigiaodich;
	}
	
}
