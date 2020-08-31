package com.khoa.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khoa.dto.DanhSachNhacNoDTO;
import com.khoa.dto.NhacNoDTO;
import com.khoa.dto.ResultMessage;
import com.khoa.entity.GoiNho;
import com.khoa.service.NhacNoService;

@RestController
@RequestMapping("/api/nhacno")
public class ApiNhacNo {
	
	@Autowired
	private NhacNoService nhacNoService;
	
	@Autowired
	private ResultMessage resultMessage;

	@PostMapping("saveNhacNo")
	public ResponseEntity saveNhacNo(@RequestBody NhacNoDTO nhacNoDTO) {
		int ketQua = nhacNoService.add(nhacNoDTO);
		if(ketQua == 2) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("2", "Không tìm thấy tài khoản này"));
		} else if(ketQua == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("1", "Lưu thông tin nhắc nợ thất bại"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("0", "Lưu thông tin nhắc nợ thành công"));
	}
	
	@PostMapping("getDanhSachNo")
	public ResponseEntity getDanhSachNo(@RequestBody NhacNoDTO nhacNoDTO) {
		DanhSachNhacNoDTO danhSachNhacNoDTO = nhacNoService.getDanhSachNo(nhacNoDTO);
		return ResponseEntity.ok(danhSachNhacNoDTO);
	}
	
	@PutMapping("updateLoaiNhacNo")
	public ResponseEntity updateLoaiNhacNo(@RequestBody NhacNoDTO nhacNoDTO) {
		DanhSachNhacNoDTO danhSachNhacNoDTO = nhacNoService.updateLoaiNhacNo(nhacNoDTO);
		return ResponseEntity.ok(danhSachNhacNoDTO);
	}
	
	@PostMapping("payDebt")
	public ResponseEntity payDebt(@RequestBody NhacNoDTO nhacNoDTO) {
		DanhSachNhacNoDTO danhSachNhacNoDTO = nhacNoService.payDebt(nhacNoDTO);
		if(danhSachNhacNoDTO == null) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("1", "Thanh toán nợ thất bại"));
		}
		return ResponseEntity.ok(danhSachNhacNoDTO);
	}
}
