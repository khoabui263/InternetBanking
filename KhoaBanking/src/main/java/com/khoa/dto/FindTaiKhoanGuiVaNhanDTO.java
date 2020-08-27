package com.khoa.dto;

import java.util.List;

public class FindTaiKhoanGuiVaNhanDTO {
	private String hotentaikhoannhan;
	private List<TaiKhoanThanhToanDTO> danhsachtaikhoangui;
	
	public FindTaiKhoanGuiVaNhanDTO() {
		
	}
	
	public FindTaiKhoanGuiVaNhanDTO(String hotentaikhoannhan, List<TaiKhoanThanhToanDTO> danhsachtaikhoangui) {
		super();
		this.hotentaikhoannhan = hotentaikhoannhan;
		this.danhsachtaikhoangui = danhsachtaikhoangui;
	}
	
	public String getHotentaikhoannhan() {
		return hotentaikhoannhan;
	}
	public void setHotentaikhoannhan(String hotentaikhoannhan) {
		this.hotentaikhoannhan = hotentaikhoannhan;
	}
	public List<TaiKhoanThanhToanDTO> getDanhsachtaikhoangui() {
		return danhsachtaikhoangui;
	}
	public void setDanhsachtaikhoangui(List<TaiKhoanThanhToanDTO> danhsachtaikhoangui) {
		this.danhsachtaikhoangui = danhsachtaikhoangui;
	}
	
	
}
