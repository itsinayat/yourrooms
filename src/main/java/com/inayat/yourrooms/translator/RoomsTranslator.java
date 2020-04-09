package com.inayat.yourrooms.translator;

import com.inayat.yourrooms.dto.RoomsDTO;
import com.inayat.yourrooms.entity.Hotels;
import com.inayat.yourrooms.entity.Rooms;

public class RoomsTranslator {

	public static Rooms translateToDao(RoomsDTO dto, Hotels hotel) {
		Rooms dao = new Rooms();
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

	public static RoomsDTO translateToDTO(Rooms dao) {
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
		
		return dto;
	}

}
