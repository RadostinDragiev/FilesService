package com.filesservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "rooms_photos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomPhoto extends BaseUUIDEntity {

    @Column(name = "room_id", nullable = false)
    private String roomId;

    @Column(name = "room_type_id", nullable = false)
    private String roomTypeId;

    @Column(name = "public_id", nullable = false, unique = true)
    private String publicId;

    @Column(name = "secure_url", nullable = false)
    private String secureUrl;

    @Column(name = "created_date_time", nullable = false)
    private LocalDateTime createdDateTime;

    @Column(name = "created_by", nullable = false)
    private String createdBy;
}
