package com.inayat.yourrooms.dto;

import java.util.Date;

public class UsersDTO {
	private Long id;

	private String firstName;

	private String lastName;

	private String email;

	private String mobile;

	private String dob;

	private String gender;

	private Long referred_by;

	private Date last_login_time;

	private Boolean is_logged_in;

	private Boolean is_verified;

	private String token;

	private Boolean is_enabled;

	private Boolean del_ind;

	private Date create_dt;

	private Date update_dt;

	private Long create_user_id;

	private Long update_user_id;

	private RolesDTO roles;

	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getReferred_by() {
		return referred_by;
	}

	public void setReferred_by(Long referred_by) {
		this.referred_by = referred_by;
	}

	public Date getLast_login_time() {
		return last_login_time;
	}

	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}

	public Boolean getIs_logged_in() {
		return is_logged_in;
	}

	public void setIs_logged_in(Boolean is_logged_in) {
		this.is_logged_in = is_logged_in;
	}

	public Boolean getIs_verified() {
		return is_verified;
	}

	public void setIs_verified(Boolean is_verified) {
		this.is_verified = is_verified;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Boolean getDel_ind() {
		return del_ind;
	}

	public void setDel_ind(Boolean del_ind) {
		this.del_ind = del_ind;
	}

	public Date getCreate_dt() {
		return create_dt;
	}

	public void setCreate_dt(Date create_dt) {
		this.create_dt = create_dt;
	}

	public Date getUpdate_dt() {
		return update_dt;
	}

	public void setUpdate_dt(Date update_dt) {
		this.update_dt = update_dt;
	}

	public Long getCreate_user_id() {
		return create_user_id;
	}

	public void setCreate_user_id(Long create_user_id) {
		this.create_user_id = create_user_id;
	}

	public Long getUpdate_user_id() {
		return update_user_id;
	}

	public void setUpdate_user_id(Long update_user_id) {
		this.update_user_id = update_user_id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIs_enabled() {
		return is_enabled;
	}

	public void setIs_enabled(Boolean is_enabled) {
		this.is_enabled = is_enabled;
	}

	public String getPassword() {
		return password;
	}

	public RolesDTO getRoles() {
		return roles;
	}

	public void setRoles(RolesDTO roles) {
		this.roles = roles;
	}

}
