package DTO;

public class TaiKhoan {
	private int accountId;
	private String username;
	private String password;
	private int accountStatus;
	private String position;
	
	public TaiKhoan() {}

	public TaiKhoan(int accountId, String username, String password, int accountStatus, String position) {
		this.accountId = accountId;
		this.username = username;
		this.password = password;
		this.accountStatus = accountStatus;
		this.position = position;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(int accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
}
