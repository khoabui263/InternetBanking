package com.khoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.khoa.entity.NganHang;

@Repository
public interface NganHangRepository extends JpaRepository<NganHang, Integer>{

	@Query("FROM NganHang n WHERE n.partnercode LIKE CONCAT('%',:partnercode,'%')")
	public NganHang findPartnerCode(@Param("partnercode") String partnercode);
}
