package com.khoa.service;

import java.util.List;

import com.khoa.dto.DanhSachLichSuGiaoDichDTO;
import com.khoa.dto.LichSuGiaoDichDetailsDTO;
import com.khoa.entity.LichSuGiaoDich;


public interface LichSuGiaoDichService {
	List<LichSuGiaoDich> findAll();
	LichSuGiaoDich findById(int id);
	void add(LichSuGiaoDich dto);
	void update(LichSuGiaoDich dto);
	void delete(int id);
	DanhSachLichSuGiaoDichDTO getLichSuGiaoDich(int manguoigui);
	DanhSachLichSuGiaoDichDTO getChuyenTien(DanhSachLichSuGiaoDichDTO danhSachLichSuGiaoDichDTO);
	DanhSachLichSuGiaoDichDTO getNhanTien(DanhSachLichSuGiaoDichDTO danhSachLichSuGiaoDichDTO);
	DanhSachLichSuGiaoDichDTO getTraNo(DanhSachLichSuGiaoDichDTO danhSachLichSuGiaoDichDTO);
	DanhSachLichSuGiaoDichDTO getNguoiKhacTraNo(DanhSachLichSuGiaoDichDTO danhSachLichSuGiaoDichDTO);
	List<LichSuGiaoDich> getLichSuGiaoDichByEmployee(DanhSachLichSuGiaoDichDTO danhSachLichSuGiaoDichDTO);
	LichSuGiaoDichDetailsDTO getLichSuGiaoDichByEmployeeDetails(int id);
}
