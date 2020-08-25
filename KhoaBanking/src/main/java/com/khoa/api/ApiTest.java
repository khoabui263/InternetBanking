package com.khoa.api;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khoa.dto.ResultMessage;
import com.khoa.dto.TaiKhoanDangNhapDTO;
import com.khoa.entity.OTP;
import com.khoa.service.OTPService;
import com.khoa.service.TaiKhoanDangNhapService;

@RestController
//@CrossOrigin
@RequestMapping("/api/test")
public class ApiTest {
	
	@Autowired
	private ResultMessage resultMessage;
	
	@Autowired
	private TaiKhoanDangNhapService taiKhoanDangNhapService;
	
	@Autowired
	private OTPService oTPService;
	
	@GetMapping
	public ResponseEntity index() {
		List<TaiKhoanDangNhapDTO> dto = taiKhoanDangNhapService.findAllDetailsTaiKhoanDangNhap();
		return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowList("DanhSachTaiKhoan", dto));
	}
	
	@GetMapping("testDate")
	public ResponseEntity testDate() {
		Date now = new Date();
//		oTPService.add(new OTP(123456, now));	
		
		return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowList("DanhSachTaiKhoan", oTPService.findAllByOrderByThoiGianLuuAsc()));
	}

}
