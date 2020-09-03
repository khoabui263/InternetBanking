package com.khoa.service;

import java.util.List;

import com.khoa.dto.FindTaiKhoanGuiVaNhanDTO;
import com.khoa.dto.NapTienDTO;
import com.khoa.dto.TaiKhoanThanhToanDTO;
import com.khoa.entity.TaiKhoanThanhToan;

public interface TaiKhoanThanhToanService {

	List<TaiKhoanThanhToan> findAll();
	TaiKhoanThanhToan findById(int id);
	void add(TaiKhoanThanhToan dto);
	void update(TaiKhoanThanhToan dto);
	void delete(int id);
	List<TaiKhoanThanhToanDTO> findByMataikhoandangnhap(int mataikhoandangnhap);
	List<TaiKhoanThanhToanDTO> findByMataikhoanthanhtoan(long mataikhoanthanhtoan);
	FindTaiKhoanGuiVaNhanDTO findTaiKhoanGuiVaNhan(TaiKhoanThanhToanDTO taiKhoanThanhToanDTO);
	int chargeMoney(NapTienDTO napTienDTO);
	
}
