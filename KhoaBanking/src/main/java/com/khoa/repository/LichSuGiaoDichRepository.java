package com.khoa.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.khoa.entity.LichSuGiaoDich;

@Repository
public interface LichSuGiaoDichRepository extends JpaRepository<LichSuGiaoDich, Integer> {
	//	Chuyển tiền
	public List<LichSuGiaoDich> findByManguoiguiAndNgaygiaodichBetweenAndMaloaigiaodichNot(int manguoigui, Date before30d, Date now, int maloaigiaodich);
	//	Nhận tiền
	public List<LichSuGiaoDich> findByManguoinhanAndNgaygiaodichBetweenAndMaloaigiaodichNot(int manguoinhan, Date before30d, Date now, int maloaigiaodich);
	//	Trả nợ
	public List<LichSuGiaoDich> findByManguoiguiAndNgaygiaodichBetweenAndMaloaigiaodich(int manguoigui, Date before30d, Date now, int maloaigiaodich);
	//	Người khác trả nợ
	public List<LichSuGiaoDich> findByManguoinhanAndNgaygiaodichBetweenAndMaloaigiaodich(int manguoinhan, Date before30d, Date now, int maloaigiaodich);
	
	public List<LichSuGiaoDich> findByManguoiguiAndNgaygiaodichBetweenOrManguoinhanAndNgaygiaodichBetween(int manguoigui, Date before30d, Date now, int manguoinhan, Date before30dd, Date nowd);
	
	public List<LichSuGiaoDich> findByManganhangguiAndNgaygiaodichBetweenOrManganhangnhanAndNgaygiaodichBetween(int manganhanggui, Date before30d, Date now, int manganhangnhan, Date before30dd, Date nowd);
}
