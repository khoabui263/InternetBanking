package com.khoa.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.khoa.dto.LichSuGiaoDichDTO;
import com.khoa.entity.GoiNho;
import com.khoa.entity.LichSuGiaoDich;
import com.khoa.entity.OTP;
import com.khoa.entity.TaiKhoanDangNhap;
import com.khoa.entity.TaiKhoanThanhToan;
import com.khoa.repository.GoiNhoRepository;
import com.khoa.repository.LichSuGiaoDichRepository;
import com.khoa.repository.OTPRepository;
import com.khoa.repository.TaiKhoanDangNhapRepository;
import com.khoa.repository.TaiKhoanThanhToanRepository;
import com.khoa.service.OTPService;

@Service
public class OTPServiceImpl implements OTPService {
	
	@Autowired
	private OTPRepository oTPRepository;
	
	@Autowired
	private TaiKhoanDangNhapRepository taiKhoanDangNhapRepository;
	
	@Autowired
	private TaiKhoanThanhToanRepository taiKhoanThanhToanRepository;
	
	@Autowired
	private LichSuGiaoDichRepository lichSuGiaoDichRepository;
	
	@Autowired
	private GoiNhoRepository goiNhoRepository;

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

	@Override
	public GoiNho confirmTransfer(LichSuGiaoDichDTO lichSuGiaoDichDTO) {
		OTP otp = oTPRepository.findByMaotpAndEmail(lichSuGiaoDichDTO.getOtp(), lichSuGiaoDichDTO.getEmail());
		Date now = new Date();
		if(otp == null || now.getTime() < otp.getThoigianluu().getTime() || now.getTime() > (otp.getThoigianluu().getTime() + 3600000)) {
			return null;
		}
		oTPRepository.delete(otp);
		
		TaiKhoanThanhToan taiKhoanGui = taiKhoanThanhToanRepository.findFirstByMataikhoanthanhtoan(lichSuGiaoDichDTO.getMataikhoannguoigui());
		TaiKhoanThanhToan taiKhoanNhan = taiKhoanThanhToanRepository.findFirstByMataikhoanthanhtoan(lichSuGiaoDichDTO.getMataikhoannguoinhan());
		long soDuNguoiGui = Long.parseLong(taiKhoanGui.getSodu());
		long soDuNguoiNhan = Long.parseLong(taiKhoanNhan.getSodu());
		long soTienGiaoDich = Long.parseLong(lichSuGiaoDichDTO.getSotiengiaodich());
		
		if(lichSuGiaoDichDTO.getNguoitraphi() == 0) {
			soDuNguoiGui = soDuNguoiGui - soTienGiaoDich;
			soDuNguoiNhan = soDuNguoiNhan + soTienGiaoDich - 5000;
			
		} else if(lichSuGiaoDichDTO.getNguoitraphi() == 1) {
			soDuNguoiGui = soDuNguoiGui - soTienGiaoDich - 5000;
			soDuNguoiNhan = soDuNguoiNhan + soTienGiaoDich;
		}
		
		taiKhoanGui.setSodu(soDuNguoiGui+"");
		taiKhoanNhan.setSodu(soDuNguoiNhan+"");
		TaiKhoanThanhToan taiKhoanGuiSauGiaoDich = taiKhoanThanhToanRepository.save(taiKhoanGui);
		TaiKhoanThanhToan taiKhoanNhanSauGiaoDich = taiKhoanThanhToanRepository.save(taiKhoanNhan);
		
		if(taiKhoanGuiSauGiaoDich == null || taiKhoanNhanSauGiaoDich == null) {
			return null;
		}
		
		LichSuGiaoDich lichSuGiaoDich = lichSuGiaoDichRepository.save(new LichSuGiaoDich(lichSuGiaoDichDTO.getMataikhoannguoigui(), 
																						lichSuGiaoDichDTO.getTennguoigui(),
																						lichSuGiaoDichDTO.getMataikhoannguoinhan(),
																						lichSuGiaoDichDTO.getTennguoinhan(),
																						lichSuGiaoDichDTO.getSotiengiaodich(),
																						lichSuGiaoDichDTO.getNoidungchuyenkhoan(),
																						lichSuGiaoDichDTO.getManganhanggui(),
																						lichSuGiaoDichDTO.getManganhangnhan(),
																						new Date(),
																						null,
																						1,
																						1));
		if(lichSuGiaoDich == null) {
			return null;
		}
		
		GoiNho goiNho = goiNhoRepository.findFirstByMataikhoancannhoAndMataikhoangoinho(lichSuGiaoDichDTO.getMataikhoancannho(), lichSuGiaoDichDTO.getMataikhoannguoinhan());
		if(goiNho == null) {
			return new GoiNho(0,lichSuGiaoDichDTO.getMataikhoancannho(), lichSuGiaoDichDTO.getMataikhoannguoinhan(), lichSuGiaoDichDTO.getMataikhoannguoinhan()+"", lichSuGiaoDichDTO.getTennguoinhan(), "", lichSuGiaoDichDTO.getManganhangnhan());
		}
		return new GoiNho(0);
	}

}
