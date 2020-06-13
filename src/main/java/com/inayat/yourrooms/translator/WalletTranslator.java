package com.inayat.yourrooms.translator;

import com.inayat.yourrooms.dto.WalletDTO;
import com.inayat.yourrooms.entity.Wallet;

public class WalletTranslator {
	public static WalletDTO convertToDto(Wallet dao) {
		WalletDTO dto = new WalletDTO();
		dto.setId(dao.getId());
		dto.setBalance(dao.getBalance());
		dto.setDel_ind(dao.getDel_ind());
		dto.setIs_activated(dao.getIs_activated());
		return dto;
	}

}
