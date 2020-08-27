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

import com.khoa.dto.FindTaiKhoanGuiVaNhanDTO;
import com.khoa.dto.LichSuGiaoDichDTO;
import com.khoa.dto.ResultMessage;
import com.khoa.dto.TaiKhoanDangNhapDTO;
import com.khoa.dto.TaiKhoanThanhToanDTO;
import com.khoa.entity.GoiNho;
import com.khoa.entity.OTP;
import com.khoa.entity.TaiKhoanThanhToan;
import com.khoa.service.OTPService;
import com.khoa.service.TaiKhoanThanhToanService;

@RestController
@RequestMapping("/api/taikhoanthanhtoan")
public class ApiTaiKhoanThanhToan {

	@Autowired
	private TaiKhoanThanhToanService taiKhoanThanhToanService;
	
	@Autowired
	private OTPService otpService;
	
	@Autowired
	private ResultMessage  resultMessage;
	
	@PostMapping("findalltaikhoanthanhtoan")
	public ResponseEntity findalltaikhoanthanhtoan(@RequestBody TaiKhoanDangNhapDTO taiKhoanDangNhapDTO) {
		List<TaiKhoanThanhToanDTO> danhSachTaiKhoanThanhToan = taiKhoanThanhToanService.findByMataikhoandangnhap(taiKhoanDangNhapDTO.getMataikhoan());
		if(danhSachTaiKhoanThanhToan == null) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("1", "Không có tài khoản nào hết"));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowList("DanhSachTaiKhoan", danhSachTaiKhoanThanhToan));
	}
	
	@PostMapping("findTaiKhoanGuiVaNhan")
	public ResponseEntity findTaiKhoanGuiVaNhan(@RequestBody TaiKhoanThanhToanDTO taiKhoanThanhToanDTO) {
		FindTaiKhoanGuiVaNhanDTO danhSachTaiKhoan = taiKhoanThanhToanService.findTaiKhoanGuiVaNhan(taiKhoanThanhToanDTO);
		if(danhSachTaiKhoan.getHotentaikhoannhan().equals("1")) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("1", "Không có tài khoản nào hết"));
			
		} else if(danhSachTaiKhoan.getHotentaikhoannhan().equals("2")) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("2", "Bạn không thể tự chuyển khoản cho các tài khoản của mình"));
			
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(danhSachTaiKhoan);
	}
	
	@PostMapping("confirmTransfer")
	public ResponseEntity confirmTransfer(@RequestBody LichSuGiaoDichDTO lichSuGiaoDichDTO) {
		GoiNho otp = otpService.confirmTransfer(lichSuGiaoDichDTO);
		if(otp == null) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("1", "Chuyển khoản thất bại"));
			
		} else if(otp.getMataikhoancannho() == 0) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("0", "Chuyển khoản thành công"));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(otp);
	}
	
	
}
