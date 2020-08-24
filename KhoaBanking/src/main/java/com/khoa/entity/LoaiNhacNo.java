package com.khoa.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "loainhacno")
public class LoaiNhacNo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int maloaino;
	
	private String tenloaino;
	
	@OneToMany(mappedBy = "nhacno", fetch = FetchType.LAZY)
	private List<NhacNo> danhsachnhacno;
}
