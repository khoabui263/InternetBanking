package com.khoa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoa.dto.GoiNhoDTO;
import com.khoa.entity.GoiNho;
import com.khoa.repository.GoiNhoRepository;
import com.khoa.service.GoiNhoService;

@Service
public class GoiNhoServiceImpl implements GoiNhoService {

	@Autowired
	private GoiNhoRepository goiNhoRepository;

	@Override
	public List<GoiNho> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GoiNho findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GoiNho add(GoiNho dto) {
		return goiNhoRepository.save(dto);

	}

	@Override
	public void update(GoiNho dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<GoiNhoDTO> searchGoiNho(int maTaiKhoanCanNho, String chuoimanguoigoinho, int maNganHang) {
		List<GoiNhoDTO> dto = new ArrayList<GoiNhoDTO>();
		List<GoiNho> goiNho = goiNhoRepository.searchGoiNho(maTaiKhoanCanNho,maNganHang,chuoimanguoigoinho);
		for (GoiNho gn : goiNho) {
			dto.add(new GoiNhoDTO(gn.getMataikhoancannho(), gn.getMataikhoangoinho(), gn.getChuoimanguoigoinho(), gn.getHotennguoigoinho(), gn.getBietdanhgoinho()));
		}
		return dto;
	}

}
