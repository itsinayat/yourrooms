package com.inayat.yourrooms.translator;

import com.inayat.yourrooms.dto.UserTokenDTO;
import com.inayat.yourrooms.entity.UserToken;

public class UserTokenTranslator {

	public static UserTokenDTO translateToDTO(UserToken dto) {
		UserTokenDTO token = new UserTokenDTO();
		token.setTokenKey(dto.getTokenKey());
		token.setRole(dto.getUser().getRole().getName());
		return token;

	}

}
