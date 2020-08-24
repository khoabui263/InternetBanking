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
@Table(name = "nhacno")
public class NhacNo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private long mataikhoannhacno;
	private String tennguoinhacno;
	private long mataikhoanduocnhacno;
	private String tennguoibino;
	private String sotienno;
	private String noidungnhacno;
	private int manhacno;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mataikhoannhacno", insertable = false, updatable = false)
	private TaiKhoanThanhToan taikhoannhacno;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mataikhoanduocnhacno", insertable = false, updatable = false)
	private TaiKhoanThanhToan taikhoanduocnhacno;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manhacno", insertable = false, updatable = false)
	private LoaiNhacNo nhacno;
	
}
