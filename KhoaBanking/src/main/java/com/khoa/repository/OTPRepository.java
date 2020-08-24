package com.khoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.khoa.entity.OTP;

@Repository
public interface OTPRepository extends JpaRepository<OTP, Integer> {

	public List<OTP> findAllByOrderByThoigianluuAsc();
}
