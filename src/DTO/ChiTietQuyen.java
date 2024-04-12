package DTO;

public class ChiTietQuyen {
	private int roleId;
	private int accountId;
	
	public ChiTietQuyen() {}
	
	public ChiTietQuyen(int roleId, int accountId) {
		this.roleId = roleId;
		this.accountId = accountId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
}
