package com.khoa.api;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khoa.dto.DanhSachNhacNoDTO;
import com.khoa.entity.TaiKhoanDangNhap;
import com.khoa.service.RealTimeService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/realtime")
public class ApiRealTime {
	
	@Autowired
	private RealTimeService realTimeService;
	
	@GetMapping("checkno/{id}")
	public Flux<Integer> checkno(@PathVariable("id") int id){
        return Flux.interval(Duration.ofSeconds(5))
                .map(val -> realTimeService.checkno(id));
    }
	
	@GetMapping("getDanhSachNo/{id}")
	public Flux<DanhSachNhacNoDTO> getDanhSachNo(@PathVariable("id") int id){
        return Flux.interval(Duration.ofSeconds(5))
                .map(val -> realTimeService.getDanhSachNo(id));
    }

}
