package com.khoa.dto;

public class NhacNoDTO {
	private int id;
	private long mataikhoannhacno;
	private int manguoinhacno;
	private String hotennguoinhacno;
	private String emailsodienthoai;
	private long mataikhoanduocnhacno;
	private int manguoibino;
	private String hotennguoibino;
	private String sotienno;
	private String noidungnhacno;
	private int maloainhacno;
	
	public NhacNoDTO() {
		
	}
	
	public NhacNoDTO(long mataikhoannhacno, int manguoinhacno, String emailsodienthoai,
			long mataikhoanduocnhacno, int manguoibino, String sotienno, String noidungnhacno, int maloainhacno) {
		super();
		this.emailsodienthoai = emailsodienthoai;
		this.mataikhoannhacno = mataikhoannhacno;
		this.manguoinhacno = manguoinhacno;
		this.mataikhoanduocnhacno = mataikhoanduocnhacno;
		this.manguoibino = manguoibino;
		this.sotienno = sotienno;
		this.noidungnhacno = noidungnhacno;
		this.maloainhacno = maloainhacno;
	}

	public NhacNoDTO(int id, long mataikhoannhacno, int manguoinhacno, String hotennguoinhacno, String emailsodienthoai,
			long mataikhoanduocnhacno, int manguoibino, String hotennguoibino, String sotienno, String noidungnhacno,
			int maloainhacno) {
		super();
		this.id = id;
		this.mataikhoannhacno = mataikhoannhacno;
		this.manguoinhacno = manguoinhacno;
		this.hotennguoinhacno = hotennguoinhacno;
		this.emailsodienthoai = emailsodienthoai;
		this.mataikhoanduocnhacno = mataikhoanduocnhacno;
		this.manguoibino = manguoibino;
		this.hotennguoibino = hotennguoibino;
		this.sotienno = sotienno;
		this.noidungnhacno = noidungnhacno;
		this.maloainhacno = maloainhacno;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmailsodienthoai() {
		return emailsodienthoai;
	}
	public void setEmailsodienthoai(String emailsodienthoai) {
		this.emailsodienthoai = emailsodienthoai;
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

	public String getHotennguoinhacno() {
		return hotennguoinhacno;
	}

	public void setHotennguoinhacno(String hotennguoinhacno) {
		this.hotennguoinhacno = hotennguoinhacno;
	}

	public String getHotennguoibino() {
		return hotennguoibino;
	}

	public void setHotennguoibino(String hotennguoibino) {
		this.hotennguoibino = hotennguoibino;
	}	
}
