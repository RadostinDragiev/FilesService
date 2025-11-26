package com.filesservice.service;

import com.filesservice.model.dto.response.RoomPhotoDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RoomPhotoService {

    List<RoomPhotoDto> uploadImages(String roomId, List<MultipartFile> images);

    List<RoomPhotoDto> getAllRoomPhotosByRoomId(String roomId);

    void deletePhoto(String publicId);

    void deleteAllByRoom(String roomId);
}
