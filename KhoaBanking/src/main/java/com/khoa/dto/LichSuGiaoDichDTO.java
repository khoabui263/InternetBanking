package com.khoa.dto;

import java.util.Date;

public class LichSuGiaoDichDTO {
	private int mataikhoancannho;
	private String email;
	private long mataikhoannguoigui;
	private String tennguoigui;
	private long mataikhoannguoinhan;
	private String tennguoinhan;
	private String sotiengiaodich;
	private String noidungchuyenkhoan;
	private int nguoitraphi;
	private int manganhanggui;
	private int manganhangnhan;
	private Date ngaygiaodich;
	private String signature;
	private int maloaigiaodich;
	private int otp;
	
	public LichSuGiaoDichDTO() {
		
	}

	public LichSuGiaoDichDTO(String email, long mataikhoannguoigui, String tennguoigui, long mataikhoannguoinhan,
			String tennguoinhan, String sotiengiaodich, String noidungchuyenkhoan, int nguoitraphi, int manganhanggui,
			int manganhangnhan, Date ngaygiaodich, String signature, int maloaigiaodich, int otp) {
		super();
		this.email = email;
		this.mataikhoannguoigui = mataikhoannguoigui;
		this.tennguoigui = tennguoigui;
		this.mataikhoannguoinhan = mataikhoannguoinhan;
		this.tennguoinhan = tennguoinhan;
		this.sotiengiaodich = sotiengiaodich;
		this.noidungchuyenkhoan = noidungchuyenkhoan;
		this.nguoitraphi = nguoitraphi;
		this.manganhanggui = manganhanggui;
		this.manganhangnhan = manganhangnhan;
		this.ngaygiaodich = ngaygiaodich;
		this.signature = signature;
		this.maloaigiaodich = maloaigiaodich;
		this.otp = otp;
	}

	public int getMataikhoancannho() {
		return mataikhoancannho;
	}

	public void setMataikhoancannho(int mataikhoancannho) {
		this.mataikhoancannho = mataikhoancannho;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public int getNguoitraphi() {
		return nguoitraphi;
	}

	public void setNguoitraphi(int nguoitraphi) {
		this.nguoitraphi = nguoitraphi;
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

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}
}
