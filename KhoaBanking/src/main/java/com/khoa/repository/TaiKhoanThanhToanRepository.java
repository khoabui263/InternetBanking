package com.khoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.khoa.dto.TaiKhoanThanhToanDTO;
import com.khoa.entity.TaiKhoanThanhToan;

@Repository
public interface TaiKhoanThanhToanRepository extends JpaRepository<TaiKhoanThanhToan, Long> {

//	@Query("SELECT new com.khoa.dto.TaiKhoanThanhToanDTO(t.mataikhoanthanhtoan, t.mataikhoandangnhap, t.sodu, t.trangthai) FROM TaiKhoanThanhToan t WHERE t.mataikhoandangnhap= :mataikhoandangnhap")
//	public List<TaiKhoanThanhToanDTO> findByMataikhoandangnhap(@Param("mataikhoandangnhap") int mataikhoandangnhap);
	
//	@Query("SELECT new com.khoa.dto.TaiKhoanThanhToanDTO(t.mataikhoanthanhtoan, t.mataikhoandangnhap, t.sodu, t.trangthai) FROM TaiKhoanThanhToan t WHERE t.mataikhoanthanhtoan= :mataikhoanthanhtoan")
//	public List<TaiKhoanThanhToanDTO> findByMataikhoanthanhtoan(@Param("mataikhoanthanhtoan") long mataikhoanthanhtoan);
	
	public List<TaiKhoanThanhToan> findByMataikhoandangnhap(int mataikhoandangnhap);
	
	public List<TaiKhoanThanhToan> findByMataikhoanthanhtoan(long mataikhoanthanhtoan);
	
}