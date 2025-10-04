package com.example.demo.service;

import com.example.demo.entity.base.File;

import java.util.List;

public interface FileService {

    List<File> findAll();

    File findById(Long id);

    File save(File file);

    void delete(Long id);
}
