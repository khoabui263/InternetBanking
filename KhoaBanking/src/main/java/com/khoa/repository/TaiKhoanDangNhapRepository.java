package com.khoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.khoa.dto.TaiKhoanDangNhapDTO;
import com.khoa.entity.GoiNho;
import com.khoa.entity.TaiKhoanDangNhap;

@Repository
public interface TaiKhoanDangNhapRepository extends JpaRepository<TaiKhoanDangNhap, Integer> {

	public TaiKhoanDangNhap findByEmailOrSodienthoai(String email, String soDienThoai);
	
	public TaiKhoanDangNhap findByMataikhoanAndTrangthai(int mataikhoan, int trangthai);

	@Query("SELECT new com.khoa.dto.TaiKhoanDangNhapDTO(t.hoten, t.email, t.sodienthoai, l.tenloainguoidung) FROM TaiKhoanDangNhap t JOIN LoaiNguoiDung l ON t.maloainguoidung = l.maloainguoidung")
	public List<TaiKhoanDangNhapDTO> findAllDetailsTaiKhoanDangNhap();
	
	public List<TaiKhoanDangNhap> findByMaloainguoidungAndTrangthai(int maloainguoidung, int trangthai);
	
	@Query("FROM TaiKhoanDangNhap t WHERE t.maloainguoidung = 2 AND t.trangthai = 1 AND (t.email LIKE CONCAT('%',:search,'%') OR t.sodienthoai LIKE CONCAT('%',:search,'%'))")
	public List<TaiKhoanDangNhap> searchTaiKhoanNhanVien(@Param("search") String search);
	
}
