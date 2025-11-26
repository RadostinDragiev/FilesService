package com.filesservice.service.impl;

import com.filesservice.model.dto.response.RoomTypePhotoDto;
import com.filesservice.model.entity.RoomTypePhoto;
import com.filesservice.repository.RoomTypePhotoRepository;
import com.filesservice.service.CloudinaryService;
import com.filesservice.service.RoomTypePhotoService;
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
public class RoomTypePhotoServiceImpl implements RoomTypePhotoService {

    private final RoomTypePhotoRepository roomTypePhotoRepository;
    private final CloudinaryService cloudinaryService;
    private final ModelMapper modelMapper;

    @SuppressWarnings("unchecked")
    @Override
    public List<RoomTypePhotoDto> uploadImages(String roomTypeId, List<MultipartFile> images) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        List<RoomTypePhoto> roomTypeImages = new ArrayList<>();
        for (MultipartFile image : images) {
            Map<String, String> uploadedFile = this.cloudinaryService.uploadFile(image, "");

            RoomTypePhoto roomTypePhoto = RoomTypePhoto.builder()
                    .roomTypeId(roomTypeId)
                    .publicId(uploadedFile.get("public_id"))
                    .secureUrl(uploadedFile.get("secure_url"))
                    .createdBy(auth.getName())
                    .build();
            roomTypeImages.add(roomTypePhoto);
        }

        List<RoomTypePhoto> roomTypePhotos = this.roomTypePhotoRepository.saveAll(roomTypeImages);

        return roomTypePhotos.stream()
                .map(roomTypePhoto -> this.modelMapper.map(roomTypePhoto, RoomTypePhotoDto.class))
                .toList();
    }

    @Override
    public List<RoomTypePhotoDto> getAllById(String roomTypeId) {
        return this.roomTypePhotoRepository.getAllByRoomTypeId(roomTypeId)
                .stream()
                .map(photo -> this.modelMapper.map(photo, RoomTypePhotoDto.class))
                .toList();
    }

    @Override
    public void deletePhoto(String publicId) {
        this.cloudinaryService.deleteFile(publicId);
        this.roomTypePhotoRepository.deleteByPublicId(publicId);
    }

    @Override
    public void deleteAllByType(String roomTypeId) {
        List<String> publicIds = this.roomTypePhotoRepository.getAllByRoomTypeId(roomTypeId).stream()
                .map(RoomTypePhoto::getPublicId)
                .toList();

        this.cloudinaryService.deleteFiles(publicIds);
        this.roomTypePhotoRepository.deleteAllByRoomTypeId(roomTypeId);
    }
}
