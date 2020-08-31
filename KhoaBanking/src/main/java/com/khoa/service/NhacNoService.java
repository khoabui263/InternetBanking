package com.khoa.service;

import java.util.List;

import com.khoa.dto.DanhSachNhacNoDTO;
import com.khoa.dto.NhacNoDTO;
import com.khoa.entity.GoiNho;
import com.khoa.entity.NhacNo;

public interface NhacNoService {
	List<NhacNo> findAll();
	NhacNo findById(int id);
	int add(NhacNoDTO dto);
	void update(NhacNo dto);
	void delete(int id);
	DanhSachNhacNoDTO getDanhSachNo(NhacNoDTO nhacNoDTO);
	DanhSachNhacNoDTO updateLoaiNhacNo(NhacNoDTO nhacNoDTO);
	DanhSachNhacNoDTO payDebt(NhacNoDTO nhacNoDTO);

}
