package com.filesservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface CloudinaryService {

    Map uploadFile(MultipartFile file, String folder);

    void deleteFile(String publicId);

    void deleteFiles(List<String> publicIds);
}
