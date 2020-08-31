package com.khoa.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoa.dto.DanhSachNhacNoDTO;
import com.khoa.dto.NhacNoDTO;
import com.khoa.entity.NhacNo;
import com.khoa.entity.TaiKhoanDangNhap;
import com.khoa.repository.NhacNoRepository;
import com.khoa.repository.TaiKhoanDangNhapRepository;
import com.khoa.service.NhacNoService;
import com.khoa.service.RealTimeService;

@Service
@Transactional(rollbackOn = Exception.class)
public class RealTimeServiceImpl implements RealTimeService {

	@Autowired
	private TaiKhoanDangNhapRepository taiKhoanDangNhapRepository;

	@Autowired
	private NhacNoRepository nhacNoRepository;

	@Override
	public int checkno(int id) {
		TaiKhoanDangNhap checkNoTaiKhoan = taiKhoanDangNhapRepository.findByMataikhoanAndTrangthai(id, 1);
		int tinhTrangNo = checkNoTaiKhoan.getTinhtrangno();
		if (tinhTrangNo == 1 || tinhTrangNo == 3) {
			checkNoTaiKhoan.setTinhtrangno(0);
			taiKhoanDangNhapRepository.save(checkNoTaiKhoan);
		}
		return tinhTrangNo;
	}

	@Override
	public DanhSachNhacNoDTO getDanhSachNo(int id) {
		DanhSachNhacNoDTO dto = new DanhSachNhacNoDTO();

		List<NhacNoDTO> dtoTaoNo = new ArrayList<NhacNoDTO>();
		List<NhacNoDTO> dtoChuaThanhToan = new ArrayList<NhacNoDTO>();
		List<NhacNoDTO> dtoTuChoiThanhToan = new ArrayList<NhacNoDTO>();

		List<NhacNo> listTaoNo = nhacNoRepository.findByManguoinhacnoAndMaloainhacno(id, 1);
		for (NhacNo taoNo : listTaoNo) {
			dtoTaoNo.add(new NhacNoDTO(taoNo.getId(), taoNo.getMataikhoannhacno(), taoNo.getManguoinhacno(),
					taoNo.getThongtinnguoinhacno().getHoten(), "", taoNo.getMataikhoanduocnhacno(),
					taoNo.getManguoibino(), taoNo.getThongtinnguoibino().getHoten(), taoNo.getSotienno(),
					taoNo.getNoidungnhacno(), taoNo.getMaloainhacno()));
		}

		List<NhacNo> listChuaThanhToan = nhacNoRepository.findByManguoibinoAndMaloainhacno(id, 1);
		for (NhacNo chuaThanhToan : listChuaThanhToan) {
			dtoChuaThanhToan.add(new NhacNoDTO(chuaThanhToan.getId(), chuaThanhToan.getMataikhoannhacno(),
					chuaThanhToan.getManguoinhacno(), chuaThanhToan.getThongtinnguoinhacno().getHoten(), "",
					chuaThanhToan.getMataikhoanduocnhacno(), chuaThanhToan.getManguoibino(),
					chuaThanhToan.getThongtinnguoibino().getHoten(), chuaThanhToan.getSotienno(),
					chuaThanhToan.getNoidungnhacno(), chuaThanhToan.getMaloainhacno()));
		}

		List<NhacNo> listTuChoiThanhToan = nhacNoRepository
				.findByManguoinhacnoAndMaloainhacno(id, 3);
		for (NhacNo tuChoi : listTuChoiThanhToan) {
			dtoTuChoiThanhToan.add(new NhacNoDTO(tuChoi.getId(), tuChoi.getMataikhoannhacno(),
					tuChoi.getManguoinhacno(), tuChoi.getThongtinnguoinhacno().getHoten(), "",
					tuChoi.getMataikhoanduocnhacno(), tuChoi.getManguoibino(), tuChoi.getThongtinnguoibino().getHoten(),
					tuChoi.getSotienno(), tuChoi.getNoidungnhacno(), tuChoi.getMaloainhacno()));
		}

		dto.setDanhSachTaoNo(dtoTaoNo);
		dto.setDanhSachChuaThanhToanNo(dtoChuaThanhToan);
		dto.setDanhSachTuChoiThanhToan(dtoTuChoiThanhToan);
		return dto;
	}

}
