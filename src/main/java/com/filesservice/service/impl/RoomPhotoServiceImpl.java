package com.filesservice.service.impl;

import com.filesservice.model.dto.request.UploadPhotoDto;
import com.filesservice.model.dto.response.RoomPhotoDto;
import com.filesservice.model.entity.RoomPhoto;
import com.filesservice.repository.RoomPhotoRepository;
import com.filesservice.service.CloudinaryService;
import com.filesservice.service.RoomPhotoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RoomPhotoServiceImpl implements RoomPhotoService {

    private final RoomPhotoRepository roomPhotoRepository;
    private final CloudinaryService cloudinaryService;
    private final ModelMapper modelMapper;

    @SuppressWarnings("unchecked")
    @Override
    public List<RoomPhotoDto> uploadPhoto(UploadPhotoDto uploadPhotoDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        List<RoomPhoto> roomPhotos = new ArrayList<>();
        for (MultipartFile file : uploadPhotoDto.getFiles()) {
            Map<String, String> uploadedFile = this.cloudinaryService.uploadFile(file, "");

            roomPhotos.add(RoomPhoto.builder()
                    .roomId(uploadPhotoDto.getRoomId())
                    .roomTypeId(uploadPhotoDto.getRoomTypeId())
                    .publicId(uploadedFile.get("public_id"))
                    .secureUrl(uploadedFile.get("secure_url"))
                    .createdBy(auth.getName())
                    .build());
        }

        return this.roomPhotoRepository.saveAll(roomPhotos)
                .stream()
                .map(photo -> this.modelMapper.map(photo, RoomPhotoDto.class))
                .toList();
    }

    @Override
    public List<RoomPhotoDto> getAllRoomPhotosByRoomId(String roomId) {
        return this.roomPhotoRepository.getAllByRoomId(roomId)
                .stream()
                .map(photo -> this.modelMapper.map(photo, RoomPhotoDto.class))
                .toList();
    }

    @Override
    public List<RoomPhotoDto> getAllRoomPhotosByRoomTypeId(String roomTypeId) {
        return this.roomPhotoRepository.getAllByRoomTypeId(roomTypeId)
                .stream()
                .map(photo -> this.modelMapper.map(photo, RoomPhotoDto.class))
                .toList();
    }

    @Override
    public void deletePhoto(String publicId) {
        this.cloudinaryService.deleteFile(publicId);
        this.roomPhotoRepository.deleteByPublicId(publicId);
    }

    @Override
    public void deleteAllByRoom(String roomId) {
        List<String> publicIds = this.roomPhotoRepository.getAllByRoomId(roomId).stream()
                .map(RoomPhoto::getPublicId)
                .toList();

        this.cloudinaryService.deleteFiles(publicIds);
        this.roomPhotoRepository.deleteAllByRoomId(roomId);
    }

    @Override
    public void deleteAllByRoomType(String roomTypeId) {
        List<String> publicIds = this.roomPhotoRepository.getAllByRoomTypeId(roomTypeId).stream()
                .map(RoomPhoto::getPublicId)
                .toList();

        this.cloudinaryService.deleteFiles(publicIds);
        this.roomPhotoRepository.deleteAllByRoomTypeId(roomTypeId);
    }
}
