package com.khoa.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khoa.dto.ResultMessage;
import com.khoa.dto.TaiKhoanDangNhapDTO;
import com.khoa.dto.TaiKhoanThanhToanDTO;
import com.khoa.entity.TaiKhoanThanhToan;
import com.khoa.service.TaiKhoanThanhToanService;

@RestController
@RequestMapping("/api/search")
public class ApiSearch {
	
	@Autowired
	private TaiKhoanThanhToanService taiKhoanThanhToanService;
	
	@Autowired
	private ResultMessage  resultMessage;
	
	@PostMapping("searchTaiKhoanThanhToan")
	public ResponseEntity searchTaiKhoanThanhToan(@RequestBody TaiKhoanThanhToanDTO taiKhoanThanhToanDTO) {
		List<TaiKhoanThanhToanDTO> search = taiKhoanThanhToanService.findByMataikhoanthanhtoan(taiKhoanThanhToanDTO.getMataikhoanthanhtoan());
		if(search == null) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("1", "Không có tài khoản nào hết"));
		}
		return ResponseEntity.ok(search);
	}

}
