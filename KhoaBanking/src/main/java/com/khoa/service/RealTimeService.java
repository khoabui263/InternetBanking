package com.khoa.service;

import com.khoa.dto.DanhSachNhacNoDTO;
import com.khoa.entity.TaiKhoanDangNhap;

public interface RealTimeService {

	int checkno(int id);

	DanhSachNhacNoDTO getDanhSachNo(int id);

}
