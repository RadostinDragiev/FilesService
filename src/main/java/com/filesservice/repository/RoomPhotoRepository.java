package com.filesservice.repository;

import com.filesservice.model.entity.RoomPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoomPhotoRepository extends JpaRepository<RoomPhoto, UUID> {

    List<RoomPhoto> getAllByRoomId(String roomId);

    List<RoomPhoto> getAllByRoomTypeId(String roomTypeId);

    @Modifying
    @Transactional
    void deleteByPublicId(String publicId);

    @Modifying
    @Transactional
    void deleteAllByRoomId(String roomId);

    @Modifying
    @Transactional
    void deleteAllByRoomTypeId(String roomTypeId);
}
