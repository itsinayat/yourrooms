package com.inayat.yourrooms.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inayat.yourrooms.dto.FileStorageException;
import com.inayat.yourrooms.dto.FileStorageProperties;
import com.inayat.yourrooms.dto.MyFileNotFoundException;
import com.inayat.yourrooms.dto.UploadFileResponse;
import com.inayat.yourrooms.entity.HotelImage;
import com.inayat.yourrooms.entity.Hotel;
import com.inayat.yourrooms.entity.RoomImage;
import com.inayat.yourrooms.entity.Room;
import com.inayat.yourrooms.model.ApiResponse;
import com.inayat.yourrooms.repositories.HotelRepository;
import com.inayat.yourrooms.repositories.RoomsImageRepository;
import com.inayat.yourrooms.repositories.HotelImageRepository;
import com.inayat.yourrooms.repositories.RoomsRepository;

@Service
public class FileStorageService {

	@Autowired
	RoomsRepository roomsRepository;

	@Autowired
	HotelRepository hotelRepository;

	@Autowired
	HotelImageRepository hotelImageRepository;

	@Autowired
	RoomsImageRepository roomsImageRepository;

	public static Path getFileStorageRoom(String id) {
		Path fileStorageLocation;
		fileStorageLocation = Paths.get(FileStorageProperties.uploadDirHotel + "//" + id).toAbsolutePath().normalize();

		try {
			Files.createDirectories(fileStorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}
		return fileStorageLocation;
	}

	public UploadFileResponse storeFileRoom(MultipartFile file, String roomId) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = getFileStorageRoom(roomId).resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			String fileDownloadUri = "hotel" + "/" + "downloadFile" + "/" + "room" + "/" + roomId + "/" + fileName;
			UploadFileResponse ufr = new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(),
					file.getSize());

			return ufr;
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	public ApiResponse storeFileRoomsMultiple(MultipartFile[] files, String roomId) throws IOException {
		Optional<Room> rooms = roomsRepository.findById(Long.valueOf(roomId));
		if (!rooms.isPresent()) {
			return new ApiResponse(785, "ROOM not found");
		}
		Room room = rooms.get();
		List<UploadFileResponse> ufrs = Arrays.asList(files).stream().map(file -> storeFileRoom(file, roomId))
				.collect(Collectors.toList());
		for (UploadFileResponse ufr : ufrs) {
			RoomImage roomImage = new RoomImage(ufr.getFileDownloadUri(), room);
			roomsImageRepository.save(roomImage);
		}

		return new ApiResponse(432, "SUCCESS");
	}

	public Resource loadFileAsResourceRoom(String fileName, String id) {
		try {
			Path filePath = getFileStorageRoom(id).resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new MyFileNotFoundException("File not found " + fileName);
			}
		} catch (MalformedURLException ex) {
			throw new MyFileNotFoundException("File not found " + fileName, ex);
		}
	}

	// hotel
	public static Path getFileStorageHotel(String id) {
		Path fileStorageLocation;
		fileStorageLocation = Paths.get(FileStorageProperties.uploadDirHotel + "//" + id).toAbsolutePath().normalize();

		try {
			Files.createDirectories(fileStorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}
		return fileStorageLocation;
	}

	public UploadFileResponse storeFileHotel(MultipartFile file, String roomId) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = getFileStorageHotel(roomId).resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			String fileDownloadUri = "hotel" + "/" + "downloadFile" + "/hotel/" + roomId + "/" + fileName;
			UploadFileResponse ufr = new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(),
					file.getSize());

			return ufr;
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	public Resource loadFileAsResourceHotel(String fileName, String id) {
		try {
			Path filePath = getFileStorageRoom(id).resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new MyFileNotFoundException("File not found " + fileName);
			}
		} catch (MalformedURLException ex) {
			throw new MyFileNotFoundException("File not found " + fileName, ex);
		}
	}

	public ApiResponse storeFileHotelsMultiple(MultipartFile[] files, String hotelId) throws JsonProcessingException {

		Optional<Hotel> hotels = hotelRepository.findById(Long.valueOf(hotelId));
		if (!hotels.isPresent()) {
			return new ApiResponse(785, "Hotel not found");
		}
		Hotel hotel = hotels.get();
		List<UploadFileResponse> ufrs = Arrays.asList(files).stream().map(file -> storeFileHotel(file, hotelId))
				.collect(Collectors.toList());
		for (UploadFileResponse ufr : ufrs) {
			HotelImage image = new HotelImage(ufr.getFileDownloadUri(), hotel);
			hotelImageRepository.save(image);
		}
		return new ApiResponse(432, "SUCCESS");

	}

	public ApiResponse deletefromroom(String id) {
		Optional<RoomImage> rooms = roomsImageRepository.findById(Long.valueOf(id));
		if (rooms.isPresent()) {
			roomsImageRepository.delete(rooms.get());
			return new ApiResponse(432, "IMAGE DELETED");
		} else {
			return new ApiResponse(432, "Image NOT FOUND");
		}

	}

	public ApiResponse deletefromhotel(String id) {
		Optional<HotelImage> rooms = hotelImageRepository.findById(Long.valueOf(id));
		if (rooms.isPresent()) {
			hotelImageRepository.delete(rooms.get());
			return new ApiResponse(432, "IMAGE DELETED");
		} else {
			return new ApiResponse(432, "Image NOT FOUND");
		}

	}

}
