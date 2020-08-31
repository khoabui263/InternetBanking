package com.khoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.khoa.entity.NhacNo;

@Repository
public interface NhacNoRepository extends JpaRepository<NhacNo, Integer>{
	public List<NhacNo> findByManguoinhacnoAndMaloainhacno(int manguoinhacno, int maloainhacno);
	public List<NhacNo> findByManguoibinoAndMaloainhacno(int manguoibino, int maloainhacno);
}
