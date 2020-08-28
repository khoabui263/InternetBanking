package com.khoa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoa.dto.GoiNhoDTO;
import com.khoa.entity.GoiNho;
import com.khoa.entity.TaiKhoanDangNhap;
import com.khoa.entity.TaiKhoanThanhToan;
import com.khoa.repository.GoiNhoRepository;
import com.khoa.repository.TaiKhoanDangNhapRepository;
import com.khoa.repository.TaiKhoanThanhToanRepository;
import com.khoa.service.GoiNhoService;

@Service
public class GoiNhoServiceImpl implements GoiNhoService {

	@Autowired
	private GoiNhoRepository goiNhoRepository;
	
	@Autowired
	private TaiKhoanThanhToanRepository taiKhoanThanhToanRepository;
	
	@Autowired
	private TaiKhoanDangNhapRepository taiKhoanDangNhapRepository;

	@Override
	public List<GoiNho> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GoiNho findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GoiNho add(GoiNho dto) {
		return goiNhoRepository.save(dto);

	}

	@Override
	public void update(GoiNho dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<GoiNhoDTO> searchGoiNho(int maTaiKhoanCanNho, String chuoimanguoigoinho, int maNganHang) {
		List<GoiNhoDTO> dto = new ArrayList<GoiNhoDTO>();
		List<GoiNho> goiNho = goiNhoRepository.searchGoiNho(maTaiKhoanCanNho,maNganHang,chuoimanguoigoinho);
		for (GoiNho gn : goiNho) {
			dto.add(new GoiNhoDTO(gn.getMataikhoancannho(), gn.getMataikhoangoinho(), gn.getChuoimanguoigoinho(), gn.getHotennguoigoinho(), gn.getBietdanhgoinho()));
		}
		return dto;
	}

	@Override
	public List<GoiNhoDTO> findByMataikhoancannhoAndTrangthai(int mataikhoancannho, int trangthai) {
		List<GoiNhoDTO> dto = new ArrayList<GoiNhoDTO>();
		List<GoiNho> goiNho = goiNhoRepository.findByMataikhoancannhoAndTrangthai(mataikhoancannho, trangthai);
		for (GoiNho gn : goiNho) {
			dto.add(new GoiNhoDTO(gn.getId(),gn.getMataikhoancannho(), gn.getMataikhoangoinho(), gn.getChuoimanguoigoinho(), gn.getHotennguoigoinho(), gn.getBietdanhgoinho(), gn.getManganhang()));
		}
		return dto;
	}

	@Override
	public String findGoiNhoExisted(int mataikhoancannho, long mataikhoangoinho, int manganhang) {
		TaiKhoanThanhToan taiKhoanThanhToanCuaToi = taiKhoanThanhToanRepository.findFirstByMataikhoanthanhtoanAndMataikhoandangnhap(mataikhoangoinho, mataikhoancannho);
		if(taiKhoanThanhToanCuaToi != null) {
			return "4"; // Tài khoản ko tồn tại
		}
		TaiKhoanThanhToan taiKhoanThanhToan = taiKhoanThanhToanRepository.findFirstByMataikhoanthanhtoanAndTrangthai(mataikhoangoinho,1);
		if(taiKhoanThanhToan == null) {
			return "1"; // Ko có tài khoản trong table goi nho
		} else {
			GoiNho goiNho = goiNhoRepository.findFirstByMataikhoancannhoAndMataikhoangoinhoAndManganhang(mataikhoancannho, mataikhoangoinho, manganhang);
			if(goiNho == null) {
//				TaiKhoanDangNhap taiKhoanDangNhap = taiKhoanDangNhapRepository.findById(mataikhoancannho).get();
				return taiKhoanThanhToan.getTaiKhoanDangNhap().getHoten(); // Chưa từng lưu
			} else {
				if(goiNho.getTrangthai() == 0) {
					return "2"; // Đang ẩn
				} else if(goiNho.getTrangthai() == 1) {
					return "3"; // Đang hoạt động
				}
			}
		}
		
		return "0";
	}

	
	@Override
	public GoiNho activateGoiNho(int mataikhoancannho, long mataikhoangoinho, int manganhang) {
		GoiNho goiNho = goiNhoRepository.findFirstByMataikhoancannhoAndMataikhoangoinhoAndManganhang(mataikhoancannho, mataikhoangoinho, manganhang);
		if(goiNho != null) {
			goiNho.setTrangthai(1);
			GoiNho ketQua = goiNhoRepository.save(goiNho);
			if(ketQua != null) {
				return ketQua;
			}
		}
		return null;
	}

	@Override
	public GoiNho deleteReminder(int id) {
		GoiNho goiNho = goiNhoRepository.findById(id).get();
		if(goiNho != null) {
			goiNho.setTrangthai(0);
			GoiNho ketQua = goiNhoRepository.save(goiNho);
			if(ketQua != null) {
				return ketQua;
			}
		}
		return null;
	}

	

}
