package com.filesservice.model.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadPhotoDto {

    @NotNull
    @Size(min = 1)
    private List<MultipartFile> files;

    @NotNull
    private String roomId;

    @NotNull
    private String roomTypeId;
}
