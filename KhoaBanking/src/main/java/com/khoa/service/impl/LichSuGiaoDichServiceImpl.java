package com.khoa.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoa.dto.DanhSachLichSuGiaoDichDTO;
import com.khoa.dto.LichSuGiaoDichDetailsDTO;
import com.khoa.entity.LichSuGiaoDich;
import com.khoa.entity.TaiKhoanDangNhap;
import com.khoa.repository.LichSuGiaoDichRepository;
import com.khoa.repository.TaiKhoanDangNhapRepository;
import com.khoa.service.LichSuGiaoDichService;

@Service
public class LichSuGiaoDichServiceImpl implements LichSuGiaoDichService {

	@Autowired
	private LichSuGiaoDichRepository lichSuGiaoDichRepository;

	@Autowired
	private TaiKhoanDangNhapRepository taiKhoanDangNhapRepository;

	@Override
	public List<LichSuGiaoDich> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LichSuGiaoDich findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(LichSuGiaoDich dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(LichSuGiaoDich dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public DanhSachLichSuGiaoDichDTO getLichSuGiaoDich(int manguoigui) {
		DanhSachLichSuGiaoDichDTO dto = new DanhSachLichSuGiaoDichDTO();
		Date now = new Date();
		Calendar cal = new GregorianCalendar();
		cal.setTime(now);
		cal.add(Calendar.DAY_OF_MONTH, -30);
		Date before30d = cal.getTime();

		List<LichSuGiaoDich> danhSachChuyenTien = lichSuGiaoDichRepository
				.findByManguoiguiAndNgaygiaodichBetweenAndMaloaigiaodichNot(manguoigui, before30d, now, 3);
		List<LichSuGiaoDich> danhSachNhanTien = lichSuGiaoDichRepository
				.findByManguoinhanAndNgaygiaodichBetweenAndMaloaigiaodichNot(manguoigui, before30d, now, 3);
		List<LichSuGiaoDich> danhSachTraNo = lichSuGiaoDichRepository
				.findByManguoiguiAndNgaygiaodichBetweenAndMaloaigiaodich(manguoigui, before30d, now, 3);
		List<LichSuGiaoDich> danhSachNguoiKhacTraNo = lichSuGiaoDichRepository
				.findByManguoinhanAndNgaygiaodichBetweenAndMaloaigiaodich(manguoigui, before30d, now, 3);

		dto.setDanhSachChuyenTien(danhSachChuyenTien);
		dto.setDanhSachNhanTien(danhSachNhanTien);
		dto.setDanhSachTraNo(danhSachTraNo);
		dto.setDanhSachNguoiKhacTraNo(danhSachNguoiKhacTraNo);

		return dto;
	}

	@Override
	public DanhSachLichSuGiaoDichDTO getChuyenTien(DanhSachLichSuGiaoDichDTO danhSachLichSuGiaoDichDTO) {
		DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
		String start = danhSachLichSuGiaoDichDTO.getNgaybatdau();
		String end = danhSachLichSuGiaoDichDTO.getNgayketthuc();

		try {
			Date startDate = sourceFormat.parse(start);
			Date endDate = sourceFormat.parse(end);

			Calendar cal = new GregorianCalendar();
			cal.setTime(endDate);
			cal.add(Calendar.DAY_OF_MONTH, 1);
			cal.add(Calendar.MINUTE, -1);
			endDate = cal.getTime();

			List<LichSuGiaoDich> danhSachChuyenTien = lichSuGiaoDichRepository
					.findByManguoiguiAndNgaygiaodichBetweenAndMaloaigiaodichNot(
							danhSachLichSuGiaoDichDTO.getManguoigui(), startDate, endDate, 3);

			danhSachLichSuGiaoDichDTO.setDanhSachChuyenTien(danhSachChuyenTien);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return danhSachLichSuGiaoDichDTO;
	}

	@Override
	public DanhSachLichSuGiaoDichDTO getNhanTien(DanhSachLichSuGiaoDichDTO danhSachLichSuGiaoDichDTO) {
		DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
		String start = danhSachLichSuGiaoDichDTO.getNgaybatdau();
		String end = danhSachLichSuGiaoDichDTO.getNgayketthuc();

		try {
			Date startDate = sourceFormat.parse(start);
			Date endDate = sourceFormat.parse(end);

			Calendar cal = new GregorianCalendar();
			cal.setTime(endDate);
			cal.add(Calendar.DAY_OF_MONTH, 1);
			cal.add(Calendar.MINUTE, -1);
			endDate = cal.getTime();

			List<LichSuGiaoDich> danhSachNhanTien = lichSuGiaoDichRepository
					.findByManguoinhanAndNgaygiaodichBetweenAndMaloaigiaodichNot(
							danhSachLichSuGiaoDichDTO.getManguoigui(), startDate, endDate, 3);

			danhSachLichSuGiaoDichDTO.setDanhSachNhanTien(danhSachNhanTien);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return danhSachLichSuGiaoDichDTO;
	}

	@Override
	public DanhSachLichSuGiaoDichDTO getTraNo(DanhSachLichSuGiaoDichDTO danhSachLichSuGiaoDichDTO) {
		DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
		String start = danhSachLichSuGiaoDichDTO.getNgaybatdau();
		String end = danhSachLichSuGiaoDichDTO.getNgayketthuc();

		try {
			Date startDate = sourceFormat.parse(start);
			Date endDate = sourceFormat.parse(end);

			Calendar cal = new GregorianCalendar();
			cal.setTime(endDate);
			cal.add(Calendar.DAY_OF_MONTH, 1);
			cal.add(Calendar.MINUTE, -1);
			endDate = cal.getTime();

			List<LichSuGiaoDich> danhSachTraNo = lichSuGiaoDichRepository
					.findByManguoiguiAndNgaygiaodichBetweenAndMaloaigiaodich(danhSachLichSuGiaoDichDTO.getManguoigui(),
							startDate, endDate, 3);

			danhSachLichSuGiaoDichDTO.setDanhSachTraNo(danhSachTraNo);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return danhSachLichSuGiaoDichDTO;
	}

	@Override
	public DanhSachLichSuGiaoDichDTO getNguoiKhacTraNo(DanhSachLichSuGiaoDichDTO danhSachLichSuGiaoDichDTO) {
		DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
		String start = danhSachLichSuGiaoDichDTO.getNgaybatdau();
		String end = danhSachLichSuGiaoDichDTO.getNgayketthuc();

		try {
			Date startDate = sourceFormat.parse(start);
			Date endDate = sourceFormat.parse(end);

			Calendar cal = new GregorianCalendar();
			cal.setTime(endDate);
			cal.add(Calendar.DAY_OF_MONTH, 1);
			cal.add(Calendar.MINUTE, -1);
			endDate = cal.getTime();

			List<LichSuGiaoDich> danhSachNguoiKhacTraNo = lichSuGiaoDichRepository
					.findByManguoinhanAndNgaygiaodichBetweenAndMaloaigiaodich(danhSachLichSuGiaoDichDTO.getManguoigui(),
							startDate, endDate, 3);

			danhSachLichSuGiaoDichDTO.setDanhSachNguoiKhacTraNo(danhSachNguoiKhacTraNo);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return danhSachLichSuGiaoDichDTO;
	}

	@Override
	public List<LichSuGiaoDich> getLichSuGiaoDichByEmployee(DanhSachLichSuGiaoDichDTO danhSachLichSuGiaoDichDTO) {
		TaiKhoanDangNhap taiKhoanDangNhap = taiKhoanDangNhapRepository
				.findByEmailOrSodienthoai(danhSachLichSuGiaoDichDTO.getEmail(), danhSachLichSuGiaoDichDTO.getEmail());
		if (taiKhoanDangNhap == null) {
			return null;
		}

		DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
		String start = danhSachLichSuGiaoDichDTO.getNgaybatdau();
		String end = danhSachLichSuGiaoDichDTO.getNgayketthuc();

		try {
			Date startDate = sourceFormat.parse(start);
			Date endDate = sourceFormat.parse(end);

			Calendar cal = new GregorianCalendar();
			cal.setTime(endDate);
			cal.add(Calendar.DAY_OF_MONTH, 1);
			cal.add(Calendar.MINUTE, -1);
			endDate = cal.getTime();

			return lichSuGiaoDichRepository.findByManguoiguiAndNgaygiaodichBetweenOrManguoinhanAndNgaygiaodichBetween(
					taiKhoanDangNhap.getMataikhoan(), startDate, endDate, taiKhoanDangNhap.getMataikhoan(), startDate,
					endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public LichSuGiaoDichDetailsDTO getLichSuGiaoDichByEmployeeDetails(int id) {
		LichSuGiaoDich lichSuGiaoDich = lichSuGiaoDichRepository.findById(id).get();
		if(lichSuGiaoDich == null) {
			return null;
		}

		LichSuGiaoDichDetailsDTO chiTietLichSuGiaoDich = new LichSuGiaoDichDetailsDTO();
		chiTietLichSuGiaoDich.setId(lichSuGiaoDich.getId());
		chiTietLichSuGiaoDich.setManguoigui(lichSuGiaoDich.getManguoigui());
		chiTietLichSuGiaoDich.setMataikhoannguoigui(lichSuGiaoDich.getMataikhoannguoigui());
		chiTietLichSuGiaoDich.setTennguoigui(lichSuGiaoDich.getTennguoigui());
		chiTietLichSuGiaoDich.setManguoinhan(lichSuGiaoDich.getManguoinhan());
		chiTietLichSuGiaoDich.setMataikhoannguoinhan(lichSuGiaoDich.getMataikhoannguoinhan());
		chiTietLichSuGiaoDich.setTennguoinhan(lichSuGiaoDich.getTennguoinhan());
		chiTietLichSuGiaoDich.setSotiengiaodich(lichSuGiaoDich.getSotiengiaodich());
		chiTietLichSuGiaoDich.setNoidungchuyenkhoan(lichSuGiaoDich.getNoidungchuyenkhoan());
		chiTietLichSuGiaoDich.setManganhanggui(lichSuGiaoDich.getManganhanggui());
		chiTietLichSuGiaoDich.setTennganhanggui(lichSuGiaoDich.getNganhanggui().getTennganhang());
		chiTietLichSuGiaoDich.setManganhangnhan(lichSuGiaoDich.getManganhangnhan());
		chiTietLichSuGiaoDich.setTennganhangnhan(lichSuGiaoDich.getNganhangnhan().getTennganhang());
		chiTietLichSuGiaoDich.setNgaygiaodich(lichSuGiaoDich.getNgaygiaodich());
		chiTietLichSuGiaoDich.setSignature(lichSuGiaoDich.getSignature());
		chiTietLichSuGiaoDich.setMaloaigiaodich(lichSuGiaoDich.getMaloaigiaodich());
		chiTietLichSuGiaoDich.setTenloaigiaodich(lichSuGiaoDich.getLoaigiaodich().getTenloaigiaodich());
		chiTietLichSuGiaoDich.setTrangthai(lichSuGiaoDich.getTrangthai());
		
		return chiTietLichSuGiaoDich;
	}

}
