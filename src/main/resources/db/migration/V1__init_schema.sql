-- ROOMS_PHOTOS
CREATE TABLE rooms_photos
(
    uuid              UUID         NOT NULL,
    room_id           VARCHAR(255) NOT NULL,
    public_id         VARCHAR(255) NOT NULL,
    secure_url        VARCHAR(255) NOT NULL,
    created_date_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by        VARCHAR(255) NOT NULL,
    PRIMARY KEY (uuid)
);

-- ROOM_TYPES_PHOTOS
CREATE TABLE room_types_photos
(
    uuid              UUID         NOT NULL,
    room_type_id      VARCHAR(255) NOT NULL,
    public_id         VARCHAR(255) NOT NULL,
    secure_url        VARCHAR(255) NOT NULL,
    created_date_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by        VARCHAR(255) NOT NULL,
    PRIMARY KEY (uuid)
);
