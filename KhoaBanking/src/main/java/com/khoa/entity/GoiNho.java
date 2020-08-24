package com.khoa.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "goinho")
public class GoiNho {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int mataikhoancannho;
	private long mataikhoangoinho;
	private String hotennguoigoinho;
	private String bietdanhgoinho;
	private int manganhang;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mataikhoancannho", insertable = false, updatable = false)
	private TaiKhoanDangNhap taikhoancannho;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mataikhoangoinho", insertable = false, updatable = false)
	private TaiKhoanThanhToan taikhoangoinho;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manganhang", insertable = false, updatable = false)
	private NganHang nganhanggoinho;

	
}
