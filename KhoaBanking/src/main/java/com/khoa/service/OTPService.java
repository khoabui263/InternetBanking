package com.khoa.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import com.khoa.dto.LichSuGiaoDichDTO;
import com.khoa.entity.GoiNho;
import com.khoa.entity.OTP;

public interface OTPService {
	List<OTP> findAll();
	OTP findById(int id);
	void add(OTP dto);
	void update(OTP dto);
	void delete(int id);
	
	List<OTP> findAllByOrderByThoiGianLuuAsc();
	OTP findByMaotpAndEmail(int maOTP, String email, String newPassWord);
	GoiNho confirmTransfer(LichSuGiaoDichDTO lichSuGiaoDichDTO);
	GoiNho confirmTransferLocalToRSA(LichSuGiaoDichDTO lichSuGiaoDichDTO) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException;
}
