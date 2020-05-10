package com.inayat.yourrooms.translator;

import com.inayat.yourrooms.dto.RoomsDTO;
import com.inayat.yourrooms.entity.Hotel;
import com.inayat.yourrooms.entity.Room;

public class RoomsTranslator {
	
	public static Room translateToDao(RoomsDTO dto, Hotel hotel) {
		Room dao = new Room();
		dao.setBalconyAvl(dto.getBalconyAvl());
		dao.setDoubleBed(dto.getDoubleBed());
		dao.setFreeCancellation(dto.getFreeCancellation());
		dao.setHotel(hotel);
		dao.setName(dto.getName());
		dao.setOccupacy(dto.getOccupacy());
		dao.setReserved(false);
		dao.setRoomSize(dto.getRoomSize());
		dao.setRoomType(dto.getRoomType());
		dao.setInitialPrice(dto.getInitialPrice());
		dao.setDiscountPrice(dto.getDiscountPrice());
		
		return dao;
	}

	public static RoomsDTO translateToDTO(Room dao) {
		RoomsDTO dto = new RoomsDTO();
		dto.setId(dao.getId());
		dto.setBalconyAvl(dao.getBalconyAvl());
		dto.setDoubleBed(dao.getDoubleBed());
		dto.setFreeCancellation(dao.getFreeCancellation());
		dto.setHotel(dao.getHotel());
		dto.setName(dao.getName());
		dto.setOccupacy(dao.getOccupacy());
		dto.setReserved(dao.getReserved());
		dto.setRoomSize(dao.getRoomSize());
		dto.setRoomType(dao.getRoomType());
		dto.setInitialPrice(dao.getInitialPrice());
		dto.setDiscountPrice(dao.getDiscountPrice());
		dto.setUpdate_dt(dao.getUpdate_dt());
		dto.setUpdate_user_id(dao.getUpdate_user_id());
		dto.setCreate_dt(dao.getCreate_dt());
		dto.setCreate_user_id(dao.getCreate_user_id());
		dto.setCreate_dt(dao.getCreate_dt());
		dto.setCreate_user_id(dao.getCreate_user_id());
		dto.setUpdate_dt(dao.getUpdate_dt());
		dto.setUpdate_user_id(dao.getUpdate_user_id());
		return dto;
	}

}
