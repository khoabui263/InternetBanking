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
@Table(name = "loaigiaodich")
public class LoaiGiaoDich {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String tenloaigiaodich;
	
	@OneToMany(mappedBy = "loaigiaodich", fetch = FetchType.LAZY)
	private List<LichSuGiaoDich> lichsugiaodich;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTenloaigiaodich() {
		return tenloaigiaodich;
	}

	public void setTenloaigiaodich(String tenloaigiaodich) {
		this.tenloaigiaodich = tenloaigiaodich;
	}

	public List<LichSuGiaoDich> getLichsugiaodich() {
		return lichsugiaodich;
	}

	public void setLichsugiaodich(List<LichSuGiaoDich> lichsugiaodich) {
		this.lichsugiaodich = lichsugiaodich;
	}
	
	
	
}
