package com.khoa.dto;

public class ChangePassWordDTO {
	private String email;
	private int otp;
	private String oldpassword;
	private String newpassword;
	
	public ChangePassWordDTO() {
		
	}

	public ChangePassWordDTO(String email, int otp, String oldpassword, String newpassword) {
		super();
		this.email = email;
		this.otp = otp;
		this.oldpassword = oldpassword;
		this.newpassword = newpassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

}
