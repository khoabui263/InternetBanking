package com.khoa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import com.khoa.dto.NganHangDTO;
import com.khoa.entity.NganHang;
import com.khoa.repository.NganHangRepository;
import com.khoa.service.NganHangService;

@Service
public class NganHangServiceImpl implements NganHangService{
	
	@Autowired
	private NganHangRepository nganHangRepository;

	@Override
	public List<NganHangDTO> findAll() {
		List<NganHangDTO> dto = new ArrayList<NganHangDTO>();
		List<NganHang> danhSachNganHang = nganHangRepository.findAll();
		
		for (NganHang nganHang : danhSachNganHang) {
			dto.add(new NganHangDTO(nganHang.getManganhang(), nganHang.getTennganhang(), nganHang.getPartnercode()));
		}
		return dto;
	}

	@Override
	public NganHang findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(NganHang dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(NganHang dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public NganHangDTO findPartnerCode(String partnercode) {
		NganHang existed = nganHangRepository.findPartnerCode(partnercode);
		NganHangDTO dto = new NganHangDTO();
		dto.setManganhang(existed.getManganhang());
		dto.setTennganhang(existed.getTennganhang());
		dto.setPartnercode(existed.getPartnercode());
		
		return dto;		
	}

}
