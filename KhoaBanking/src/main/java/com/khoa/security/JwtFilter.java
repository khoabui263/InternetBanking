package com.khoa.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

public class JwtFilter extends BasicAuthenticationFilter {

	private UserDetailsService userDetailsService;

//	@Value("${jwt.secret}")
//	private String secret;

	public JwtFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String tokenHeader = request.getHeader("Authorization");
		System.out.println("tokenHeader: " + tokenHeader);

		// Kiểm tra token có dc gửi lên ko và có bắt đầu = "Bearer "
		if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
			String token = tokenHeader.replace("Bearer ", "");
			
			try {
				Date expiration = Jwts.parser()
						.setSigningKey("yeet")
						.parseClaimsJws(token)
						.getBody()
						.getExpiration();
			} catch (ExpiredJwtException e) {
				response.sendError(401, "Đang refesh lại token");
				return;
			}


			// Giải mã email
			String email = (String) Jwts.parser()
										.setSigningKey("yeet")
										.parseClaimsJws(token)
										.getBody()
//										.get("passWord");
										.getSubject();

			System.out.println("Email: " + email);

			// Dùng email để truy vấn database
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);

			// Tạo đối tượng Authentication để để truyền vào SpringContext
			Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
					userDetails.getAuthorities());

			// Set thông tin vào SecurityContextHolder để bên config lấy dc tên quyền
			SecurityContextHolder.getContext().setAuthentication(authentication);

			// Cho phép đi tiếp
			chain.doFilter(request, response);
		} else {
			response.sendError(403, "Không có token, vui lòng đăng nhập");

//			response.setStatus(sc);
		}
	}

}
