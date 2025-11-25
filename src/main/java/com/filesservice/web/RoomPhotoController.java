package com.filesservice.web;

import com.filesservice.model.dto.request.UploadPhotoDto;
import com.filesservice.model.dto.response.RoomPhotoDto;
import com.filesservice.service.RoomPhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@PreAuthorize("hasAnyRole('ADMINISTRATOR', 'MANAGER')")
@RequiredArgsConstructor
public class RoomPhotoController {

    private final RoomPhotoService roomPhotoService;

    @PostMapping(value = "/upload-photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<RoomPhotoDto>> uploadPhotos(@ModelAttribute UploadPhotoDto uploadPhotoDto) {
        log.info("Upload room photo triggered");
        List<RoomPhotoDto> roomPhotos = this.roomPhotoService.uploadPhoto(uploadPhotoDto);

        return ResponseEntity.ok(roomPhotos);
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<RoomPhotoDto>> getPhotosByRoom(@PathVariable String roomId) {
        log.info("Get all room photos for room id: {}", roomId);
        List<RoomPhotoDto> roomPhotosByRoomId = this.roomPhotoService.getAllRoomPhotosByRoomId(roomId);

        return ResponseEntity.ok(roomPhotosByRoomId);
    }

    @GetMapping("/room-type/{roomTypeId}")
    public ResponseEntity<List<RoomPhotoDto>> getPhotosByRoomType(@PathVariable String roomTypeId) {
        log.info("Get all room photos for room type id: {}", roomTypeId);
        List<RoomPhotoDto> roomPhotos = this.roomPhotoService.getAllRoomPhotosByRoomTypeId(roomTypeId);

        return ResponseEntity.ok(roomPhotos);
    }

    @DeleteMapping("/{publicId}")
    public ResponseEntity<Void> deletePhoto(@PathVariable String publicId) {
        log.info("Delete photo with public id: {}", publicId);
        this.roomPhotoService.deletePhoto(publicId);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/room/{roomId}")
    public ResponseEntity<Void> deletePhotoByRoom(@PathVariable String roomId) {
        log.info("Delete photos with room id: {}", roomId);
        this.roomPhotoService.deleteAllByRoom(roomId);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/room-type/{roomTypeId}")
    public ResponseEntity<Void> deletePhotoByRoomType(@PathVariable String roomTypeId) {
        log.info("Delete photos with room type id: {}", roomTypeId);
        this.roomPhotoService.deleteAllByRoomType(roomTypeId);

        return ResponseEntity.ok().build();
    }
}
