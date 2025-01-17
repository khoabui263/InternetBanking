package com.khoa.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khoa.dto.DanhSachLichSuGiaoDichDTO;
import com.khoa.dto.LichSuGiaoDichDetailsDTO;
import com.khoa.dto.ResultMessage;
import com.khoa.entity.GoiNho;
import com.khoa.entity.LichSuGiaoDich;
import com.khoa.service.LichSuGiaoDichService;

@RestController
@RequestMapping("/api/lichsugiaodich")
public class ApiLichSuGiaoDich {
	
	@Autowired
	private ResultMessage resultMessage;
	
	@Autowired
	private LichSuGiaoDichService lichSuGiaoDichService;
	
	@PostMapping("getLichSuGiaoDich")
	public ResponseEntity saveReminder(@RequestBody LichSuGiaoDich lichSuGiaoDich) {
		DanhSachLichSuGiaoDichDTO danhSach = lichSuGiaoDichService.getLichSuGiaoDich(lichSuGiaoDich.getManguoigui());
		return ResponseEntity.ok(danhSach);
	}
	
	@PostMapping("getChuyenTien")
	public ResponseEntity getChuyenTien(@RequestBody DanhSachLichSuGiaoDichDTO danhSachLichSuGiaoDichDTO) {
		DanhSachLichSuGiaoDichDTO danhSach = lichSuGiaoDichService.getChuyenTien(danhSachLichSuGiaoDichDTO);
		return ResponseEntity.ok(danhSach);
	}
	
	@PostMapping("getNhanTien")
	public ResponseEntity getNhanTien(@RequestBody DanhSachLichSuGiaoDichDTO danhSachLichSuGiaoDichDTO) {
		DanhSachLichSuGiaoDichDTO danhSach = lichSuGiaoDichService.getNhanTien(danhSachLichSuGiaoDichDTO);
		return ResponseEntity.ok(danhSach);
	}
	
	@PostMapping("getTraNo")
	public ResponseEntity getTraNo(@RequestBody DanhSachLichSuGiaoDichDTO danhSachLichSuGiaoDichDTO) {
		DanhSachLichSuGiaoDichDTO danhSach = lichSuGiaoDichService.getTraNo(danhSachLichSuGiaoDichDTO);
		return ResponseEntity.ok(danhSach);
	}
	
	@PostMapping("getNguoiKhacTraNo")
	public ResponseEntity getNguoiKhacTraNo(@RequestBody DanhSachLichSuGiaoDichDTO danhSachLichSuGiaoDichDTO) {
		DanhSachLichSuGiaoDichDTO danhSach = lichSuGiaoDichService.getNguoiKhacTraNo(danhSachLichSuGiaoDichDTO);
		return ResponseEntity.ok(danhSach);
	}
	
	@PostMapping("getLichSuGiaoDichByEmployee")
	public ResponseEntity getLichSuGiaoDichByEmployee(@RequestBody DanhSachLichSuGiaoDichDTO danhSachLichSuGiaoDichDTO) {
		List<LichSuGiaoDich> danhSach = lichSuGiaoDichService.getLichSuGiaoDichByEmployee(danhSachLichSuGiaoDichDTO);
		if(danhSach == null) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("1", "Không tìm thấy tài khoản"));
		}
		return ResponseEntity.ok(danhSach);
	}
	
	@PostMapping("getLichSuGiaoDichByEmployeeDetails")
	public ResponseEntity getLichSuGiaoDichByEmployeeDetails(@RequestBody LichSuGiaoDichDetailsDTO lichSuGiaoDichDetailsDTO) {
		LichSuGiaoDichDetailsDTO chiTietLichSuGiaoDich = lichSuGiaoDichService.getLichSuGiaoDichByEmployeeDetails(lichSuGiaoDichDetailsDTO.getId());
		if(chiTietLichSuGiaoDich == null) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("1", "Tìm kiếm thất bại"));
		}
		return ResponseEntity.ok(chiTietLichSuGiaoDich);
	}
	
	@PostMapping("getLichSuGiaoDichByAdmin")
	public ResponseEntity getLichSuGiaoDichByAdmin(@RequestBody DanhSachLichSuGiaoDichDTO danhSachLichSuGiaoDichDTO) {
		List<LichSuGiaoDich> danhSach = lichSuGiaoDichService.getLichSuGiaoDichByAdmin(danhSachLichSuGiaoDichDTO);
		if(danhSach == null) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("0", "Chưa có giao dịch nào"));
		}
		return ResponseEntity.ok(danhSach);
	}
}
