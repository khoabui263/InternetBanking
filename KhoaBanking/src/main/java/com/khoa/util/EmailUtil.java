package com.khoa.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

	@Autowired
	private EmailTypesConstant emailTypesConstant;

	public static final String HOST_NAME = "smtp.gmail.com";
	public static final int SSL_PORT = 465; // Port for SSL
	public static final int TSL_PORT = 587; // Port for TLS/STARTTLS
	public static final String APP_EMAIL = "reaper15810@gmail.com"; // your email
	public static final String APP_PASSWORD = "Reaper310179"; // your password

	public int transfer(String otp, String receiveEmail, String loaiEmail) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", HOST_NAME);
		props.put("mail.smtp.socketFactory.port", SSL_PORT);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.port", SSL_PORT);

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(APP_EMAIL, APP_PASSWORD);
			}
		});
//		session = Session.getInstance(props, this);

		// 2) compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(APP_EMAIL));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiveEmail));

			// 3) create HTML content
			message.setSubject("Đây là email từ KhoaBanking");
			String htmlContent = "";

			switch (loaiEmail) {
			case EmailTypesConstant.CHANGE_PASSWORD:
				htmlContent = "<h2>Đây là mail xác nhận đổi mật khẩu</h2>" + "Mã xác nhận otp của bạn là: " + otp
						+ "<strong style='margin-left: 10px; color: red'>Lưu ý: Mã OTP này chỉ có hiệu lực trong vòng 1 tiếng</strong>";
				message.setContent(htmlContent, "text/html; charset=UTF-8");
				break;

			case EmailTypesConstant.TRANSFER:
				htmlContent = "<h2>Đây là mail xác nhận chuyển khoản</h2>" + "Mã xác nhận otp của bạn là: " + otp
						+ "<strong style='margin-left: 10px; color: red'>Lưu ý: Mã OTP này chỉ có hiệu lực trong vòng 1 tiếng</strong>";
				message.setContent(htmlContent, "text/html; charset=UTF-8");
				break;
				
			case EmailTypesConstant.ADDNEWACCOUNT:
				htmlContent = "<h2>Đây là mail xác nhận tạo và liên kết với tài khoản ngân hàng</h2>" + "Mã xác nhận otp của bạn là: " + otp
						+ "<strong style='margin-left: 10px; color: red'>Lưu ý: Mã OTP này chỉ có hiệu lực trong vòng 1 tiếng</strong>";
				message.setContent(htmlContent, "text/html; charset=UTF-8");
				break;
				
			case EmailTypesConstant.CHECKACCOUNTEXISTED:
				htmlContent = "<h2>Đây là mail xác nhận quên mật khẩu</h2>" + "Mã xác nhận otp của bạn là: " + otp
						+ "<strong style='margin-left: 10px; color: red'>Lưu ý: Mã OTP này chỉ có hiệu lực trong vòng 1 tiếng</strong>";
				message.setContent(htmlContent, "text/html; charset=UTF-8");
				break;

			default:
				break;
			}

			// 4) send message
			Transport.send(message);

			System.out.println("Message sent successfully");
			return 1;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return 0;
	}

}
