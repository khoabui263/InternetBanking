package com.khoa.api;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.didisoft.pgp.PGPException;
import com.didisoft.pgp.exceptions.WrongPasswordException;
import com.khoa.dto.FindTaiKhoanGuiVaNhanDTO;
import com.khoa.dto.LichSuGiaoDichDTO;
import com.khoa.dto.LienNganHangDTO;
import com.khoa.dto.NhacNoDTO;
import com.khoa.dto.ResultMessage;
import com.khoa.entity.GoiNho;
import com.khoa.service.LienNganHangService;
import com.khoa.service.OTPService;

@RestController
@RequestMapping("/api/liennganhang")
public class ApiLienNganHang {

	@Autowired
	private ResultMessage resultMessage;
	
	@Autowired
	private LienNganHangService lienNganHangService;
	
	@Autowired
	private OTPService otpService;
	
	@PostMapping("localFindAccountRSA")
	public ResponseEntity localFindAccountRSA(@RequestBody LienNganHangDTO lienNganHangDTO) {
		FindTaiKhoanGuiVaNhanDTO danhSachTaiKhoan = lienNganHangService.localFindAccountRSA(lienNganHangDTO);
		if(danhSachTaiKhoan.getHotentaikhoannhan().equals("1")) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("1", "Không có tài khoản nào hết"));
			
		} else if(danhSachTaiKhoan.getHotentaikhoannhan().equals("2")) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("2", "Bạn không thể tự chuyển khoản cho các tài khoản của mình"));
			
		}
		return ResponseEntity.status(HttpStatus.OK).body(danhSachTaiKhoan);
	}
	
	@PostMapping("confirmTransferLocalToRSA")
	public ResponseEntity confirmTransferLocalToRSA(@RequestBody LichSuGiaoDichDTO lichSuGiaoDichDTO)
			throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, InvalidKeyException,
			SignatureException {
		GoiNho otp = otpService.confirmTransferLocalToRSA(lichSuGiaoDichDTO);
		if(otp == null) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("1", "Chuyển khoản thất bại"));
			
		} else if(otp.getMataikhoancannho() == 0) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("0", "Chuyển khoản thành công"));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(otp);
	}
	
	@PostMapping("localFindAccountPGP")
	public ResponseEntity localFindAccountPGP(@RequestBody LienNganHangDTO lienNganHangDTO) {
		FindTaiKhoanGuiVaNhanDTO danhSachTaiKhoan = lienNganHangService.localFindAccountPGP(lienNganHangDTO);
		if(danhSachTaiKhoan.getHotentaikhoannhan().equals("1")) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("1", "Không có tài khoản nào hết"));
			
		} else if(danhSachTaiKhoan.getHotentaikhoannhan().equals("2")) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("2", "Bạn không thể tự chuyển khoản cho các tài khoản của mình"));
			
		}
		return ResponseEntity.status(HttpStatus.OK).body(danhSachTaiKhoan);
	}
	
	@PostMapping("confirmTransferLocalToPGP")
	public ResponseEntity confirmTransferLocalToPGP(@RequestBody LichSuGiaoDichDTO lichSuGiaoDichDTO)
			throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, InvalidKeyException,
			SignatureException, WrongPasswordException, PGPException {
		GoiNho otp = otpService.confirmTransferLocalToPGP(lichSuGiaoDichDTO);
		if(otp == null) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("1", "Chuyển khoản thất bại"));
			
		} else if(otp.getMataikhoancannho() == 0) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("0", "Chuyển khoản thành công"));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(otp);
	}
}
