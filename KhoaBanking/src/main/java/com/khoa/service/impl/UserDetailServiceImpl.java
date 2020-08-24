package com.khoa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.khoa.dto.UserDetailsDTO;
import com.khoa.entity.TaiKhoanDangNhap;
import com.khoa.repository.TaiKhoanDangNhapRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private TaiKhoanDangNhapRepository taiKhoanDangNhapRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		TaiKhoanDangNhap taiKhoanDangNhap = taiKhoanDangNhapRepository.findByEmailOrSodienthoai(username,username);
		if(taiKhoanDangNhap == null) throw new UsernameNotFoundException("Không tìm thấy tài khoản");
		
		// Trong thực tế, 1 user có thể có nhiều quyền => ngta bắt phải tạo ra 1 danh sách
		List<GrantedAuthority> authroties = new ArrayList<GrantedAuthority>();
		String roleName = taiKhoanDangNhap.getSodienthoai();
		authroties.add(new SimpleGrantedAuthority(roleName));
		
		UserDetailsDTO userDetailsDTO = new UserDetailsDTO(taiKhoanDangNhap.getEmail(), taiKhoanDangNhap.getMatkhau(), authroties);
		return userDetailsDTO;
	}
}
