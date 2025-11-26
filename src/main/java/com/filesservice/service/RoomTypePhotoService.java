package com.filesservice.service;

import com.filesservice.model.dto.response.RoomTypePhotoDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RoomTypePhotoService {

    List<RoomTypePhotoDto> uploadImages(String roomTypeId, List<MultipartFile> images);

    List<RoomTypePhotoDto> getAllById(String roomTypeId);

    void deletePhoto(String publicId);

    void deleteAllByType(String roomTypeId);
}
