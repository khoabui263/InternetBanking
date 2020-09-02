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
import com.khoa.entity.LichSuGiaoDich;
import com.khoa.repository.LichSuGiaoDichRepository;
import com.khoa.service.LichSuGiaoDichService;

@Service
public class LichSuGiaoDichServiceImpl implements LichSuGiaoDichService {

	@Autowired
	private LichSuGiaoDichRepository lichSuGiaoDichRepository;

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
					.findByManguoiguiAndNgaygiaodichBetweenAndMaloaigiaodich(
							danhSachLichSuGiaoDichDTO.getManguoigui(), startDate, endDate, 3);
			
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
					.findByManguoinhanAndNgaygiaodichBetweenAndMaloaigiaodich(
							danhSachLichSuGiaoDichDTO.getManguoigui(), startDate, endDate, 3);
			
			danhSachLichSuGiaoDichDTO.setDanhSachNguoiKhacTraNo(danhSachNguoiKhacTraNo);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return danhSachLichSuGiaoDichDTO;
	}

}