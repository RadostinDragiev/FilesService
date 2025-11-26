package com.filesservice.web;

import com.filesservice.model.dto.response.RoomTypePhotoDto;
import com.filesservice.service.RoomTypePhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/room-type")
@PreAuthorize("hasAnyRole('ADMINISTRATOR', 'MANAGER')")
@RequiredArgsConstructor
public class RoomTypePhotoController {

    private final RoomTypePhotoService roomTypePhotoService;

    @PostMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<RoomTypePhotoDto>> uploadImage(@PathVariable String id, @RequestParam List<MultipartFile> images) {
        log.info("Upload images for room type triggered");
        List<RoomTypePhotoDto> roomTypePhotos = this.roomTypePhotoService.uploadImages(id, images);

        return ResponseEntity.ok(roomTypePhotos);
    }

    @GetMapping("/{roomTypeId}")
    public ResponseEntity<List<RoomTypePhotoDto>> getPhotosByRoomType(@PathVariable String roomTypeId) {
        log.info("Get all room type photos for room type id: {}", roomTypeId);
        List<RoomTypePhotoDto> roomPhotos = this.roomTypePhotoService.getAllById(roomTypeId);

        return ResponseEntity.ok(roomPhotos);
    }

    @DeleteMapping("/{publicId}")
    public ResponseEntity<Void> deletePhoto(@PathVariable String publicId) {
        log.info("Delete room type photo with public id: {}", publicId);
        this.roomTypePhotoService.deletePhoto(publicId);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{roomTypeId}")
    public ResponseEntity<Void> deletePhotoByRoomType(@PathVariable String roomTypeId) {
        log.info("Delete room type photos with room type id: {}", roomTypeId);
        this.roomTypePhotoService.deleteAllByType(roomTypeId);

        return ResponseEntity.ok().build();
    }
}
