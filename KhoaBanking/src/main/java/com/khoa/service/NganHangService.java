package com.khoa.service;

import java.util.List;

import com.khoa.dto.NganHangDTO;
import com.khoa.entity.NganHang;

public interface NganHangService {
	List<NganHangDTO> findAll();
	NganHang findById(int id);
	void add(NganHang dto);
	void update(NganHang dto);
	void delete(int id);
	NganHangDTO findPartnerCode(String partnercode);
}
