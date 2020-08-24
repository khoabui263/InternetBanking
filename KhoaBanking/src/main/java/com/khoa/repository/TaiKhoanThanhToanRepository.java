package com.khoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.khoa.entity.TaiKhoanThanhToan;

@Repository
public interface TaiKhoanThanhToanRepository extends JpaRepository<TaiKhoanThanhToan, Long> {

//	public List<TaiKhoanThanhToan> findBy
}
