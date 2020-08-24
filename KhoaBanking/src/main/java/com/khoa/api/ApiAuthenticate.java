package com.khoa.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khoa.dto.LoginDTO;
import com.khoa.dto.ResultMessage;
import com.khoa.dto.TaiKhoanDangNhapDTO;
import com.khoa.dto.TokensDTO;
import com.khoa.entity.TaiKhoanDangNhap;
import com.khoa.repository.TaiKhoanDangNhapRepository;
import com.khoa.service.TaiKhoanDangNhapService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
//@CrossOrigin
@RequestMapping("/api/authenticate")
public class ApiAuthenticate {
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Autowired
	private ResultMessage resultMessage;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TaiKhoanDangNhapService taiKhoanDangNhapService;
	
	@PostMapping("login")
	public ResponseEntity login(@RequestBody LoginDTO loginDTO) {
		try {
			Authentication authentication = new UsernamePasswordAuthenticationToken(loginDTO.getUserName(), loginDTO.getPassWord());
			
			// Gọi method authenticate() để xác thực thông tin thông qua UserDetailServiceImpl
			// Nó sẽ trả về UserDetail và gán trong biến authentication
			authentication = authenticationManager.authenticate(authentication);

			// Sau đó set thông tin UserDetail vừa r để sử dụng cho việc phân quyền
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			
			// Lưu refeshtoken
			String str = loginDTO.getUserName() + secret;
			String refreshToken = BCrypt.hashpw(str, BCrypt.gensalt(12));
			TaiKhoanDangNhapDTO taiKhoanDangNhapDTO = taiKhoanDangNhapService.findByEmailOrSodienthoai(loginDTO.getUserName(), loginDTO.getUserName());
			taiKhoanDangNhapDTO.setRefeshtoken(refreshToken);
			taiKhoanDangNhapService.update(taiKhoanDangNhapDTO);
			
			// Tạo token trả về cho Client
			Date now = new Date();
			final long JWT_EXPIRATION = 1000 * 60 * 60 * 24 * 10;
			String accessToken = Jwts.builder()
							.setSubject(taiKhoanDangNhapDTO.getEmail())
							.claim("mataikhoan",taiKhoanDangNhapDTO.getMataikhoan())
							.claim("role", taiKhoanDangNhapDTO.getMaloainguoidung())
							.claim("name", taiKhoanDangNhapDTO.getHoten())
							.setIssuedAt(now)
							.setExpiration(new Date(now.getTime() + JWT_EXPIRATION))
							.signWith(SignatureAlgorithm.HS512, secret)
							.compact();
			
			Map<String, Object> tokens = new HashMap<>();
			tokens.put("accessToken", accessToken);
			tokens.put("refreshToken", refreshToken);
			tokens.put("userName", loginDTO.getUserName());

			return  ResponseEntity.status(HttpStatus.OK).body(tokens);
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.OK).body(resultMessage.ShowResult("1", "Sai thông tin đăng nhập"));
		}
	}
	
	@PostMapping("refeshToken")
	public ResponseEntity refeshToken(@RequestBody TokensDTO tokensDTO) {
		TaiKhoanDangNhapDTO taiKhoanDangNhapDTO = taiKhoanDangNhapService.findByEmailOrSodienthoai(tokensDTO.getUserName(), tokensDTO.getUserName());
		if(taiKhoanDangNhapDTO.getRefeshtoken().equals(tokensDTO.getRefreshToken())){			
			Date now = new Date();
			final long JWT_EXPIRATION = 1000 * 60 * 60 * 24 * 10;
			String accessToken = Jwts.builder()
							.setSubject(tokensDTO.getUserName())
							.setIssuedAt(now)
							.setExpiration(new Date(now.getTime() + JWT_EXPIRATION))
							.signWith(SignatureAlgorithm.HS512, secret)
							.compact();
			
			Map<String, Object> tokens = new HashMap<>();
			tokens.put("accessToken", accessToken);
			tokens.put("refeshToken", taiKhoanDangNhapDTO.getRefeshtoken());
			tokens.put("userName", tokensDTO.getUserName());
			
			return ResponseEntity.status(HttpStatus.OK).body(tokens);
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultMessage.ShowResult("1", "lỗi r"));
	}

}
