package com.khoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.khoa.dto.TaiKhoanDangNhapDTO;
import com.khoa.entity.TaiKhoanDangNhap;

@Repository
public interface TaiKhoanDangNhapRepository extends JpaRepository<TaiKhoanDangNhap, Integer> {

	public TaiKhoanDangNhap findByEmailOrSodienthoai(String email, String soDienThoai);

	@Query("SELECT new com.khoa.dto.TaiKhoanDangNhapDTO(t.hoten, t.email, t.sodienthoai, l.tenloainguoidung) FROM TaiKhoanDangNhap t JOIN LoaiNguoiDung l ON t.maloainguoidung = l.maloainguoidung")
	public List<TaiKhoanDangNhapDTO> findAllDetailsTaiKhoanDangNhap();
}
