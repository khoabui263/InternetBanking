package com.khoa.service;

import java.util.List;

import com.khoa.entity.TaiKhoanLienNganHang;

public interface TaiKhoanLienNganHangService {
	List<TaiKhoanLienNganHang> findAll();
	TaiKhoanLienNganHang findById(int id);
	void add(TaiKhoanLienNganHang dto);
	TaiKhoanLienNganHang update(TaiKhoanLienNganHang dto);
	void delete(int id);
	
	TaiKhoanLienNganHang findByMataikhoan(long mataikhoan);
}
