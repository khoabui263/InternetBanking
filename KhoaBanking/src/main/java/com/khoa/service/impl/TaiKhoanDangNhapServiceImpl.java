package com.khoa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoa.dto.TaiKhoanDangNhapDTO;
import com.khoa.entity.TaiKhoanDangNhap;
import com.khoa.repository.TaiKhoanDangNhapRepository;
import com.khoa.service.TaiKhoanDangNhapService;

@Service
public class TaiKhoanDangNhapServiceImpl implements TaiKhoanDangNhapService {

	@Autowired
	private TaiKhoanDangNhapRepository taiKhoanDangNhapRepository;

	@Override
	public List<TaiKhoanDangNhapDTO> findAll() {
		List<TaiKhoanDangNhapDTO> dto = new ArrayList<TaiKhoanDangNhapDTO>();

		List<TaiKhoanDangNhap> list = taiKhoanDangNhapRepository.findAll();
		for (TaiKhoanDangNhap item : list) {
			dto.add(new TaiKhoanDangNhapDTO(item.getMataikhoan(), item.getHoten(), item.getEmail(),
					item.getSodienthoai(), item.getMatkhau(), item.getRefeshtoken(), item.getMaloainguoidung()));
		}
		return dto;
	}

	@Override
	public TaiKhoanDangNhapDTO findById(int id) {
		TaiKhoanDangNhap taiKhoanDangNhap = taiKhoanDangNhapRepository.findById(id).get();

		return new TaiKhoanDangNhapDTO(taiKhoanDangNhap.getMataikhoan(), taiKhoanDangNhap.getHoten(),
				taiKhoanDangNhap.getEmail(), taiKhoanDangNhap.getSodienthoai(), taiKhoanDangNhap.getMatkhau(),
				taiKhoanDangNhap.getRefeshtoken(), taiKhoanDangNhap.getMaloainguoidung());
	}

	@Override
	public void add(TaiKhoanDangNhapDTO dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(TaiKhoanDangNhapDTO dto) {
		TaiKhoanDangNhap taiKhoanDangNhap = taiKhoanDangNhapRepository.findById(dto.getMataikhoan()).get();
		taiKhoanDangNhap.setRefeshtoken(dto.getRefeshtoken());
		taiKhoanDangNhapRepository.save(taiKhoanDangNhap);

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public TaiKhoanDangNhapDTO findByEmailOrSodienthoai(String email, String soDienThoai) {
		TaiKhoanDangNhap taiKhoanDangNhap = taiKhoanDangNhapRepository.findByEmailOrSodienthoai(email, soDienThoai);
		return new TaiKhoanDangNhapDTO(taiKhoanDangNhap.getMataikhoan(), taiKhoanDangNhap.getHoten(),
				taiKhoanDangNhap.getEmail(), taiKhoanDangNhap.getSodienthoai(), taiKhoanDangNhap.getMatkhau(),
				taiKhoanDangNhap.getRefeshtoken(), taiKhoanDangNhap.getMaloainguoidung());
	}

	@Override
	public List<TaiKhoanDangNhapDTO> findAllDetailsTaiKhoanDangNhap() {
		return taiKhoanDangNhapRepository.findAllDetailsTaiKhoanDangNhap();
	}

}
