package com.khoa.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khoa.dto.LichSuGiaoDichDTO;
import com.khoa.dto.ResultMessage;
import com.khoa.entity.GoiNho;
import com.khoa.service.GoiNhoService;

@RestController
@RequestMapping("/api/goinho")
public class ApiGoiNho {
	
	@Autowired
	private GoiNhoService goiNhoService;
	
	@Autowired
	private ResultMessage resultMessage;

	@PostMapping("saveReminder")
	public ResponseEntity saveReminder(@RequestBody GoiNho GoiNho) {
		GoiNho goiNho = goiNhoService.add(GoiNho);
		if(goiNho == null) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("1", "Lưu thông tin gợi nhớ thất bại"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("0", "Lưu thông tin gợi nhớ thành công"));
	}
}
