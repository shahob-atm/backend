package org.example.backend.controller;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/file")
public class FileController {

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> saveFile(@RequestParam MultipartFile file) throws IOException {

        String filename = UUID.randomUUID() + file.getOriginalFilename();
        FileOutputStream fileOutputStream = new FileOutputStream("files/" + filename);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
        return ResponseEntity.ok(filename);
    }

    @GetMapping("/{name}")
    public void getFile(@PathVariable String name, HttpServletResponse response) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("files/" + name);
        ServletOutputStream servletOutputStream = response.getOutputStream();
        fileInputStream.transferTo(servletOutputStream);
        fileInputStream.close();
    }
}
