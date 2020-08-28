package com.khoa.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khoa.dto.GoiNhoDTO;
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
	
	@PostMapping("findByMataikhoancannhoAndTrangthai")
	public ResponseEntity findByMataikhoancannhoAndTrangthai(@RequestBody GoiNho GoiNho) {
		List<GoiNhoDTO> goiNho = goiNhoService.findByMataikhoancannhoAndTrangthai(GoiNho.getMataikhoancannho(), 1);
		if(goiNho == null) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("1", "Lưu thông tin gợi nhớ thất bại"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowList("danhSachGoiNho", goiNho));
	}
	
	@PostMapping("findGoiNhoExisted")
	public ResponseEntity findGoiNhoExisted(@RequestBody GoiNho goiNho) {
		String ketQua = goiNhoService.findGoiNhoExisted(goiNho.getMataikhoancannho(), goiNho.getMataikhoangoinho(), goiNho.getManganhang());
		if(ketQua.equals("1")) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("1", "Tài khoản không tồn tại"));
		} else if(ketQua.equals("2")) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("2", "Tài khoản đang ẩn"));
		} else if(ketQua.equals("3")) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("3", "Tài khoản đã được lưu"));
		} else if(ketQua.equals("4")) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("4", "Không tự lưu chính tài khoản của mình"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("0", ketQua));
	}
	

	@PutMapping("activateGoiNho")
	public ResponseEntity activateGoiNho(@RequestBody GoiNho goiNho) {
		GoiNho gn = goiNhoService.activateGoiNho(goiNho.getMataikhoancannho(), goiNho.getMataikhoangoinho(), goiNho.getManganhang());
		if(gn == null) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("1", "Cập nhật trang thái thất bại"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("0", "Cập nhật trang thái thành công"));
	}
	
	@PutMapping("deleteReminder")
	public ResponseEntity deleteReminder(@RequestBody GoiNho goiNho) {
		GoiNho gn = goiNhoService.deleteReminder(goiNho.getId());
		if(gn == null) {
			return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("1", "Xóa gợi nhớ thất bại"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("0", "Xóa gợi nhớ thành công"));
	}
}
