package com.khoa.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoa.dto.DanhSachNhacNoDTO;
import com.khoa.dto.NhacNoDTO;
import com.khoa.entity.LichSuGiaoDich;
import com.khoa.entity.NhacNo;
import com.khoa.entity.TaiKhoanDangNhap;
import com.khoa.entity.TaiKhoanThanhToan;
import com.khoa.repository.LichSuGiaoDichRepository;
import com.khoa.repository.NhacNoRepository;
import com.khoa.repository.TaiKhoanDangNhapRepository;
import com.khoa.repository.TaiKhoanThanhToanRepository;
import com.khoa.service.NhacNoService;

@Service
public class NhacNoServiceImpl implements NhacNoService {

	@Autowired
	private NhacNoRepository nhacNoRepository;

	@Autowired
	private TaiKhoanDangNhapRepository taiKhoanDangNhapRepository;

	@Autowired
	private TaiKhoanThanhToanRepository taiKhoanThanhToanRepository;
	
	@Autowired
	private LichSuGiaoDichRepository lichSuGiaoDichRepository;

	@Override
	public List<NhacNo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NhacNo findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(NhacNoDTO dto) {
		TaiKhoanDangNhap taiKhoanDangNhap = taiKhoanDangNhapRepository
				.findByEmailOrSodienthoai(dto.getEmailsodienthoai(), dto.getEmailsodienthoai());
		if (taiKhoanDangNhap == null) {
			return 2;
		}
		long taiKhoanDuocNhacNo = taiKhoanDangNhap.getTaiKhoanThanhToans().get(0).getMataikhoanthanhtoan();
		NhacNo nhacNo = new NhacNo();
		nhacNo.setManguoinhacno(dto.getManguoinhacno());
		nhacNo.setMataikhoannhacno(dto.getMataikhoannhacno());
		nhacNo.setManguoibino(taiKhoanDangNhap.getMataikhoan());
		nhacNo.setMataikhoanduocnhacno(taiKhoanDangNhap.getTaiKhoanThanhToans().get(0).getMataikhoanthanhtoan());
		nhacNo.setSotienno(dto.getSotienno() + "");
		nhacNo.setNoidungnhacno(dto.getNoidungnhacno());
		nhacNo.setMaloainhacno(1);

		taiKhoanDangNhap.setTinhtrangno(1);
		TaiKhoanDangNhap ketQuaDangNhap = taiKhoanDangNhapRepository.save(taiKhoanDangNhap);
		NhacNo ketQuaNhacNo = nhacNoRepository.save(nhacNo);
		if (ketQuaDangNhap == null || ketQuaNhacNo == null) {
			return 1;
		}
		return 0;
	}

	@Override
	public void update(NhacNo dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public DanhSachNhacNoDTO getDanhSachNo(NhacNoDTO nhacNoDTO) {
		DanhSachNhacNoDTO dto = new DanhSachNhacNoDTO();

		List<NhacNoDTO> dtoTaoNo = new ArrayList<NhacNoDTO>();
		List<NhacNoDTO> dtoChuaThanhToan = new ArrayList<NhacNoDTO>();
		List<NhacNoDTO> dtoTuChoiThanhToan = new ArrayList<NhacNoDTO>();

		List<NhacNo> listTaoNo = nhacNoRepository.findByManguoinhacnoAndMaloainhacno(nhacNoDTO.getManguoinhacno(), 1);
		for (NhacNo taoNo : listTaoNo) {
			dtoTaoNo.add(new NhacNoDTO(taoNo.getId(), taoNo.getMataikhoannhacno(), taoNo.getManguoinhacno(),
					taoNo.getThongtinnguoinhacno().getHoten(), "", taoNo.getMataikhoanduocnhacno(),
					taoNo.getManguoibino(), taoNo.getThongtinnguoibino().getHoten(), taoNo.getSotienno(),
					taoNo.getNoidungnhacno(), taoNo.getMaloainhacno()));
		}

		List<NhacNo> listChuaThanhToan = nhacNoRepository.findByManguoibinoAndMaloainhacno(nhacNoDTO.getManguoinhacno(),
				1);
		for (NhacNo chuaThanhToan : listChuaThanhToan) {
			dtoChuaThanhToan.add(new NhacNoDTO(chuaThanhToan.getId(), chuaThanhToan.getMataikhoannhacno(),
					chuaThanhToan.getManguoinhacno(), chuaThanhToan.getThongtinnguoinhacno().getHoten(), "",
					chuaThanhToan.getMataikhoanduocnhacno(), chuaThanhToan.getManguoibino(),
					chuaThanhToan.getThongtinnguoibino().getHoten(), chuaThanhToan.getSotienno(),
					chuaThanhToan.getNoidungnhacno(), chuaThanhToan.getMaloainhacno()));
		}

		List<NhacNo> listTuChoiThanhToan = nhacNoRepository
				.findByManguoinhacnoAndMaloainhacno(nhacNoDTO.getManguoinhacno(), 3);
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

	@Override
	public DanhSachNhacNoDTO updateLoaiNhacNo(NhacNoDTO nhacNoDTO) {
		TaiKhoanDangNhap taiKhoanDangNhap = taiKhoanDangNhapRepository.findById(nhacNoDTO.getManguoinhacno()).get();
		TaiKhoanDangNhap taiKhoanBiNo = taiKhoanDangNhapRepository.findById(nhacNoDTO.getManguoibino()).get();
		NhacNo nhacNo = nhacNoRepository.findById(nhacNoDTO.getId()).get();
		if (nhacNo == null || taiKhoanDangNhap == null) {
			return null;
		}
		
		if(taiKhoanDangNhap.getTinhtrangno() == 0 && nhacNoDTO.getMaloainhacno() == 1) {
			taiKhoanDangNhap.setTinhtrangno(4);
			taiKhoanBiNo.setTinhtrangno(1);
			taiKhoanDangNhapRepository.save(taiKhoanBiNo);
		} else {
			taiKhoanDangNhap.setTinhtrangno(nhacNoDTO.getMaloainhacno());
		}
		
		nhacNo.setMaloainhacno(nhacNoDTO.getMaloainhacno());
		TaiKhoanDangNhap updateDangNhap = taiKhoanDangNhapRepository.save(taiKhoanDangNhap);
		NhacNo updateNhacNo = nhacNoRepository.save(nhacNo);
		if (updateNhacNo == null || updateDangNhap == null) {
			return null;
		}
		
		if(nhacNoDTO.getMaloainhacno() == 3) {
			nhacNoDTO.setManguoinhacno(taiKhoanBiNo.getMataikhoan());
		}

		return getDanhSachNo(nhacNoDTO);
	}

	@Override
	public DanhSachNhacNoDTO payDebt(NhacNoDTO nhacNoDTO) {
		TaiKhoanThanhToan taiKhoanNhacNo = taiKhoanThanhToanRepository
				.findFirstByMataikhoanthanhtoanAndTrangthai(nhacNoDTO.getMataikhoannhacno(), 1);
		TaiKhoanThanhToan taiKhoanTraNo = taiKhoanThanhToanRepository
				.findFirstByMataikhoanthanhtoanAndTrangthai(nhacNoDTO.getMataikhoanduocnhacno(), 1);

		if (taiKhoanNhacNo == null || taiKhoanTraNo == null) {
			return null;
		}

		long soDuNguoinhacNo = Long.parseLong(taiKhoanNhacNo.getSodu());
		long soDuNguoiTraNo = Long.parseLong(taiKhoanTraNo.getSodu());
		long soTienNo = Long.parseLong(nhacNoDTO.getSotienno());

		soDuNguoinhacNo = soDuNguoinhacNo + soTienNo;
		soDuNguoiTraNo = soDuNguoiTraNo - soTienNo;
		taiKhoanNhacNo.setSodu(soDuNguoinhacNo + "");
		taiKhoanTraNo.setSodu(soDuNguoiTraNo + "");
		TaiKhoanThanhToan taiKhoanNhacNoSauGiaoDich = taiKhoanThanhToanRepository.save(taiKhoanNhacNo);
		TaiKhoanThanhToan taiKhoanTraNoSauGiaoDich = taiKhoanThanhToanRepository.save(taiKhoanTraNo);

		if (taiKhoanNhacNoSauGiaoDich == null || taiKhoanTraNoSauGiaoDich == null) {
			return null;
		}

		LichSuGiaoDich lichSuGiaoDich = new LichSuGiaoDich(nhacNoDTO.getMataikhoanduocnhacno(),
															nhacNoDTO.getHotennguoibino(),
															nhacNoDTO.getMataikhoannhacno(),
															nhacNoDTO.getHotennguoinhacno(),
															nhacNoDTO.getSotienno(),
															nhacNoDTO.getNoidungnhacno(),
															1,
															1,
															new Date(),
															null,
															3,
															1);
		
		LichSuGiaoDich lichSuThanhToanNo = lichSuGiaoDichRepository.save(lichSuGiaoDich);
		if(lichSuThanhToanNo == null) {
			return null;
		}

		TaiKhoanDangNhap taiKhoanNguoiNhacNo = taiKhoanDangNhapRepository.findById(nhacNoDTO.getManguoinhacno()).get();
		TaiKhoanDangNhap taiKhoanNguoiTraNo = taiKhoanDangNhapRepository.findById(nhacNoDTO.getManguoibino()).get();
		NhacNo nhacNo = nhacNoRepository.findById(nhacNoDTO.getId()).get();
		if (nhacNo == null || taiKhoanNguoiNhacNo == null || taiKhoanNguoiTraNo == null) {
			return null;
		}

		taiKhoanNguoiNhacNo.setTinhtrangno(2);
		taiKhoanNguoiTraNo.setTinhtrangno(2);
		nhacNo.setMataikhoanduocnhacno(nhacNoDTO.getMataikhoanduocnhacno());
		nhacNo.setMaloainhacno(2);
		TaiKhoanDangNhap updateNguoiNhacNo = taiKhoanDangNhapRepository.save(taiKhoanNguoiNhacNo);
		TaiKhoanDangNhap updateNguoiTraNo = taiKhoanDangNhapRepository.save(taiKhoanNguoiTraNo);
		NhacNo updateNhacNo = nhacNoRepository.save(nhacNo);
		if (updateNhacNo == null || updateNguoiNhacNo == null || updateNguoiTraNo == null) {
			return null;
		}
		nhacNoDTO.setManguoinhacno(updateNguoiTraNo.getMataikhoan());
		return getDanhSachNo(nhacNoDTO);
	}

}
