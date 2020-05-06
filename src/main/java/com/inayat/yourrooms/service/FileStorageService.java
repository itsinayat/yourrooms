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
import com.inayat.yourrooms.entity.Hotels;
import com.inayat.yourrooms.entity.Rooms;
import com.inayat.yourrooms.model.ApiResponse;
import com.inayat.yourrooms.repositories.HotelRepository;
import com.inayat.yourrooms.repositories.RoomsRepository;

@Service
public class FileStorageService {

	@Autowired
	RoomsRepository roomsRepository;
	
	@Autowired
	HotelRepository hotelRepository;

	public static Path getFileStorageService(String id) {
		Path fileStorageLocation;
		fileStorageLocation = Paths.get(FileStorageProperties.uploadDirRooom + "//" + id).toAbsolutePath().normalize();

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
			Path targetLocation = getFileStorageService(roomId).resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			String fileDownloadUri = "hotel" + "/" + "downloadFile" + "/" + "room" + "/" + roomId + "/" + fileName;
			UploadFileResponse ufr = new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(),
					file.getSize());

			return ufr;
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
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
			Path targetLocation = getFileStorageService(roomId).resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			String fileDownloadUri = "hotel" + "/" + "downloadFile" + "/" + roomId + "/" + fileName;
			UploadFileResponse ufr = new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(),
					file.getSize());

			return ufr;
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	public ApiResponse storeFileRoomsMultiple(MultipartFile[] files, String roomId) throws IOException {
		Optional<Rooms> rooms = roomsRepository.findById(Long.valueOf(roomId));
		if (!rooms.isPresent()) {
			return new ApiResponse(785, "ROOM not found");
		}
		Rooms room = rooms.get();
		List<String> dfuri = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		List<UploadFileResponse> ufrs = Arrays.asList(files).stream().map(file -> storeFileRoom(file, roomId))
				.collect(Collectors.toList());
		for (UploadFileResponse ufr : ufrs) {

			dfuri.add(ufr.getFileDownloadUri());
		}
		String jsonStr = mapper.writeValueAsString(dfuri);
		room.setImages(jsonStr);
		roomsRepository.save(room);
		return new ApiResponse(432, "SUCCESS", ufrs);
	}

	public Resource loadFileAsResource(String fileName, String roomId) {
		try {
			Path filePath = getFileStorageService(roomId).resolve(fileName).normalize();
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

	public ApiResponse getImagesOfRoom(String roomId) throws JsonParseException, JsonMappingException, IOException {
		Optional<Rooms> rooms = roomsRepository.findById(Long.valueOf(roomId));
		if (!rooms.isPresent()) {
			return new ApiResponse(785, "ROOM not found");
		}
		Rooms room = rooms.get();
		String images = room.getImages();
		ObjectMapper mapper = new ObjectMapper();
		String[] pp1 = mapper.readValue(images, String[].class);
		return new ApiResponse(432, "SUCCESS", pp1);
	}

	public ApiResponse storeFileHotelsMultiple(MultipartFile[] files, String hotelId) throws JsonProcessingException {

		Optional<Hotels> hotels = hotelRepository.findById(Long.valueOf(hotelId));
		if (!hotels.isPresent()) {
			return new ApiResponse(785, "Hotel not found");
		}
		Hotels hotel = hotels.get();
		List<String> dfuri = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		List<UploadFileResponse> ufrs = Arrays.asList(files).stream().map(file -> storeFileHotel(file, hotelId))
				.collect(Collectors.toList());
		for (UploadFileResponse ufr : ufrs) {

			dfuri.add(ufr.getFileDownloadUri());
		}
		String jsonStr = mapper.writeValueAsString(dfuri);
		hotel.setImages(jsonStr);
		hotelRepository.save(hotel);
		return new ApiResponse(432, "SUCCESS", ufrs);

	}

}
