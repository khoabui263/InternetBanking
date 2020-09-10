package com.khoa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoa.entity.TaiKhoanLienNganHang;
import com.khoa.repository.TaiKhoanLienNganHangRepository;
import com.khoa.service.TaiKhoanLienNganHangService;

@Service
public class TaiKhoanLienNganHangServiceImpl implements TaiKhoanLienNganHangService {
	
	@Autowired
	private TaiKhoanLienNganHangRepository taiKhoanLienNganHangRepository;

	@Override
	public List<TaiKhoanLienNganHang> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaiKhoanLienNganHang findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(TaiKhoanLienNganHang dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TaiKhoanLienNganHang update(TaiKhoanLienNganHang dto) {
		return taiKhoanLienNganHangRepository.save(dto);
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TaiKhoanLienNganHang findByMataikhoan(long mataikhoan) {
		return taiKhoanLienNganHangRepository.findByMataikhoan(mataikhoan);
	}

}
