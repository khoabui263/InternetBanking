package com.khoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.khoa.entity.TaiKhoanLienNganHang;

@Repository
public interface TaiKhoanLienNganHangRepository extends JpaRepository<TaiKhoanLienNganHang, Long>{
	
	public TaiKhoanLienNganHang findByMataikhoan(long mataikhoan);

}
