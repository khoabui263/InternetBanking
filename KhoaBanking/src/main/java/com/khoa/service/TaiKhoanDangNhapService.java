package com.khoa.service;

import java.util.List;

import com.khoa.dto.ConfirmInfoDTO;
import com.khoa.dto.TaiKhoanDangNhapDTO;
import com.khoa.entity.TaiKhoanDangNhap;

public interface TaiKhoanDangNhapService {

	List<TaiKhoanDangNhapDTO> findAll();
	TaiKhoanDangNhapDTO findById(int id);
	void add(TaiKhoanDangNhapDTO dto);
	void update(TaiKhoanDangNhapDTO dto);
	void delete(int id);
	TaiKhoanDangNhapDTO findByEmailOrSodienthoai(String email, String soDienThoai);
	List<TaiKhoanDangNhapDTO> findAllDetailsTaiKhoanDangNhap();
	TaiKhoanDangNhapDTO sendEmailChangePassWord(String email, String passWord);
	TaiKhoanDangNhapDTO sendEmailChuyenTien(String email);
	int sendEmailConfirmInfo(TaiKhoanDangNhapDTO taiKhoanDangNhapDTO);
	TaiKhoanDangNhapDTO confirmInfo(ConfirmInfoDTO confirmInfoDTO);
	int createEmployee(TaiKhoanDangNhap taiKhoanDangNhap);
	List<TaiKhoanDangNhap> findAllEmployee();
	List<TaiKhoanDangNhap> searchTaiKhoanNhanVien(String email);
	TaiKhoanDangNhap updateEmployee(TaiKhoanDangNhap taiKhoanDangNhap);
	TaiKhoanDangNhap deleteEmployee(int mataikhoan);
	TaiKhoanDangNhapDTO sendEmailCheckAccountExisted(String email);
}
