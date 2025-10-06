package com.example.demo.service;

import com.example.demo.entity.Image;

import java.util.List;

public interface ImageService {

    List<Image> findAll();

    Image findById(Long id);

    Image save(Image image);

    void delete(Long id);
}
