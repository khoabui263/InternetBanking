package com.khoa.service;

import java.util.List;

import com.khoa.entity.OTP;

public interface OTPService {
	List<OTP> findAll();
	OTP findById(int id);
	void add(OTP dto);
	void update(OTP dto);
	void delete(int id);
	
	List<OTP> findAllByOrderByThoiGianLuuAsc();
}
