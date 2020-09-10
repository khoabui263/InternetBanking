package com.khoa.api;

import java.io.IOException;
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

import com.khoa.dto.LienNganHangDTO;
import com.khoa.dto.NganHangDTO;
import com.khoa.dto.ResultMessage;
import com.khoa.entity.NganHang;
import com.khoa.entity.TaiKhoanLienNganHang;
import com.khoa.service.NganHangService;
import com.khoa.service.TaiKhoanLienNganHangService;

@RestController
@RequestMapping("/api/rsa")
public class ApiRSA {
	private static final long TIMEOUT = 1000 * 60 * 5;
	private static final String SECRET = "localtorsa";

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
	public ResponseEntity confirmTranfer(@RequestBody LienNganHangDTO lienNganHangDTO) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
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
		
		String stringpub = new String(Files.readAllBytes(Paths.get("publicrsa64.pem")));
		stringpub = stringpub.replaceAll("\\n", "").replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "");
		System.out.println("stringpub: " + stringpub);
		X509EncodedKeySpec kspub = new X509EncodedKeySpec(Base64.getDecoder().decode(stringpub));
		KeyFactory kfpub = KeyFactory.getInstance("RSA");
		PublicKey pub2 = kfpub.generatePublic(kspub);
		
		boolean verified = true;
		Signature sign2 = Signature.getInstance("SHA512withRSA");
		sign2.initVerify(pub2);
		String chuoiSignatureBenGui = lienNganHangDTO.getPartnerCode() + "|" + lienNganHangDTO.getMataikhoanthanhtoan() + "|" + lienNganHangDTO.getTimeStampe() + "|" + SECRET;
		byte[] bytes2 = chuoiSignatureBenGui.getBytes("UTF-8");
		sign2.update(bytes2);

		boolean bool = sign2.verify(lienNganHangDTO.getSignature());
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
		
		String stringprv = new String(Files.readAllBytes(Paths.get("privatersa64.pem")));
		stringprv =  stringprv.replaceAll("\\n", "").replace("-----BEGIN RSA PRIVATE KEY-----", "").replace("-----END RSA PRIVATE KEY-----", "");
		System.out.println("stringprv: " + stringprv);
		PKCS8EncodedKeySpec ksprv = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(stringprv));
		KeyFactory kfprv = KeyFactory.getInstance("RSA");
		PrivateKey pvt2 = kfprv.generatePrivate(ksprv);
		
		Signature sign = Signature.getInstance("SHA512withRSA"); // Dùng thuật toán SHA256 để ký, hoặc dùng các loại khác
		sign.initSign(pvt2);
		String chuoiSignatureBenNhan = "200-OK|" + SECRET;
		byte[] bytes1 = chuoiSignatureBenNhan.getBytes("UTF-8");
		sign.update(bytes1);
		byte[] signature = sign.sign();
		System.out.println("signature: " + signature);
		
		result.setStatus(0);
		result.setHoten(taiKhoanLienNganHang.getHoten());
		result.setSignature(signature);
		result.setMessage("Thành công");
		return ResponseEntity.status(HttpStatus.OK).body(result);

	}
}
