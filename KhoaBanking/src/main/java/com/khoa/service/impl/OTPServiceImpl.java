package com.khoa.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.khoa.entity.OTP;
import com.khoa.entity.TaiKhoanDangNhap;
import com.khoa.repository.OTPRepository;
import com.khoa.repository.TaiKhoanDangNhapRepository;
import com.khoa.service.OTPService;

@Service
public class OTPServiceImpl implements OTPService {
	
	@Autowired
	private OTPRepository oTPRepository;
	
	@Autowired
	private TaiKhoanDangNhapRepository taiKhoanDangNhapRepository;

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

	@Override
	public OTP findByMaotpAndEmail(int maOTP, String email, String newPassWord) {
		OTP otp = oTPRepository.findByMaotpAndEmail(maOTP,email);
		Date now = new Date();
		
//		long diff = now.getTime() - otp.getThoigianluu().getTime();
//		long diffMinutes = diff / (60 * 1000) % 60;
//		System.out.println(now.getTime());
//		System.out.println(otp.getThoigianluu().getTime());
//		System.out.println(diff);
//		System.out.println(diffMinutes);
		
		if(otp == null || now.getTime() < otp.getThoigianluu().getTime() || now.getTime() > (otp.getThoigianluu().getTime() + 3600000)) {
			return null;
		}
		
		TaiKhoanDangNhap taiKhoanDangNhap = taiKhoanDangNhapRepository.findByEmailOrSodienthoai(email, email);
		String hashPassWord = BCrypt.hashpw(newPassWord, BCrypt.gensalt(12));
		taiKhoanDangNhap.setMatkhau(hashPassWord);
		TaiKhoanDangNhap taiKhoanSauKhiUpdate = taiKhoanDangNhapRepository.save(taiKhoanDangNhap);
		oTPRepository.delete(otp);
			
		if(taiKhoanSauKhiUpdate == null) {
			return null;
		}
		
		return new OTP();
	}

}
