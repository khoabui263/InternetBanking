package com.khoa.dto;

public class NapTienDTO {
	private long mataikhoanchuyen;
	private long mataikhoannhan;
	private String money;
	private int type;
	
	public NapTienDTO() {
		
	}
	
	public NapTienDTO(long mataikhoanchuyen, long mataikhoannhan, String money, int type) {
		super();
		this.mataikhoanchuyen = mataikhoanchuyen;
		this.mataikhoannhan = mataikhoannhan;
		this.money = money;
		this.type = type;
	}

	public long getMataikhoanchuyen() {
		return mataikhoanchuyen;
	}

	public void setMataikhoanchuyen(long mataikhoanchuyen) {
		this.mataikhoanchuyen = mataikhoanchuyen;
	}

	public long getMataikhoannhan() {
		return mataikhoannhan;
	}

	public void setMataikhoannhan(long mataikhoannhan) {
		this.mataikhoannhan = mataikhoannhan;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
