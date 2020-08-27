package com.khoa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoa.dto.FindTaiKhoanGuiVaNhanDTO;
import com.khoa.dto.TaiKhoanThanhToanDTO;
import com.khoa.entity.TaiKhoanThanhToan;
import com.khoa.repository.TaiKhoanDangNhapRepository;
import com.khoa.repository.TaiKhoanThanhToanRepository;
import com.khoa.service.TaiKhoanThanhToanService;

@Service
public class TaiKhoanThanhToanServiceImpl implements TaiKhoanThanhToanService {
	
	@Autowired
	private TaiKhoanThanhToanRepository taiKhoanThanhToanRepository;
	
	@Autowired
	private TaiKhoanDangNhapRepository taiKhoanDangNhapRepository;

	@Override
	public List<TaiKhoanThanhToan> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaiKhoanThanhToan findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(TaiKhoanThanhToan dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(TaiKhoanThanhToan dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TaiKhoanThanhToanDTO> findByMataikhoandangnhap(int mataikhoandangnhap) {
		List<TaiKhoanThanhToanDTO> dto = new ArrayList<TaiKhoanThanhToanDTO>();
		
		List<TaiKhoanThanhToan> list = taiKhoanThanhToanRepository.findByMataikhoandangnhap(mataikhoandangnhap);
		for (TaiKhoanThanhToan taiKhoanThanhToan : list) {
			dto.add(new TaiKhoanThanhToanDTO(taiKhoanThanhToan.getMataikhoanthanhtoan(), 
											taiKhoanThanhToan.getMataikhoandangnhap(), 
											taiKhoanThanhToan.getSodu(), 
											taiKhoanThanhToan.getTrangthai(), 
											taiKhoanThanhToan.getTaiKhoanDangNhap().getHoten()));
		}
		
		return dto;
		
	}

	@Override
	public List<TaiKhoanThanhToanDTO> findByMataikhoanthanhtoan(long mataikhoanthanhtoan) {
		List<TaiKhoanThanhToanDTO> dto = new ArrayList<TaiKhoanThanhToanDTO>();
		
		List<TaiKhoanThanhToan> list =  taiKhoanThanhToanRepository.findByMataikhoanthanhtoan(mataikhoanthanhtoan);
		for (TaiKhoanThanhToan taiKhoanThanhToan : list) {
			dto.add(new TaiKhoanThanhToanDTO(taiKhoanThanhToan.getMataikhoanthanhtoan(), 
											taiKhoanThanhToan.getMataikhoandangnhap(), 
											taiKhoanThanhToan.getSodu(), 
											taiKhoanThanhToan.getTrangthai(), 
											taiKhoanThanhToan.getTaiKhoanDangNhap().getHoten()));
		}
		
		return dto;
		
	}

	@Override
	public FindTaiKhoanGuiVaNhanDTO findTaiKhoanGuiVaNhan(TaiKhoanThanhToanDTO taiKhoanThanhToanDTO) {
		TaiKhoanThanhToan taiKhoanNhan = new TaiKhoanThanhToan();
		try {
			taiKhoanNhan = taiKhoanThanhToanRepository.findByMataikhoanthanhtoan(taiKhoanThanhToanDTO.getMataikhoanthanhtoan()).get(0);
		} catch (Exception e) {
			return new FindTaiKhoanGuiVaNhanDTO("1", null);
		}
		
		List<TaiKhoanThanhToanDTO> danhSachGuiDTO = new ArrayList<TaiKhoanThanhToanDTO>();
		List<TaiKhoanThanhToan> danhSachTaiKhoanGui = taiKhoanThanhToanRepository.findByMataikhoandangnhap(taiKhoanThanhToanDTO.getMataikhoandangnhap());
		for (TaiKhoanThanhToan taiKhoanThanhToan : danhSachTaiKhoanGui) {
			if(taiKhoanThanhToan.getMataikhoanthanhtoan() == taiKhoanThanhToanDTO.getMataikhoanthanhtoan()) {
				return new FindTaiKhoanGuiVaNhanDTO("2", null);
			}
			danhSachGuiDTO.add(new TaiKhoanThanhToanDTO(taiKhoanThanhToan.getMataikhoanthanhtoan(),
														taiKhoanThanhToan.getMataikhoandangnhap(),
														taiKhoanThanhToan.getSodu(),
														taiKhoanThanhToan.getTrangthai(),
														taiKhoanThanhToan.getTaiKhoanDangNhap().getHoten()));
		}
		
		FindTaiKhoanGuiVaNhanDTO dto = new FindTaiKhoanGuiVaNhanDTO(
											taiKhoanNhan.getTaiKhoanDangNhap().getHoten(), danhSachGuiDTO);
		
		return dto;
	}

}
