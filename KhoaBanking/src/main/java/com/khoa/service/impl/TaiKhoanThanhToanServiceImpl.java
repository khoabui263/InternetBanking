package com.khoa.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoa.dto.FindTaiKhoanGuiVaNhanDTO;
import com.khoa.dto.NapTienDTO;
import com.khoa.dto.TaiKhoanThanhToanDTO;
import com.khoa.entity.LichSuGiaoDich;
import com.khoa.entity.TaiKhoanThanhToan;
import com.khoa.repository.LichSuGiaoDichRepository;
import com.khoa.repository.TaiKhoanDangNhapRepository;
import com.khoa.repository.TaiKhoanThanhToanRepository;
import com.khoa.service.TaiKhoanThanhToanService;

@Service
public class TaiKhoanThanhToanServiceImpl implements TaiKhoanThanhToanService {
	
	@Autowired
	private TaiKhoanThanhToanRepository taiKhoanThanhToanRepository;
	
	@Autowired
	private TaiKhoanDangNhapRepository taiKhoanDangNhapRepository;
	
	@Autowired
	private LichSuGiaoDichRepository lichSuGiaoDichRepository;

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
		
		List<TaiKhoanThanhToan> list = taiKhoanThanhToanRepository.findByMataikhoandangnhapAndTrangthai(mataikhoandangnhap, 1);
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
		
		List<TaiKhoanThanhToan> list =  taiKhoanThanhToanRepository.findByMataikhoanthanhtoanAndTrangthai(mataikhoanthanhtoan, 1);
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
			taiKhoanNhan = taiKhoanThanhToanRepository.findByMataikhoanthanhtoanAndTrangthai(taiKhoanThanhToanDTO.getMataikhoanthanhtoan(), 1).get(0);
		} catch (Exception e) {
			return new FindTaiKhoanGuiVaNhanDTO("1", null);
		}
		
		List<TaiKhoanThanhToanDTO> danhSachGuiDTO = new ArrayList<TaiKhoanThanhToanDTO>();
		List<TaiKhoanThanhToan> danhSachTaiKhoanGui = taiKhoanThanhToanRepository.findByMataikhoandangnhapAndTrangthai(taiKhoanThanhToanDTO.getMataikhoandangnhap(), 1);
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

	@Override
	public int chargeMoney(NapTienDTO napTienDTO) {
		TaiKhoanThanhToan taiKhoanNguoiNhan = taiKhoanThanhToanRepository.findFirstByMataikhoanthanhtoanAndTrangthai(napTienDTO.getMataikhoannhan(), 1);
		if(taiKhoanNguoiNhan == null) {
			return 1;
		}
		
		long soDuNguoiNhan = Long.parseLong(taiKhoanNguoiNhan.getSodu());
		long soTienGiaoDich = Long.parseLong(napTienDTO.getMoney());
		
		if(napTienDTO.getType() == 0) {
			TaiKhoanThanhToan taiKhoanNguoiGui = taiKhoanThanhToanRepository.findFirstByMataikhoanthanhtoanAndTrangthai(napTienDTO.getMataikhoanchuyen(), 1);
			if(taiKhoanNguoiGui == null) {
				return 1;
			}
			long soDuNguoiGui = Long.parseLong(taiKhoanNguoiGui.getSodu());
			
			if(soDuNguoiGui + 5000 < soTienGiaoDich) {
				return 2;
			}			
			// Người gửi trả phí
			soDuNguoiGui = soDuNguoiGui - soTienGiaoDich - 5000;
			soDuNguoiNhan = soDuNguoiNhan + soTienGiaoDich;
			
			taiKhoanNguoiGui.setSodu(soDuNguoiGui+"");
			taiKhoanNguoiNhan.setSodu(soDuNguoiNhan+"");
			TaiKhoanThanhToan taiKhoanGuiSauGiaoDich = taiKhoanThanhToanRepository.save(taiKhoanNguoiGui);
			TaiKhoanThanhToan taiKhoanNhanSauGiaoDich = taiKhoanThanhToanRepository.save(taiKhoanNguoiNhan);
			
			if(taiKhoanGuiSauGiaoDich == null || taiKhoanNhanSauGiaoDich == null) {
				return 3;
			}
			
			LichSuGiaoDich lichSuGiaoDich = lichSuGiaoDichRepository.save(new LichSuGiaoDich(taiKhoanGuiSauGiaoDich.getMataikhoandangnhap(),
																							taiKhoanGuiSauGiaoDich.getMataikhoanthanhtoan(),
																							taiKhoanGuiSauGiaoDich.getTaiKhoanDangNhap().getHoten(),
																							taiKhoanNhanSauGiaoDich.getMataikhoandangnhap(),
																							taiKhoanNhanSauGiaoDich.getMataikhoanthanhtoan(),
																							taiKhoanNhanSauGiaoDich.getTaiKhoanDangNhap().getHoten(),
																							napTienDTO.getMoney(),
																							"",
																							1,
																							1,
																							new Date(),
																							null,
																							1,
																							1));
			if(lichSuGiaoDich == null) {
				return 3;
			}
			
		} else if(napTienDTO.getType() == 1) {
			soDuNguoiNhan = soDuNguoiNhan + soTienGiaoDich;
			taiKhoanNguoiNhan.setSodu(soDuNguoiNhan+"");
			TaiKhoanThanhToan taiKhoanNhanSauGiaoDich = taiKhoanThanhToanRepository.save(taiKhoanNguoiNhan);
			
			if(taiKhoanNhanSauGiaoDich == null) {
				return 3;
			}
		}
		return 0;
	}

}
