package com.khoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.khoa.entity.NganHang;

@Repository
public interface NganHangRepository extends JpaRepository<NganHang, Integer>{

}
