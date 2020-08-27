package com.khoa.dto;

public class GoiNhoDTO {
	private int id;
	private int mataikhoancannho;
	private long mataikhoangoinho;
	private String chuoimanguoigoinho;
	private String hotennguoigoinho;
	private String bietdanhgoinho;
	private int manganhang;
	
	public GoiNhoDTO() {
		
	}

	public GoiNhoDTO(int mataikhoancannho, long mataikhoangoinho, String chuoimanguoigoinho,
			String hotennguoigoinho, String bietdanhgoinho) {
		super();
		this.id = id;
		this.mataikhoancannho = mataikhoancannho;
		this.mataikhoangoinho = mataikhoangoinho;
		this.chuoimanguoigoinho = chuoimanguoigoinho;
		this.hotennguoigoinho = hotennguoigoinho;
		this.bietdanhgoinho = bietdanhgoinho;
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
}
