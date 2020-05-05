package com.inayat.yourrooms.translator;

import com.inayat.yourrooms.dto.WalletDTO;
import com.inayat.yourrooms.entity.Wallet;

public class WalletTranslator {
	public static WalletDTO convertToDto(Wallet dao) {
		WalletDTO dto = new WalletDTO();
		dto.setId(dao.getId());
		dto.setBalance(dao.getBalance());
		dto.setCreate_dt(dao.getCreate_dt());
		dto.setCreate_user_id(dao.getCreate_user_id());
		dto.setDel_ind(dao.getDel_ind());
		dto.setIs_activated(dao.getIs_activated());
		dto.setUpdate_dt(dao.getUpdate_dt());
		dto.setUpdate_user_id(dao.getUpdate_user_id());
		return dto;
	}

}
