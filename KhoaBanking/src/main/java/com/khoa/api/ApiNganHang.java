package com.khoa.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khoa.dto.NganHangDTO;
import com.khoa.dto.ResultMessage;
import com.khoa.entity.GoiNho;
import com.khoa.service.NganHangService;

@RestController
@RequestMapping("/api/nganhang")
public class ApiNganHang {
	
	@Autowired
	private NganHangService nganHangService;
	
	@Autowired
	private ResultMessage resultMessage;
	
	@GetMapping("")
	public ResponseEntity getDanhSachNganHang() {
		List<NganHangDTO> dto = nganHangService.findAll();
		if(dto == null) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("1", "Lấy thông tin ngân hàng thất bại"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowList("danhSachNganHang", dto));
	}

}
