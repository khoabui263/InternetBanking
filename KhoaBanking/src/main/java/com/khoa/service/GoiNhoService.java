package com.khoa.service;

import java.util.List;

import com.khoa.dto.GoiNhoDTO;
import com.khoa.entity.GoiNho;

public interface GoiNhoService {
	List<GoiNho> findAll();
	GoiNho findById(int id);
	GoiNho add(GoiNho dto);
	void update(GoiNho dto);
	void delete(int id);
	List<GoiNhoDTO> searchGoiNho(int maTaiKhoanCanNho, String chuoimanguoigoinho, int maNganHang);
	List<GoiNhoDTO> findByMataikhoancannhoAndTrangthai(int mataikhoancannho, int trangthai);
	GoiNho activateGoiNho(int mataikhoancannho, long mataikhoangoinho, int manganhang);
	String findGoiNhoExisted(int mataikhoancannho, long mataikhoangoinho, int manganhang);
	GoiNho deleteReminder(int id);
}
