package com.khoa.api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.didisoft.pgp.PGPException;
import com.didisoft.pgp.PGPLib;
import com.didisoft.pgp.SignatureCheckResult;
import com.didisoft.pgp.exceptions.FileIsEncryptedException;
import com.khoa.dto.LienNganHangDTO;
import com.khoa.dto.NganHangDTO;
import com.khoa.dto.ResultMessage;
import com.khoa.entity.TaiKhoanLienNganHang;
import com.khoa.service.NganHangService;
import com.khoa.service.TaiKhoanLienNganHangService;

@RestController
@RequestMapping("/api/pgp")
public class ApiPGP {
	private static final long TIMEOUT = 1000 * 60 * 5;
	private static final String SECRET = "localtopgp";

	@Autowired
	private ResultMessage resultMessage;

	@Autowired
	private NganHangService nganHangService;
	
	@Autowired
	private TaiKhoanLienNganHangService taiKhoanLienNganHangService;

	@PostMapping("getaccount")
	public ResponseEntity getaccount(@RequestBody LienNganHangDTO lienNganHangDTO) {
		LienNganHangDTO result = new LienNganHangDTO();
		Date now = new Date();

		if (now.getTime() < lienNganHangDTO.getTimeStampe()
				|| now.getTime() > (lienNganHangDTO.getTimeStampe() + TIMEOUT)) {
			result.setStatus(1);
			result.setMessage("Gửi trễ hơn 5 phút");
			return ResponseEntity.status(HttpStatus.OK).body(result);
		}

		String chuoiGoc = lienNganHangDTO.getPartnerCode() + "|" + lienNganHangDTO.getMataikhoanthanhtoan() + "|"
				+ lienNganHangDTO.getTimeStampe() + "|" + SECRET;
		boolean checkOrigin = BCrypt.checkpw(chuoiGoc, lienNganHangDTO.getChuoiMaHoa());
		if (checkOrigin == false) {
			result.setStatus(2);
			result.setMessage("Gói tin giả mạo");
			return ResponseEntity.status(HttpStatus.OK).body(result);

		}

		NganHangDTO nganHang = nganHangService.findPartnerCode(lienNganHangDTO.getPartnerCode());
		if (nganHang == null) {
			result.setStatus(3);
			result.setMessage("Không tìm thấy ngân hàng đã liên kết");
			return ResponseEntity.status(HttpStatus.OK).body(result);

		}
		
		TaiKhoanLienNganHang taiKhoanLienNganHang = taiKhoanLienNganHangService.findByMataikhoan(lienNganHangDTO.getMataikhoanthanhtoan());
		if(taiKhoanLienNganHang == null) {
			result.setStatus(4);
			result.setMessage("Không tìm thấy tài khoản");
			return ResponseEntity.status(HttpStatus.OK).body(result);
		}
		
		result.setStatus(0);
		result.setHoten(taiKhoanLienNganHang.getHoten());
		result.setMessage("Thành công");
		return ResponseEntity.status(HttpStatus.OK).body(result);

	}
	
	@PostMapping("confirmTranfer")
	public ResponseEntity confirmTranfer(@RequestBody LienNganHangDTO lienNganHangDTO) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException, FileIsEncryptedException, PGPException {
		LienNganHangDTO result = new LienNganHangDTO();
		Date now = new Date();

		if (now.getTime() < lienNganHangDTO.getTimeStampe()
				|| now.getTime() > (lienNganHangDTO.getTimeStampe() + TIMEOUT)) {
			result.setStatus(1);
			result.setMessage("Gửi trễ hơn 5 phút");
			return ResponseEntity.status(HttpStatus.OK).body(result);
		}

		String chuoiGoc = lienNganHangDTO.getPartnerCode() + "|" + lienNganHangDTO.getMataikhoanthanhtoan() + "|"
				+ lienNganHangDTO.getSoTienChuyen() + "|" + lienNganHangDTO.getNoiDungChuyen() + "|"
				+ lienNganHangDTO.getTimeStampe() + "|" + SECRET;
		System.out.println("So sánh chuỗi: " + chuoiGoc);
		boolean checkOrigin = BCrypt.checkpw(chuoiGoc, lienNganHangDTO.getChuoiMaHoa());
		if (checkOrigin == false) {
			result.setStatus(2);
			result.setMessage("Gói tin giả mạo");
			return ResponseEntity.status(HttpStatus.OK).body(result);

		}
		
		PGPLib pgp = new PGPLib();
		boolean bool = false;
		SignatureCheckResult signatureCheck = pgp.verifyAndExtract("sign.pgp", "publicpgp.txt", "OUTPUT.txt");
		if (signatureCheck == SignatureCheckResult.SignatureVerified) {
			System.out.println("The signature is valid.");
			bool = true;
		} else if (signatureCheck == SignatureCheckResult.SignatureBroken) {
			System.out.println("Message corrupted or signature forged");
		} else if (signatureCheck == SignatureCheckResult.PublicKeyNotMatching) {
			System.out.println("Signature not matching provided public key (the message is from another sender)");
		} else {
			System.out.println("No signature found in message");
		}

		if(bool == false) {
			result.setStatus(5);
			result.setMessage("Signature giả mạo");
			return ResponseEntity.status(HttpStatus.OK).body(result);
		}

		NganHangDTO nganHang = nganHangService.findPartnerCode(lienNganHangDTO.getPartnerCode());
		if (nganHang == null) {
			result.setStatus(3);
			result.setMessage("Không tìm thấy ngân hàng đã liên kết");
			return ResponseEntity.status(HttpStatus.OK).body(result);

		}
		
		TaiKhoanLienNganHang taiKhoanLienNganHang = taiKhoanLienNganHangService.findByMataikhoan(lienNganHangDTO.getMataikhoanthanhtoan());
		if(taiKhoanLienNganHang == null) {
			result.setStatus(4);
			result.setMessage("Không tìm thấy tài khoản");
			return ResponseEntity.status(HttpStatus.OK).body(result);
		}
		
		long soDuTaiKhoan = Long.parseLong(taiKhoanLienNganHang.getSodu());
		long soTienGiaoDich = Long.parseLong(lienNganHangDTO.getSoTienChuyen());
		taiKhoanLienNganHang.setSodu((soDuTaiKhoan + soTienGiaoDich)+"");
		TaiKhoanLienNganHang taiKhoanLienNganHangSauGiaoDich = taiKhoanLienNganHangService.update(taiKhoanLienNganHang);
		if(taiKhoanLienNganHangSauGiaoDich == null) {
			result.setStatus(6);
			result.setMessage("Giao dịch thất bại");
			return ResponseEntity.status(HttpStatus.OK).body(result);
		}
		
		String chuoiSignature = "200-OK" + SECRET;
		PrintWriter writer = new PrintWriter("message.txt", "UTF-8");
		writer.print(chuoiSignature);
		writer.close();

		boolean asciiArmor = true;
		pgp.signFile("message.txt",
				"privatepgp.txt",
				"khoa",
				"sign.pgp",
				asciiArmor);

		BufferedReader br = new BufferedReader(new FileReader("sign.pgp"));
		String temp = "";
		String signature = "";
		while ((temp = br.readLine()) != null) {
			if (temp.equals("-----BEGIN PGP MESSAGE-----")
					|| temp.equals("Version: Didisoft OpenPGP Library for Java 3.2")
					|| temp.equals("-----END PGP MESSAGE-----")) {
				continue;
			} else {
				signature += temp;
			}
		}
		System.out.println("Signature trả về" + signature);
		
		result.setStatus(0);
		result.setHoten(taiKhoanLienNganHang.getHoten());
		result.setSignaturePGP(signature);
		result.setMessage("Thành công");
		return ResponseEntity.status(HttpStatus.OK).body(result);

	}
}
