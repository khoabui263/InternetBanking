package com.khoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.khoa.entity.GoiNho;

@Repository
public interface GoiNhoRepository extends JpaRepository<GoiNho, Integer> {

	public GoiNho findFirstByMataikhoancannhoAndMataikhoangoinho(int mataikhoancannho, long mataikhoangoinho);

//	public List<GoiNho> findByChuoimanguoigoinhoContainingOrHotennguoigoinhoContainingOrBietdanhgoinhoContaining(
//			String chuoimanguoigoinho, String hotennguoigoinho, String bietdanhgoinho);

	@Query("FROM GoiNho g WHERE g.mataikhoancannho = :mataikhoancannho AND g.manganhang= :manganhang AND (g.chuoimanguoigoinho LIKE CONCAT('%',:chuoigoinho,'%') OR g.hotennguoigoinho LIKE CONCAT('%',:chuoigoinho,'%') OR g.bietdanhgoinho LIKE CONCAT('%',:chuoigoinho,'%'))")
	public List<GoiNho> searchGoiNho(@Param("mataikhoancannho") int mataikhoancannho, @Param("manganhang") int manganhang, @Param("chuoigoinho") String chuoigoinho);

}
