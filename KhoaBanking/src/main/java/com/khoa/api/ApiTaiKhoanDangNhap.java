package com.khoa.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khoa.dto.ChangePassWordDTO;
import com.khoa.dto.ConfirmInfoDTO;
import com.khoa.dto.ResultMessage;
import com.khoa.dto.TaiKhoanDangNhapDTO;
import com.khoa.dto.TaiKhoanThanhToanDTO;
import com.khoa.entity.OTP;
import com.khoa.entity.TaiKhoanDangNhap;
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
		
		return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("0", "Gửi mail thành công"));
	}
	
	@PostMapping("confirmChangePassWord")
	public ResponseEntity sendEmailChangePassWord(@RequestBody ChangePassWordDTO changePassWordDTO) {
		OTP otp = oTPService.findByMaotpAndEmail(changePassWordDTO.getOtp(), changePassWordDTO.getEmail(), changePassWordDTO.getNewpassword());
		if(otp == null) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("1", "Cập nhật mật khẩu thất bại"));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("0", "Cập nhật mật khẩu thành công"));
	}
	
	@PostMapping("sendEmailChuyenTien")
	public ResponseEntity sendEmailChuyenTien(@RequestBody TaiKhoanDangNhapDTO taiKhoanDangNhapDTO) {
		TaiKhoanDangNhapDTO danhSachTaiKhoanThanhToan = taiKhoanDangNhapService.sendEmailChuyenTien(taiKhoanDangNhapDTO.getEmail());
		if(danhSachTaiKhoanThanhToan == null) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("1", "Gửi mail thất bại"));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("0", "Gửi mail thành công"));
	}
	
	@PostMapping("sendEmailConfirmInfo")
	public ResponseEntity sendEmailConfirmInfo(@RequestBody TaiKhoanDangNhapDTO taiKhoanDangNhapDTO) {
		int ketQua = taiKhoanDangNhapService.sendEmailConfirmInfo(taiKhoanDangNhapDTO);
		if(ketQua == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("1", "Email và số điện thoại này đã được sử dụng"));
		} else if(ketQua == 2) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("2", "Gửi mail thất bại"));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("0", "Gửi mail thành công"));
	}
	
	@PostMapping("confirmInfo")
	public ResponseEntity confirmInfo(@RequestBody ConfirmInfoDTO confirmInfoDTO) {
		TaiKhoanDangNhapDTO taiKhoanDanhNhap = taiKhoanDangNhapService.confirmInfo(confirmInfoDTO);
		if(taiKhoanDanhNhap == null) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("1", "Tạo tài khoản thất bại"));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("0", "Tạo tài khoản thành công"));
	}
	
	@PostMapping("createEmployee")
	public ResponseEntity createEmployee(@RequestBody TaiKhoanDangNhap taiKhoanDangNhap) {
		int ketQua = taiKhoanDangNhapService.createEmployee(taiKhoanDangNhap);
		if(ketQua == 2) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("2", "Tài khoản đã tồn tại"));
		} else if(ketQua == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("1", "Tạo tài khoản nhân viên thất bại"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("0", "Tạo tài khoản nhân viên thành công"));
	}
	
	@GetMapping("findAllEmployee")
	public ResponseEntity createEmployee() {
		List<TaiKhoanDangNhap> danhSachNhanVien = taiKhoanDangNhapService.findAllEmployee();
		return ResponseEntity.ok(danhSachNhanVien);
	}
	
	@PostMapping("updateEmployee")
	public ResponseEntity updateEmployee(@RequestBody TaiKhoanDangNhap taiKhoanDangNhap) {
		TaiKhoanDangNhap taiKhoanDanhNhap = taiKhoanDangNhapService.updateEmployee(taiKhoanDangNhap);
		if(taiKhoanDanhNhap == null) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("1", "Cập nhật tài khoản thất bại"));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("0", "Cập nhật tài khoản thành công"));
	}
	
	@PostMapping("deleteEmployee")
	public ResponseEntity deleteEmployee(@RequestBody TaiKhoanDangNhap taiKhoanDangNhap) {
		TaiKhoanDangNhap taiKhoanDanhNhap = taiKhoanDangNhapService.deleteEmployee(taiKhoanDangNhap.getMataikhoan());
		if(taiKhoanDanhNhap == null) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("1", "Xóa tài khoản thất bại"));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("0", "Xóa tài khoản thành công"));
	}
	
}
