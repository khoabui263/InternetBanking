package com.khoa.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
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

import com.khoa.dto.LichSuGiaoDichDTO;
import com.khoa.dto.LienNganHangDTO;
import com.khoa.entity.GoiNho;
import com.khoa.entity.LichSuGiaoDich;
import com.khoa.entity.OTP;
import com.khoa.entity.TaiKhoanDangNhap;
import com.khoa.entity.TaiKhoanThanhToan;
import com.khoa.repository.GoiNhoRepository;
import com.khoa.repository.LichSuGiaoDichRepository;
import com.khoa.repository.OTPRepository;
import com.khoa.repository.TaiKhoanDangNhapRepository;
import com.khoa.repository.TaiKhoanThanhToanRepository;
import com.khoa.service.OTPService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class OTPServiceImpl implements OTPService {

	private static final String SECRET = "localtorsa";
	private static final String URLCONFIRMTRANSFERLOCALTORSA = "http://localhost:8080/api/rsa/confirmTranfer";

	@Autowired
	private OTPRepository oTPRepository;

	@Autowired
	private TaiKhoanDangNhapRepository taiKhoanDangNhapRepository;

	@Autowired
	private TaiKhoanThanhToanRepository taiKhoanThanhToanRepository;

	@Autowired
	private LichSuGiaoDichRepository lichSuGiaoDichRepository;

	@Autowired
	private GoiNhoRepository goiNhoRepository;

	@Override
	public List<OTP> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OTP findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(OTP dto) {
		oTPRepository.save(dto);

	}

	@Override
	public void update(OTP dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<OTP> findAllByOrderByThoiGianLuuAsc() {
		return oTPRepository.findAllByOrderByThoigianluuAsc();
	}

	@Override
	public OTP findByMaotpAndEmail(int maOTP, String email, String newPassWord) {
		OTP otp = oTPRepository.findByMaotpAndEmail(maOTP, email);
		Date now = new Date();

//		long diff = now.getTime() - otp.getThoigianluu().getTime();
//		long diffMinutes = diff / (60 * 1000) % 60;
//		System.out.println(now.getTime());
//		System.out.println(otp.getThoigianluu().getTime());
//		System.out.println(diff);
//		System.out.println(diffMinutes);

		if (otp == null || now.getTime() < otp.getThoigianluu().getTime()
				|| now.getTime() > (otp.getThoigianluu().getTime() + 3600000)) {
			oTPRepository.delete(otp);
			return null;
		}

		TaiKhoanDangNhap taiKhoanDangNhap = taiKhoanDangNhapRepository.findByEmailOrSodienthoai(email, email);
		String hashPassWord = BCrypt.hashpw(newPassWord, BCrypt.gensalt(12));
		taiKhoanDangNhap.setMatkhau(hashPassWord);
		TaiKhoanDangNhap taiKhoanSauKhiUpdate = taiKhoanDangNhapRepository.save(taiKhoanDangNhap);
		oTPRepository.delete(otp);

		if (taiKhoanSauKhiUpdate == null) {
			return null;
		}

		return new OTP();
	}

	@Override
	public GoiNho confirmTransfer(LichSuGiaoDichDTO lichSuGiaoDichDTO) {
		OTP otp = oTPRepository.findByMaotpAndEmail(lichSuGiaoDichDTO.getOtp(), lichSuGiaoDichDTO.getEmail());
		Date now = new Date();
		if (otp == null || now.getTime() < otp.getThoigianluu().getTime()
				|| now.getTime() > (otp.getThoigianluu().getTime() + 3600000)) {
			oTPRepository.delete(otp);
			return null;
		}
		oTPRepository.delete(otp);

		TaiKhoanThanhToan taiKhoanGui = taiKhoanThanhToanRepository
				.findFirstByMataikhoanthanhtoan(lichSuGiaoDichDTO.getMataikhoannguoigui());
		TaiKhoanThanhToan taiKhoanNhan = taiKhoanThanhToanRepository
				.findFirstByMataikhoanthanhtoan(lichSuGiaoDichDTO.getMataikhoannguoinhan());
		long soDuNguoiGui = Long.parseLong(taiKhoanGui.getSodu());
		long soDuNguoiNhan = Long.parseLong(taiKhoanNhan.getSodu());
		long soTienGiaoDich = Long.parseLong(lichSuGiaoDichDTO.getSotiengiaodich());

		if (lichSuGiaoDichDTO.getNguoitraphi() == 0) {
			soDuNguoiGui = soDuNguoiGui - soTienGiaoDich;
			soDuNguoiNhan = soDuNguoiNhan + soTienGiaoDich - 5000;

		} else if (lichSuGiaoDichDTO.getNguoitraphi() == 1) {
			soDuNguoiGui = soDuNguoiGui - soTienGiaoDich - 5000;
			soDuNguoiNhan = soDuNguoiNhan + soTienGiaoDich;
		}

		taiKhoanGui.setSodu(soDuNguoiGui + "");
		taiKhoanNhan.setSodu(soDuNguoiNhan + "");
		TaiKhoanThanhToan taiKhoanGuiSauGiaoDich = taiKhoanThanhToanRepository.save(taiKhoanGui);
		TaiKhoanThanhToan taiKhoanNhanSauGiaoDich = taiKhoanThanhToanRepository.save(taiKhoanNhan);

		if (taiKhoanGuiSauGiaoDich == null || taiKhoanNhanSauGiaoDich == null) {
			return null;
		}

		LichSuGiaoDich lichSuGiaoDich = lichSuGiaoDichRepository.save(new LichSuGiaoDich(
				taiKhoanGuiSauGiaoDich.getMataikhoandangnhap(), lichSuGiaoDichDTO.getMataikhoannguoigui(),
				lichSuGiaoDichDTO.getTennguoigui(), taiKhoanNhanSauGiaoDich.getMataikhoandangnhap(),
				lichSuGiaoDichDTO.getMataikhoannguoinhan(), lichSuGiaoDichDTO.getTennguoinhan(),
				lichSuGiaoDichDTO.getSotiengiaodich(), lichSuGiaoDichDTO.getNoidungchuyenkhoan(),
				lichSuGiaoDichDTO.getManganhanggui(), lichSuGiaoDichDTO.getManganhangnhan(), new Date(), null, 1, 1));
		if (lichSuGiaoDich == null) {
			return null;
		}

		GoiNho goiNho = goiNhoRepository.findFirstByMataikhoancannhoAndMataikhoangoinho(
				lichSuGiaoDichDTO.getMataikhoancannho(), lichSuGiaoDichDTO.getMataikhoannguoinhan());
		if (goiNho == null) {
			return new GoiNho(0, lichSuGiaoDichDTO.getMataikhoancannho(), lichSuGiaoDichDTO.getMataikhoannguoinhan(),
					lichSuGiaoDichDTO.getMataikhoannguoinhan() + "", lichSuGiaoDichDTO.getTennguoinhan(), "",
					lichSuGiaoDichDTO.getManganhangnhan(), 1);
		}
		return new GoiNho(0);
	}

	@Override
	public GoiNho confirmTransferLocalToRSA(LichSuGiaoDichDTO lichSuGiaoDichDTO)
			throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
		OTP otp = oTPRepository.findByMaotpAndEmail(lichSuGiaoDichDTO.getOtp(), lichSuGiaoDichDTO.getEmail());
		Date now = new Date();
		long timeStampe = now.getTime();
		if (otp == null || now.getTime() < otp.getThoigianluu().getTime() || now.getTime() > (otp.getThoigianluu().getTime() + 3600000)) {
			return null;
		}
//		oTPRepository.delete(otp);
		
		// Lấy private key và public key
		String stringprv = new String(Files.readAllBytes(Paths.get("privatersa64.pem")));
		stringprv =  stringprv.replaceAll("\\n", "").replace("-----BEGIN RSA PRIVATE KEY-----", "").replace("-----END RSA PRIVATE KEY-----", "");
		System.out.println("stringprv: " + stringprv);
		PKCS8EncodedKeySpec ksprv = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(stringprv));
		KeyFactory kfprv = KeyFactory.getInstance("RSA");
		PrivateKey pvt2 = kfprv.generatePrivate(ksprv);
		
		Signature sign = Signature.getInstance("SHA512withRSA"); // Dùng thuật toán SHA256 để ký, hoặc dùng các loại khác
		sign.initSign(pvt2);
		String chuoiSignature = "local" + "|" + lichSuGiaoDichDTO.getMataikhoannguoinhan() + "|" + timeStampe + "|" + SECRET;
		byte[] bytes1 = chuoiSignature.getBytes("UTF-8");
		sign.update(bytes1);
		byte[] signature = sign.sign();
		System.out.println("signature: " + signature);
		
		long soTienGiaoDich = Long.parseLong(lichSuGiaoDichDTO.getSotiengiaodich());
		if (lichSuGiaoDichDTO.getNguoitraphi() == 0) {
			soTienGiaoDich = soTienGiaoDich - 5000;
		}
		String chuoiSoTienGiaoDich = soTienGiaoDich+"";
		
		LienNganHangDTO body = new LienNganHangDTO();
		body.setPartnerCode("local");
		body.setTimeStampe(timeStampe);
		body.setMataikhoanthanhtoan(lichSuGiaoDichDTO.getMataikhoannguoinhan());
		body.setSoTienChuyen(chuoiSoTienGiaoDich);
		body.setNoiDungChuyen(lichSuGiaoDichDTO.getNoidungchuyenkhoan());
		String chuoiGoc = "local" + "|" + lichSuGiaoDichDTO.getMataikhoannguoinhan() + "|"
				+ chuoiSoTienGiaoDich + "|" + lichSuGiaoDichDTO.getNoidungchuyenkhoan() + "|"
				+ timeStampe + "|" + SECRET;
		System.out.println("Gói tin gốc: " + chuoiGoc);
		String chuoiHash = BCrypt.hashpw(chuoiGoc, BCrypt.gensalt(12));
		body.setChuoiMaHoa(chuoiHash);
		body.setSignature(signature);

		
		final long JWT_EXPIRATION = 1000 * 60 * 60 * 24 * 10;
		String accessToken = Jwts.builder().setSubject(lichSuGiaoDichDTO.getEmail()).setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + JWT_EXPIRATION)).signWith(SignatureAlgorithm.HS512, "yeet")
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
		ResponseEntity<LienNganHangDTO> response = restTemplate.exchange(URLCONFIRMTRANSFERLOCALTORSA, HttpMethod.POST, entity,
				LienNganHangDTO.class);
		LienNganHangDTO result = response.getBody();
		System.out.println(result.getStatus() + " - " + result.getMessage());
		
		if(result.getStatus() != 0) {
			return null;
		}
		
		String stringpub = new String(Files.readAllBytes(Paths.get("publicrsa64.pem")));
		stringpub = stringpub.replaceAll("\\n", "").replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "");
		System.out.println("stringpub: " + stringpub);
		X509EncodedKeySpec kspub = new X509EncodedKeySpec(Base64.getDecoder().decode(stringpub));
		KeyFactory kfpub = KeyFactory.getInstance("RSA");
		PublicKey pub2 = kfpub.generatePublic(kspub);
		
		boolean verified = true;
		Signature sign2 = Signature.getInstance("SHA512withRSA");
		sign2.initVerify(pub2);
		String chuoiSignatureBenNhan = "200-OK|" + SECRET;
		byte[] bytes2 = chuoiSignatureBenNhan.getBytes("UTF-8");
		sign2.update(bytes2);
		
		boolean bool = sign2.verify(result.getSignature());
		if(bool == false) {
			return null;
		}

		TaiKhoanThanhToan taiKhoanGui = taiKhoanThanhToanRepository.findFirstByMataikhoanthanhtoan(lichSuGiaoDichDTO.getMataikhoannguoigui());
		long soDuNguoiGui = Long.parseLong(taiKhoanGui.getSodu());
		long soTienChuyen = Long.parseLong(lichSuGiaoDichDTO.getSotiengiaodich());
		
		if (lichSuGiaoDichDTO.getNguoitraphi() == 0) {
			soDuNguoiGui = soDuNguoiGui - soTienChuyen;

		} else if (lichSuGiaoDichDTO.getNguoitraphi() == 1) {
			soDuNguoiGui = soDuNguoiGui - soTienChuyen - 5000;
		}

		taiKhoanGui.setSodu(soDuNguoiGui + "");
		TaiKhoanThanhToan taiKhoanGuiSauGiaoDich = taiKhoanThanhToanRepository.save(taiKhoanGui);

		if (taiKhoanGuiSauGiaoDich == null) {
			return null;
		}
		
		

		LichSuGiaoDich lichSuGiaoDich = new LichSuGiaoDich();		
		lichSuGiaoDich.setMaloaigiaodich(2);
		lichSuGiaoDich.setManganhanggui(1);
		lichSuGiaoDich.setManganhangnhan(2);
		lichSuGiaoDich.setManguoigui(taiKhoanGuiSauGiaoDich.getMataikhoandangnhap());
		lichSuGiaoDich.setManguoinhan(5); // Demo
		lichSuGiaoDich.setMataikhoannguoigui(lichSuGiaoDichDTO.getMataikhoannguoigui());
		lichSuGiaoDich.setMataikhoannguoinhan(lichSuGiaoDichDTO.getMataikhoannguoinhan());
		lichSuGiaoDich.setNgaygiaodich(new Date());
		lichSuGiaoDich.setSotiengiaodich(lichSuGiaoDichDTO.getSotiengiaodich());
		lichSuGiaoDich.setNoidungchuyenkhoan(lichSuGiaoDichDTO.getNoidungchuyenkhoan());
		lichSuGiaoDich.setTennguoigui(lichSuGiaoDichDTO.getTennguoigui());
		lichSuGiaoDich.setTennguoinhan(lichSuGiaoDichDTO.getTennguoinhan());
		lichSuGiaoDich.setSignatureNguoiGui(signature.toString());
		lichSuGiaoDich.setSignatureNguoiNhan(result.getSignature().toString());
		lichSuGiaoDich.setTrangthai(1);
		
		LichSuGiaoDich lichSuGiaoDichRSA = lichSuGiaoDichRepository.save(lichSuGiaoDich);
		
		if (lichSuGiaoDichRSA == null) {
			return null;
		}

		GoiNho goiNho = goiNhoRepository.findFirstByMataikhoancannhoAndMataikhoangoinho(
				lichSuGiaoDichDTO.getMataikhoancannho(), lichSuGiaoDichDTO.getMataikhoannguoinhan());
		if (goiNho == null) {
			return new GoiNho(0, lichSuGiaoDichDTO.getMataikhoancannho(), lichSuGiaoDichDTO.getMataikhoannguoinhan(),
					lichSuGiaoDichDTO.getMataikhoannguoinhan() + "", lichSuGiaoDichDTO.getTennguoinhan(), "",
					lichSuGiaoDichDTO.getManganhangnhan(), 1);
		}
		return new GoiNho(0);
	}

}
