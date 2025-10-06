package com.example.demo.service.impl;

import com.example.demo.dao.ImageDao;
import com.example.demo.entity.Image;
import com.example.demo.service.ImageService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageDao imageDao;

    public ImageServiceImpl(ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    public List<Image> findAll() {
        return imageDao.findAll();
    }

    public Image findById(Long id) {
        return imageDao.findById(id).orElse(null);
    }

    @Transactional
    public Image save(Image file) {
        return imageDao.save(file);
    }

    @Transactional
    public void delete(Long id) {
        imageDao.deleteById(id);
    }
}
