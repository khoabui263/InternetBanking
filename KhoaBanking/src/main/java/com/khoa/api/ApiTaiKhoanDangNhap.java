package com.khoa.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khoa.dto.ChangePassWordDTO;
import com.khoa.dto.ResultMessage;
import com.khoa.dto.TaiKhoanDangNhapDTO;
import com.khoa.dto.TaiKhoanThanhToanDTO;
import com.khoa.entity.OTP;
import com.khoa.service.OTPService;
import com.khoa.service.TaiKhoanDangNhapService;

@RestController
@RequestMapping("/api/taikhoandangnhap")
public class ApiTaiKhoanDangNhap {
	
	@Autowired
	private TaiKhoanDangNhapService taiKhoanDangNhapService;
	
	@Autowired
	private OTPService oTPService;
	
	@Autowired
	private ResultMessage resultMessage;
	
	@PostMapping("sendEmailChangePassWord")
	public ResponseEntity sendEmailChangePassWord(@RequestBody TaiKhoanDangNhapDTO taiKhoanDangNhapDTO) {
		TaiKhoanDangNhapDTO danhSachTaiKhoanThanhToan = taiKhoanDangNhapService.sendEmailChangePassWord(taiKhoanDangNhapDTO.getEmail(), taiKhoanDangNhapDTO.getMatkhau());
		if(danhSachTaiKhoanThanhToan == null) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("1", "Không tìm thấy tài khoản"));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("0", "Tìm thấy tài khoản"));
	}
	
	@PostMapping("confirmChangePassWord")
	public ResponseEntity sendEmailChangePassWord(@RequestBody ChangePassWordDTO changePassWordDTO) {
		OTP otp = oTPService.findByMaotpAndEmail(changePassWordDTO.getOtp(), changePassWordDTO.getEmail(), changePassWordDTO.getNewpassword());
		if(otp == null) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("1", "Cập nhật mật khẩu thất bại"));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("0", "Cập nhật mật khẩu thành công"));
	}
	
	

}
