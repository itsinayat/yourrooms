package com.inayat.yourrooms.translator;

import com.inayat.yourrooms.dto.UsersDTO;
import com.inayat.yourrooms.entity.User;

public class UsersTranslator {
	
	public static UsersDTO convertToDto(User tUser) {
		UsersDTO dto = new UsersDTO();
		dto.setCreate_dt(tUser.getCreate_dt());
		dto.setCreate_user_id(tUser.getCreate_user_id());
		dto.setDel_ind(tUser.getDel_ind());
		dto.setDob(tUser.getDob());
		dto.setEmail(tUser.getEmail());
		dto.setFirstName(tUser.getFirstName());
		dto.setGender(tUser.getGender());
		dto.setIs_logged_in(tUser.getIs_logged_in());
		dto.setIs_verified(tUser.getIs_verified());
		dto.setLast_login_time(tUser.getLast_login_time());
		dto.setLastName(tUser.getLastName());
		dto.setMobile(tUser.getMobile());
		dto.setReferred_by(tUser.getReferred_by());
		dto.setUpdate_dt(tUser.getUpdate_dt());
		dto.setUpdate_user_id(tUser.getUpdate_user_id());
		dto.setUsername(tUser.getUsername());
		dto.setRole(tUser.getRole().getName());
		dto.setReferral_code(tUser.getReferral_code());
		dto.setId(tUser.getId());
	    return dto;
	}
	public static User convertToDao(UsersDTO dto) {
		User dao = new User();
		dao.setCreate_dt(dto.getCreate_dt());
		dao.setCreate_user_id(0L);
		dao.setDel_ind(false);
		dao.setDob(dto.getDob());
		dao.setEmail(dto.getEmail());
		dao.setFirstName(dto.getFirstName());
		dao.setGender(dto.getGender());
		dao.setIs_logged_in(dto.getIs_logged_in());
		dao.setIs_verified(dto.getIs_verified());
		dao.setLast_login_time(dto.getLast_login_time());
		dao.setLastName(dto.getLastName());
		dao.setMobile(dto.getMobile());
		dao.setReferred_by(dto.getReferred_by());
		dao.setUpdate_dt(dto.getUpdate_dt());
		dao.setUpdate_user_id(dto.getUpdate_user_id());
		//username is mobile number
		dao.setUsername(dto.getMobile());
		dao.setPassword(dto.getPassword());
		dao.setReferral_code(dto.getReferral_code());
	    return dao;
	}

	
}
