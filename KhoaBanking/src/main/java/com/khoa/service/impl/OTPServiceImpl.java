package com.khoa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoa.entity.OTP;
import com.khoa.repository.OTPRepository;
import com.khoa.service.OTPService;

@Service
public class OTPServiceImpl implements OTPService {
	
	@Autowired
	private OTPRepository oTPRepository;

	@Override
	public List<OTP> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OTP findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(OTP dto) {
		oTPRepository.save(dto);
		
	}

	@Override
	public void update(OTP dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<OTP> findAllByOrderByThoiGianLuuAsc() {
		return oTPRepository.findAllByOrderByThoigianluuAsc();
	}

}
