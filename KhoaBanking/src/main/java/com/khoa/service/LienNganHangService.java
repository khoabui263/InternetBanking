package com.khoa.service;

import com.khoa.dto.FindTaiKhoanGuiVaNhanDTO;
import com.khoa.dto.LienNganHangDTO;

public interface LienNganHangService {
	FindTaiKhoanGuiVaNhanDTO localFindAccountRSA(LienNganHangDTO lienNganHangDTO);
}
