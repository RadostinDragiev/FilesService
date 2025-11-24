package com.filesservice.service;

import com.filesservice.model.dto.request.UploadPhotoDto;
import com.filesservice.model.dto.response.RoomPhotoDto;

import java.util.List;

public interface RoomPhotoService {

    List<RoomPhotoDto> uploadPhoto(UploadPhotoDto uploadPhotoDto);

    List<RoomPhotoDto> getAllRoomPhotosByRoomId(String roomId);

    List<RoomPhotoDto> getAllRoomPhotosByRoomTypeId(String roomTypeId);

    void deletePhoto(String publicId);

    void deleteAllByRoom(String roomId);

    void deleteAllByRoomType(String roomTypeId);
}
