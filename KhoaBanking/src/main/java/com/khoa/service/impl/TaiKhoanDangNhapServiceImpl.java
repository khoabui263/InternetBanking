package com.khoa.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.khoa.dto.TaiKhoanDangNhapDTO;
import com.khoa.entity.OTP;
import com.khoa.entity.TaiKhoanDangNhap;
import com.khoa.repository.OTPRepository;
import com.khoa.repository.TaiKhoanDangNhapRepository;
import com.khoa.service.TaiKhoanDangNhapService;
import com.khoa.util.EmailTypesConstant;
import com.khoa.util.EmailUtil;

@Service
public class TaiKhoanDangNhapServiceImpl implements TaiKhoanDangNhapService {

	@Autowired
	private TaiKhoanDangNhapRepository taiKhoanDangNhapRepository;
	
	@Autowired
	private OTPRepository oTPRepository;
	
	@Autowired
	private EmailUtil emailUtil;

	@Override
	public List<TaiKhoanDangNhapDTO> findAll() {
		List<TaiKhoanDangNhapDTO> dto = new ArrayList<TaiKhoanDangNhapDTO>();

		List<TaiKhoanDangNhap> list = taiKhoanDangNhapRepository.findAll();
		for (TaiKhoanDangNhap item : list) {
			dto.add(new TaiKhoanDangNhapDTO(item.getMataikhoan(), item.getHoten(), item.getEmail(),
					item.getSodienthoai(), item.getMatkhau(), item.getRefeshtoken(), item.getMaloainguoidung()));
		}
		return dto;
	}

	@Override
	public TaiKhoanDangNhapDTO findById(int id) {
		TaiKhoanDangNhap taiKhoanDangNhap = taiKhoanDangNhapRepository.findById(id).get();

		return new TaiKhoanDangNhapDTO(taiKhoanDangNhap.getMataikhoan(), taiKhoanDangNhap.getHoten(),
				taiKhoanDangNhap.getEmail(), taiKhoanDangNhap.getSodienthoai(), taiKhoanDangNhap.getMatkhau(),
				taiKhoanDangNhap.getRefeshtoken(), taiKhoanDangNhap.getMaloainguoidung());
	}

	@Override
	public void add(TaiKhoanDangNhapDTO dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(TaiKhoanDangNhapDTO dto) {
		TaiKhoanDangNhap taiKhoanDangNhap = taiKhoanDangNhapRepository.findById(dto.getMataikhoan()).get();
		taiKhoanDangNhap.setRefeshtoken(dto.getRefeshtoken());
		taiKhoanDangNhapRepository.save(taiKhoanDangNhap);

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public TaiKhoanDangNhapDTO findByEmailOrSodienthoai(String email, String soDienThoai) {
		TaiKhoanDangNhap taiKhoanDangNhap = taiKhoanDangNhapRepository.findByEmailOrSodienthoai(email, soDienThoai);
		return new TaiKhoanDangNhapDTO(taiKhoanDangNhap.getMataikhoan(), taiKhoanDangNhap.getHoten(),
				taiKhoanDangNhap.getEmail(), taiKhoanDangNhap.getSodienthoai(), taiKhoanDangNhap.getMatkhau(),
				taiKhoanDangNhap.getRefeshtoken(), taiKhoanDangNhap.getMaloainguoidung());
	}

	@Override
	public List<TaiKhoanDangNhapDTO> findAllDetailsTaiKhoanDangNhap() {
		return taiKhoanDangNhapRepository.findAllDetailsTaiKhoanDangNhap();
	}

	@Override
	public TaiKhoanDangNhapDTO sendEmailChangePassWord(String email, String passWord) {
		TaiKhoanDangNhap taiKhoanDangNhap = taiKhoanDangNhapRepository.findByEmailOrSodienthoai(email, email);
		if(taiKhoanDangNhap != null) {
			if(BCrypt.checkpw(passWord, taiKhoanDangNhap.getMatkhau())) {
				Random rd = new Random();
				int number = rd.nextInt((999999 - 100000) + 1) + 100000;
				oTPRepository.save(new OTP(number, taiKhoanDangNhap.getEmail(), new Date()));
				
				emailUtil.transfer(number+"", taiKhoanDangNhap.getEmail(), EmailTypesConstant.CHANGE_PASSWORD);
				return new TaiKhoanDangNhapDTO();
			}
		}
		
		return null;
	}

	@Override
	public TaiKhoanDangNhapDTO sendEmailChuyenTien(String email) {
		Random rd = new Random();
		int number = rd.nextInt((999999 - 100000) + 1) + 100000;
		oTPRepository.save(new OTP(number, email, new Date()));
		
		emailUtil.transfer(number+"", email, EmailTypesConstant.TRANSFER);
		return new TaiKhoanDangNhapDTO();
	}

}
