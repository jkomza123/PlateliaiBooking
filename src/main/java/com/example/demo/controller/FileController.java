package com.example.demo.controller;

import com.example.demo.entity.base.File;
import com.example.demo.service.FileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping
    public List<File> getAllFiles() {
        return fileService.findAll();
    }

    @GetMapping("/{id}")
    public File getFile(@PathVariable Long id) {
        return fileService.findById(id);
    }

    @PostMapping
    public File createFile(@RequestBody File file) {
        return fileService.save(file);
    }

    @PutMapping("/{id}")
    public File updateFile(@PathVariable Long id, @RequestBody File file) {
        file.setId(id);
        return fileService.save(file);
    }

    @DeleteMapping("/{id}")
    public void deleteFile(@PathVariable Long id) {
        fileService.delete(id);
    }
}
