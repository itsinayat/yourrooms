package com.inayat.yourrooms.dto;

public class ChangeRoleRequest {
		Long userId;
		Long roleId;
		public Long getUserId() {
			return userId;
		}
		public void setUserId(Long userId) {
			this.userId = userId;
		}
		public Long getRoleId() {
			return roleId;
		}
		public void setRoleId(Long roleId) {
			this.roleId = roleId;
		}
		
		
}