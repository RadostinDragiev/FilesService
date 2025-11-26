package com.filesservice.repository;

import com.filesservice.model.entity.RoomTypePhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoomTypePhotoRepository extends JpaRepository<RoomTypePhoto, UUID> {

    List<RoomTypePhoto> getAllByRoomTypeId(String roomTypeId);

    void deleteByPublicId(String publicId);

    void deleteAllByRoomTypeId(String roomTypeId);
}
