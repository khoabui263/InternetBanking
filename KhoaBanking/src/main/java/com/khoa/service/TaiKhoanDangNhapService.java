package com.khoa.service;

import java.util.List;

import com.khoa.dto.TaiKhoanDangNhapDTO;

public interface TaiKhoanDangNhapService {

	List<TaiKhoanDangNhapDTO> findAll();
	TaiKhoanDangNhapDTO findById(int id);
	void add(TaiKhoanDangNhapDTO dto);
	void update(TaiKhoanDangNhapDTO dto);
	void delete(int id);
	TaiKhoanDangNhapDTO findByEmailOrSodienthoai(String email, String soDienThoai);
	List<TaiKhoanDangNhapDTO> findAllDetailsTaiKhoanDangNhap();
	
}
