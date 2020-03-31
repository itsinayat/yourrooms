package com.inayat.yourrooms.translator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.inayat.yourrooms.dto.UsersDTO;
import com.inayat.yourrooms.entity.User;

@Configuration
public class UsersTranslator {
	
	public UsersDTO convertToDto(User tUser) {
		UsersDTO dto = new UsersDTO();
		dto.setId(tUser.getId());
		dto.setCreate_dt(tUser.getCreate_dt());
		dto.setCreate_user_id(tUser.getCreate_user_id());
		dto.setDel_ind(tUser.getDel_ind());
		dto.setDob(tUser.getDob());
		dto.setEmail(tUser.getEmail());
		dto.setFirstName(tUser.getFirstName());
		dto.setGender(tUser.getGender());
		dto.setIs_enabled(tUser.getIs_enabled());
		dto.setIs_logged_in(tUser.getIs_logged_in());
		dto.setIs_verified(tUser.getIs_verified());
		dto.setLast_login_time(tUser.getLast_login_time());
		dto.setLastName(tUser.getLastName());
		dto.setMobile(tUser.getMobile());
		dto.setReferred_by(tUser.getReferred_by());
		dto.setUpdate_dt(tUser.getUpdate_dt());
		dto.setUpdate_user_id(tUser.getUpdate_user_id());
	    return dto;
	}

	
}
