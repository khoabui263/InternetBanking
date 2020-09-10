package com.khoa.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.khoa.dto.FindTaiKhoanGuiVaNhanDTO;
import com.khoa.dto.LienNganHangDTO;
import com.khoa.dto.ResultMessage;
import com.khoa.dto.TaiKhoanThanhToanDTO;
import com.khoa.entity.TaiKhoanThanhToan;
import com.khoa.repository.TaiKhoanThanhToanRepository;
import com.khoa.service.LienNganHangService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class LienNganHangServiceImpl implements LienNganHangService{
	private static final String SECRET = "localtorsa";
	private static final String URLFINDRSAACCOUNT = "http://localhost:8080/api/rsa/getaccount";
	
	@Autowired
	private TaiKhoanThanhToanRepository taiKhoanThanhToanRepository;

	@Override
	public FindTaiKhoanGuiVaNhanDTO localFindAccountRSA(LienNganHangDTO lienNganHangDTO) {
		long timeStampw = new Date().getTime();
		LienNganHangDTO body = new LienNganHangDTO();
		body.setPartnerCode("local");
		body.setTimeStampe(timeStampw);
		body.setMataikhoanthanhtoan(lienNganHangDTO.getMataikhoanthanhtoan());
		String chuoiGoc = "local"+"|"+lienNganHangDTO.getMataikhoanthanhtoan()+"|"+timeStampw+"|"+SECRET;
		String chuoiHash = BCrypt.hashpw(chuoiGoc, BCrypt.gensalt(12));
		body.setChuoiMaHoa(chuoiHash);
		
		Date now = new Date();
		final long JWT_EXPIRATION = 1000 * 60 * 60 * 24 * 10;
		String accessToken = Jwts.builder()
				.setSubject(lienNganHangDTO.getEmail())
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + JWT_EXPIRATION))
				.signWith(SignatureAlgorithm.HS512, "yeet")
				.compact();
		HttpHeaders headers = new HttpHeaders();
		 
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        // Yêu cầu trả về định dạng JSON
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + accessToken);
 
        // HttpEntity<String>: To get result as String.
        HttpEntity<LienNganHangDTO> entity = new HttpEntity<>(body, headers);
 
        // RestTemplate
        RestTemplate restTemplate = new RestTemplate();
 
        // Gửi yêu cầu với phương thức GET, và các thông tin Headers.
        ResponseEntity<LienNganHangDTO> response = restTemplate.exchange(URLFINDRSAACCOUNT, HttpMethod.POST, entity, LienNganHangDTO.class);
        LienNganHangDTO result = response.getBody();
        System.out.println(result.getStatus() + " - " + result.getMessage());
        
        if(result.getStatus() != 0) {
        	return new FindTaiKhoanGuiVaNhanDTO("1", null);
        }
        
        List<TaiKhoanThanhToanDTO> danhSachGuiDTO = new ArrayList<TaiKhoanThanhToanDTO>();
		List<TaiKhoanThanhToan> danhSachTaiKhoanGui = taiKhoanThanhToanRepository.findByMataikhoandangnhapAndTrangthai(lienNganHangDTO.getMataikhoandangnhap(), 1);
		for (TaiKhoanThanhToan taiKhoanThanhToan : danhSachTaiKhoanGui) {
			if(taiKhoanThanhToan.getMataikhoanthanhtoan() == lienNganHangDTO.getMataikhoanthanhtoan()) {
				return new FindTaiKhoanGuiVaNhanDTO("2", null);
			}
			danhSachGuiDTO.add(new TaiKhoanThanhToanDTO(taiKhoanThanhToan.getMataikhoanthanhtoan(),
														taiKhoanThanhToan.getMataikhoandangnhap(),
														taiKhoanThanhToan.getSodu(),
														taiKhoanThanhToan.getTrangthai(),
														taiKhoanThanhToan.getTaiKhoanDangNhap().getHoten()));
		}
		
		FindTaiKhoanGuiVaNhanDTO dto = new FindTaiKhoanGuiVaNhanDTO(result.getHoten(), danhSachGuiDTO);
		
		return dto;
	}

}
