package com.khoa.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.khoa.dto.ConfirmInfoDTO;
import com.khoa.dto.TaiKhoanDangNhapDTO;
import com.khoa.entity.OTP;
import com.khoa.entity.TaiKhoanDangNhap;
import com.khoa.entity.TaiKhoanThanhToan;
import com.khoa.repository.OTPRepository;
import com.khoa.repository.TaiKhoanDangNhapRepository;
import com.khoa.repository.TaiKhoanThanhToanRepository;
import com.khoa.service.TaiKhoanDangNhapService;
import com.khoa.util.EmailTypesConstant;
import com.khoa.util.EmailUtil;

@Service
public class TaiKhoanDangNhapServiceImpl implements TaiKhoanDangNhapService {

	@Autowired
	private TaiKhoanDangNhapRepository taiKhoanDangNhapRepository;
	
	@Autowired
	private TaiKhoanThanhToanRepository taiKhoanThanhToanRepository;
	
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

	@Override
	public int sendEmailConfirmInfo(TaiKhoanDangNhapDTO taiKhoanDangNhapDTO) {
		TaiKhoanDangNhap taiKhoanDangNhap = taiKhoanDangNhapRepository.findByEmailOrSodienthoai(taiKhoanDangNhapDTO.getEmail(), taiKhoanDangNhapDTO.getSodienthoai());
		if(taiKhoanDangNhap != null) {
			return 1;
		}
		
		Random rd = new Random();
		int number = rd.nextInt((999999 - 100000) + 1) + 100000;
		oTPRepository.save(new OTP(number, taiKhoanDangNhapDTO.getEmail(), new Date()));
		int ketQua = emailUtil.transfer(number+"", taiKhoanDangNhapDTO.getEmail(), EmailTypesConstant.ADDNEWACCOUNT);
		if(ketQua == 0) {
			return 2;
		}
		return 0;
	}

	@Override
	public TaiKhoanDangNhapDTO confirmInfo(ConfirmInfoDTO confirmInfoDTO) {
		OTP otp = oTPRepository.findByMaotpAndEmail(confirmInfoDTO.getOtp(), confirmInfoDTO.getEmail());
		Date now = new Date();
		if(otp == null || now.getTime() < otp.getThoigianluu().getTime() || now.getTime() > (otp.getThoigianluu().getTime() + 3600000)) {
			oTPRepository.delete(otp);
			return null;
		}
		oTPRepository.delete(otp);
		String hashPassWord = BCrypt.hashpw(confirmInfoDTO.getMatkhau(), BCrypt.gensalt(12));
		
		TaiKhoanDangNhap taiKhoanDangNhap = new TaiKhoanDangNhap();
		taiKhoanDangNhap.setHoten(confirmInfoDTO.getHoten());
		taiKhoanDangNhap.setEmail(confirmInfoDTO.getEmail());
		taiKhoanDangNhap.setSodienthoai(confirmInfoDTO.getSodienthoai());
		taiKhoanDangNhap.setMatkhau(hashPassWord);
		taiKhoanDangNhap.setMaloainguoidung(3);
		taiKhoanDangNhap.setTrangthai(0);
		taiKhoanDangNhap.setTrangthai(1);
		
		TaiKhoanDangNhap taiKhoanMoi = taiKhoanDangNhapRepository.save(taiKhoanDangNhap);
		if(taiKhoanMoi == null) {
			return null;
		}
		
		TaiKhoanThanhToan lastestTaiKhoanThanhToan = taiKhoanThanhToanRepository.findTopByOrderByMataikhoanthanhtoanDesc();
		TaiKhoanThanhToan taiKhoanThanhToan = new TaiKhoanThanhToan();
		taiKhoanThanhToan.setMataikhoanthanhtoan(lastestTaiKhoanThanhToan.getMataikhoanthanhtoan() + 1);
		taiKhoanThanhToan.setMataikhoandangnhap(taiKhoanMoi.getMataikhoan());
		taiKhoanThanhToan.setSodu(confirmInfoDTO.getSotien());
		taiKhoanThanhToan.setTrangthai(1);
		
		TaiKhoanThanhToan newTaiKhoanThanhToan = taiKhoanThanhToanRepository.save(taiKhoanThanhToan);
		if(newTaiKhoanThanhToan == null) {
			return null;
		}
		
		return new TaiKhoanDangNhapDTO();
	}

	@Override
	public int createEmployee(TaiKhoanDangNhap taiKhoanDangNhap) {
		TaiKhoanDangNhap taiKhoanExisted= taiKhoanDangNhapRepository.findByEmailOrSodienthoai(taiKhoanDangNhap.getEmail(), taiKhoanDangNhap.getSodienthoai());
		if(taiKhoanExisted != null) {
			return 2;
		}
		
		String hashPassWord = BCrypt.hashpw(taiKhoanDangNhap.getMatkhau(), BCrypt.gensalt(12));
		TaiKhoanDangNhap taiKhoanNhanVien = new TaiKhoanDangNhap();
		taiKhoanNhanVien.setHoten(taiKhoanDangNhap.getHoten());
		taiKhoanNhanVien.setEmail(taiKhoanDangNhap.getEmail());
		taiKhoanNhanVien.setSodienthoai(taiKhoanDangNhap.getSodienthoai());
		taiKhoanNhanVien.setMatkhau(hashPassWord);
		taiKhoanNhanVien.setMaloainguoidung(2);
		taiKhoanNhanVien.setTinhtrangno(0);
		taiKhoanNhanVien.setTrangthai(1);
		
		TaiKhoanDangNhap created = taiKhoanDangNhapRepository.save(taiKhoanNhanVien);
		if(created == null) {
			return 1;
		}
		return 0;
	}

	@Override
	public List<TaiKhoanDangNhap> findAllEmployee() {
		return taiKhoanDangNhapRepository.findByMaloainguoidungAndTrangthai(2, 1);
	}

	@Override
	public List<TaiKhoanDangNhap> searchTaiKhoanNhanVien(String email) {
		return taiKhoanDangNhapRepository.searchTaiKhoanNhanVien(email);
	}

	@Override
	public TaiKhoanDangNhap updateEmployee(TaiKhoanDangNhap taiKhoanDangNhap) {
		TaiKhoanDangNhap taiKhoanExisted = taiKhoanDangNhapRepository.findByMataikhoanAndTrangthai(taiKhoanDangNhap.getMataikhoan(), 1);
		if(taiKhoanExisted == null) {
			return null;
		}
		
		taiKhoanExisted.setHoten(taiKhoanDangNhap.getHoten());
		taiKhoanExisted.setEmail(taiKhoanDangNhap.getEmail());
		taiKhoanExisted.setSodienthoai(taiKhoanDangNhap.getSodienthoai());
		
		return taiKhoanDangNhapRepository.save(taiKhoanExisted);
	}

	@Override
	public TaiKhoanDangNhap deleteEmployee(int mataikhoan) {
		TaiKhoanDangNhap taiKhoanExisted = taiKhoanDangNhapRepository.findByMataikhoanAndTrangthai(mataikhoan, 1);
		if(taiKhoanExisted == null) {
			return null;
		}
		
		taiKhoanExisted.setTrangthai(0);
		return taiKhoanDangNhapRepository.save(taiKhoanExisted);
	}

	@Override
	public TaiKhoanDangNhapDTO sendEmailCheckAccountExisted(String email) {
		TaiKhoanDangNhap taiKhoanExisted= taiKhoanDangNhapRepository.findByEmailOrSodienthoai(email, email);
		if(taiKhoanExisted == null) {
			return null;
		}
		
		Random rd = new Random();
		int number = rd.nextInt((999999 - 100000) + 1) + 100000;
		oTPRepository.save(new OTP(number, email, new Date()));
		
		emailUtil.transfer(number+"", email, EmailTypesConstant.CHECKACCOUNTEXISTED);
		return new TaiKhoanDangNhapDTO();
		
	}

}
