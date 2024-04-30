package DTO;

public class Quyen {
	private int roleId;
	private String roleName;
	private String roleTabName;
	private int status;
	
	public Quyen() {}
	
	public Quyen(int roleId, String roleName,String roleTabName, int status) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleTabName = roleTabName;
		this.status = status;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRoleTabName() {
		return roleTabName;
	}

	public void setRoleTabName(String roleTabName) {
		this.roleTabName = roleTabName;
	}

	
	
}
