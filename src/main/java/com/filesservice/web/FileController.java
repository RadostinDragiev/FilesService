package com.filesservice.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FileController {

    @PostMapping("/create-file")
    public ResponseEntity<Void> createFile() {
        log.warn("Communication assembled!");
        return ResponseEntity.ok().build();
    }
}
